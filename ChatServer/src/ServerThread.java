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
	
	public void close() throws IOException {
		this.socket.close();
		if (this.in != null) this.in.close();
		if (this.out != null) this.out.close();
	}
	
	@Override
	public void run() {
		while (true) {			
			try {
				while (true) {
	                Message msg = (Message) in.readObject();
	                switch (msg.getType()) {
	                	case LOGIN:
	                		username = msg.getContent();
	                		if (!MessengerServer.exists(username)) {
	                			msg.setSuccess(true);
	                			MessengerServer.userOnline(username); // Aviso que se conectó alguien
	                			MessengerServer.addClient(username, this);
	                		} else {
	                			msg.setSuccess(false);
	                			msg.setContent("Nombre de usuario ya existente. Pruebe con otro");
	                		}
	                		
	                		out.writeObject(msg);
	                		out.flush();
	                		break;
	                	case USERS:
	                		msg.setContent(MessengerServer.getOnlineUsers());
	                		out.writeObject(msg);
	                		out.flush();
	                		break;
	                	case MENSAJE:
	                		MessengerServer.sendMsg(msg);
	                		break;
	                	case DIFUSION:
	                		MessengerServer.enviarDifusion(msg);
	                		break;
                		default: 
                			break;
	                }
				}
            }
            catch(Exception e){  
            	System.out.println(" ERROR reading: " + e.getMessage());
            }
        }
	}
	
	protected void send(Message msg) throws IOException {
		out.writeObject(msg);
	}
	
	public String getUsername() {
    	return username;
    }
	
}