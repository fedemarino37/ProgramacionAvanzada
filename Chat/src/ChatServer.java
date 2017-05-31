import java.io.*;
import java.net.*;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ChatServer {
	private static HashSet<String> names;
	private static HashSet<PrintWriter> writers;
	protected static final int PORT = 9092;
	
	JFrame frame = new JFrame("Chatter");
	JTextArea messageArea = new JTextArea(8, 40);
	
	public ChatServer() {
		messageArea.setEditable(false);
        frame.getContentPane().add(new JScrollPane(messageArea), "Center");
        frame.pack();
	}
	
	private void log(String text) {
		messageArea.append(text + System.lineSeparator());
	}
	
	private void run() {
		log("Iniciando servidor");
		names = new HashSet<String>();
		writers = new HashSet<PrintWriter>();
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			log("Servidor esperando conexiones");
			while (true) {
				Socket socket = serverSocket.accept();
				String ipCliente = socket.getInetAddress().getHostAddress();
				log(ipCliente + " se ha conectado");
				new ChatServerThread(socket).start();
			}
		} catch (IOException e) {
			log("Exception caught when trying to listen on port " + PORT + " or listening for a connection");
            log(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		ChatServer server = new ChatServer();
        server.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        server.frame.setVisible(true);
        server.run();
	}
	
	protected static HashSet<String> getNames() {
		return names;
	}
	
	protected static HashSet<PrintWriter> getWriters() {
		return writers;
	}
}
