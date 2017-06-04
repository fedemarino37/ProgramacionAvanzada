import java.net.*;
import java.io.*;

public class ServerThread extends Thread {
	
	private Socket socket;
	private String username;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public ServerThread(Socket socket) throws IOException {
        super("ChatServerThread");
        this.socket = socket;
        this.out = new ObjectOutputStream(socket.getOutputStream());
		this.in = new ObjectInputStream(socket.getInputStream());
    }
	
	public void close() {
		try {
			this.socket.close();
			if (this.in != null) this.in.close();
			if (this.out != null) this.out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			while (true) {
                Message msg = (Message) in.readObject();
                MessengerServer.logReceivedMessage(msg);
                switch (msg.getType()) {
                	case LOGIN:
                		username = msg.getContent();
                		if (!MessengerServer.exists(username)) {
                			msg.setSuccess(true);
                			MessengerServer.addClient(username, this);
                			msg.setContent(MessengerServer.getOnlineUsers());
                			// Aviso que se conectó alguien - actualizan sus listas de usuarios
                			MessengerServer.difundir(msg);
                		} else {
                			msg.setSuccess(false);
                			msg.setContent("Nombre de usuario ya existente. Pruebe con otro");
                			out.writeObject(msg); 
                			out.flush();
                		}
                		break;
                	case MENSAJE:
                		MessengerServer.enviar(msg);
                		break;
                	case DIFUSION:
                		MessengerServer.difundir(msg);
            		default: 
            			break;
                }
			}
        }
        catch(Exception e){
        	MessengerServer.deleteClient(username);
        } finally {
        	this.close();
        }
	}
	
	protected void send(Message msg) throws IOException {
		out.writeObject(msg);
		out.flush();
		MessengerServer.logSentMessage(msg);
	}
	
	public String getUsername() {
    	return username;
    }
	
}