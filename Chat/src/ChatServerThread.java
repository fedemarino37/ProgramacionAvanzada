import java.net.*;
import java.io.*;

public class ChatServerThread extends Thread {
	private Socket socket;
	private String name; 
	
	public ChatServerThread(Socket socket) {
        super("ChatServerThread");
        this.socket = socket;
    }

	@Override
	public void run() {
		try (
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ){
			while (true) {
                out.println("SUBMITNAME");
                name = in.readLine();
                if (name == null) {
                    return;
                }
//                synchronized (ChatServer.getNames()) {
//                    if (!ChatServer.getNames().contains(name)) {
//                        ChatServer.getNames().add(name);
//                        break;
//                    }
//                }
                
                out.println("NAMEACCEPTED");
                ChatServer.getWriters().add(out);
                for (PrintWriter writer : ChatServer.getWriters()) {
                	writer.println("LOGGEDIN " + name);
                }
                
                while (true) {
                	String input = in.readLine();
                	if (input == null) return;
                	for (PrintWriter writer : ChatServer.getWriters()) {
                        writer.println("MESSAGE " + name + ": " + input);
                    }
                }
            }
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
