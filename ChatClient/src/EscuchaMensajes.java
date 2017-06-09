import java.io.EOFException;
import java.io.ObjectInputStream;

import javax.swing.JOptionPane;

public class EscuchaMensajes extends Thread {
	private MessengerClient cliente;
	private ObjectInputStream in;

	public EscuchaMensajes(MessengerClient cliente, ObjectInputStream in) {
		this.cliente = cliente;
		this.in = in;
	}

	@Override
	public void run() {
		try {
			while (true) {
				Message msg = (Message) in.readObject();
				switch (msg.getType()) {
				case LOGIN:
				case LOGOUT:
					cliente.setUsuariosEnLista(msg.getContent().split(","));
					break;
				case MENSAJE:
					cliente.recibirMensaje(msg);
					break;
				case DIFUSION:
					cliente.recibirMensaje(msg);
					break;
				default:
					break;
				}
			}
		} catch (EOFException e) {
			cliente.mostrarDialog("El servidor se detuvo", "Servidor apagado", JOptionPane.INFORMATION_MESSAGE);
			System.out.println("El servidor se detuvo");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cliente.cerrarTodo();
		}
	}

}
