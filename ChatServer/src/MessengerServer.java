import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MessengerServer extends Thread {

	private static ArchivoDePropiedades properties;
	private static Map<String, ServerThread> clients = new HashMap<String, ServerThread>();
	private static Thread server;
	private static ServerSocket serverSocket;
	protected static final int PUERTO = 8080;

	private final static int ANCHO = 700;
	private final static int ALTO = 640;
	private final static int ALTO_LOG = 520;
	private final static int ANCHO_LOG = ANCHO - 25;

	private static JButton botonIniciar;
	private static JButton botonDetener;
	public static JTextArea log;

	public static void main(String[] args) {
		cargarInterfaz();
	}

	private static void cargarInterfaz() {
		JFrame ventana = new JFrame("Servidor Messenger");
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setSize(ANCHO, ALTO);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setLayout(null);

		JLabel titulo = new JLabel("Log del servidor...");
		titulo.setFont(new Font("Courier New", Font.BOLD, 16));
		titulo.setBounds(10, 0, 200, 30);
		ventana.add(titulo);

		log = new JTextArea();
		log.setEditable(false);
		log.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		JScrollPane scroll = new JScrollPane(log, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBounds(10, 40, ANCHO_LOG, ALTO_LOG);
		ventana.add(scroll);

		botonIniciar = new JButton();
		botonDetener = new JButton();
		botonIniciar.setText("Iniciar");
		botonIniciar.setBounds(220, ALTO - 70, 100, 30);
		botonIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				server = new Thread(new MessengerServer());
				server.start();
				actualizarBotones(true);
			}
		});

		ventana.add(botonIniciar);

		botonDetener.setText("Detener");
		botonDetener.setBounds(360, ALTO - 70, 100, 30);
		botonDetener.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					close();
				} catch (IOException e1) {
					log.append("Fallo al intentar detener el servidor." + System.lineSeparator());
					e1.printStackTrace();
				}
			}
		});
		botonDetener.setEnabled(false);
		ventana.add(botonDetener);

		ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		ventana.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				if (serverSocket != null) {
					try {
						close();
					} catch (IOException e) {
						log.append("Fallo al intentar detener el servidor." + System.lineSeparator());
						e.printStackTrace();
						System.exit(1);
					}
				}
				System.exit(0);
			}
		});

		ventana.setVisible(true);
	}

	private static void actualizarBotones(boolean corriendo) {
		botonDetener.setEnabled(corriendo);
		botonIniciar.setEnabled(!corriendo);
	}
	
	protected synchronized static void logReceivedMessage(Message msg) {
		log.append("Recibido: " + msg.toString() + System.lineSeparator());
	}
	
	protected synchronized static void logSentMessage(Message msg) {
		log.append("Enviado: " + msg.toString() + System.lineSeparator());
	}
	
	private static void close() throws IOException {
		server.stop();
		for (ServerThread cliente : clients.values()) {
			cliente.close();
		}
		if (serverSocket != null) serverSocket.close();
	}
	
	public void run() {
		try {
			log.append("Iniciando el servidor..." + System.lineSeparator());
			properties = new ArchivoDePropiedades("config.properties");
			properties.lectura();
			serverSocket = new ServerSocket(properties.getPuerto());
			log.append("Servidor esperando conexiones en puerto " + properties.getPuerto() + "..." + System.lineSeparator());
			while (true) {
				new ServerThread(serverSocket.accept()).start();
			}
		} catch (BindException be) {
			log.append("Hay otro servidor corriendo en el puerto " + properties.getPuerto() + ". Modifique el archivo de configuración" + System.lineSeparator());
		} catch (IllegalArgumentException ie) {
			log.append("No se pudo conectar al puerto " + properties.getPuerto() + ". Modifique el archivo de configuración" + System.lineSeparator());
		} catch (Exception e) {
			log.append("Falló la conexión." + System.lineSeparator());
			e.printStackTrace();
		} finally {
			actualizarBotones(false);
		}
	}
	
	protected static synchronized void addClient(String username, ServerThread serverThread) {    
    	clients.put(username, serverThread);
    	log.append(username + " se unió." + System.lineSeparator());
    }
	
	protected static synchronized void deleteClient(String username) {
		clients.remove(username);
		try {
			difundir(new Message(MessageType.LOGOUT, getOnlineUsers()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.append(username + " se fue." + System.lineSeparator());
	}
	
	protected static synchronized String getOnlineUsers() { 
		return String.join(",", clients.keySet());
	}
	
	protected static synchronized boolean exists (String username) {
		return clients.containsKey(username);
	}
	
	protected static synchronized void difundir(Message msg) throws IOException {
		for (String clientName : clients.keySet()) {
			ServerThread client = clients.get(clientName);
			client.send(msg);
		}
	}
	
	protected static synchronized void enviar(Message msg) throws IOException {
		clients.get(msg.getRecipient()).send(msg);
	}
}