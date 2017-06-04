import java.io.EOFException;
import java.io.ObjectInputStream;

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
				case LOGIN: // Alguien inició sesión
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
			System.out.println("El servidor se detuvo");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cliente.cerrarTodo();
		}
	}

}
