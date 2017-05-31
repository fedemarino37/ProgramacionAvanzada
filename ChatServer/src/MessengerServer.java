import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MessengerServer extends Thread {

	private static Map<String, ServerThread> clients = new HashMap<String, ServerThread>();
	private static Thread server;
	private static ServerSocket serverSocket;
	protected static final int PUERTO = 9999;

	private final static int ANCHO = 700;
	private final static int ALTO = 640;
	private final static int ALTO_LOG = 520;
	private final static int ANCHO_LOG = ANCHO - 25;

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

		final JButton botonIniciar = new JButton();
		final JButton botonDetener = new JButton();
		botonIniciar.setText("Iniciar");
		botonIniciar.setBounds(220, ALTO - 70, 100, 30);
		botonIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				server = new Thread(new MessengerServer());
				server.start();
				botonIniciar.setEnabled(false);
				botonDetener.setEnabled(true);
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
				botonDetener.setEnabled(false);
				botonIniciar.setEnabled(true);
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

	private static void close() throws IOException {
		server.stop();
		for (ServerThread cliente : clients.values()) {
			cliente.close();
		}
		serverSocket.close();
	}
	
	public void run() {
		try {
			log.append("Iniciando el servidor..." + System.lineSeparator());
			serverSocket = new ServerSocket(PUERTO);
			log.append("Servidor esperando conexiones..." + System.lineSeparator());
			while (true) {
				new ServerThread(serverSocket.accept()).start();
			}
		} catch (Exception e) {
			log.append("Fallo la conexi�n." + System.lineSeparator());
			e.printStackTrace();
		}
	}
	
	protected static synchronized void addClient(String username, ServerThread serverThread) {    
    	clients.put(username, serverThread);
    }
	
	protected static synchronized String getOnlineUsers() { 
		String result = "";
		for (String username : clients.keySet()) {
			result += username + ",";
		}
		result.substring(0, result.length());
		return result;
	}
	
	protected static synchronized boolean exists (String username) {
		return clients.containsKey(username);
	}
	
	protected static synchronized void userOnline(String username) throws IOException {
		for (ServerThread client : clients.values()) {
			client.send(new Message(MessageType.LOGIN, username));
		}
	}
	
	protected static synchronized void enviarDifusion(Message msg) throws IOException {
		if (msg.getType() != MessageType.DIFUSION) return;
		for (String clientName : clients.keySet()) {
			if (clientName.equals(msg.getSender())) continue;
			ServerThread client = clients.get(clientName);
			client.send(msg);
		}
	}
	
	protected static synchronized void sendMsg(Message msg) throws IOException {
		if (msg.getType() == MessageType.MENSAJE) {
			clients.get(msg.getRecipient()).send(msg);
		}
	}
}