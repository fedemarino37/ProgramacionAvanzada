import java.awt.SecondaryLoop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Client {
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private String username;
	private Map<String, ClientChat> chatsAbiertos;
	JFrame frame = new JFrame("Chat");
    JTextArea messageArea = new JTextArea(8, 40);
    JList<String> usersList = new JList<String>();
    JButton btnOpenChat = new JButton("Abrir chat");
    JButton btnDifusion = new JButton("Difusión");
    
    public Client() {
    	// Layout GUI
        messageArea.setEditable(false);
        JPanel panelUsuarios = new JPanel();
        panelUsuarios.add(new JLabel("Usuarios conectados"));
        panelUsuarios.add(usersList, "South");
        frame.getContentPane().add(panelUsuarios, "Center");
        frame.getContentPane().add(btnOpenChat, "East");
        frame.getContentPane().add(messageArea, "North");
        frame.getContentPane().add(btnDifusion, "South");
        frame.getContentPane().add(new JScrollPane(usersList), "Center");
        frame.pack();
        
        chatsAbiertos = new HashMap<String, ClientChat>();
        // Add Listeners
        btnOpenChat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String recipient = (String) usersList.getSelectedValue();
				getChatWindow(recipient);
			}
		});
        btnDifusion.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		String texto = getDifusionTextDialog();
        		if (texto != null)
        			enviarDifusion(texto);
			}
        });
    }
    
    private ClientChat openChatWindow(String recipient) {
		ClientChat clientChat = new ClientChat(this, recipient);
		clientChat.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				chatsAbiertos.remove(clientChat.getRecipient());
			}
		});
		clientChat.setVisible(true);
		return clientChat;
	}
    
    private ClientChat getChatWindow(String user) {
    	if (!chatsAbiertos.containsKey(user)) {
    		chatsAbiertos.put(user, openChatWindow(user));
    	}
    	return chatsAbiertos.get(user);
    }
    
    private void handleReceivedMessage(Message msg) {
    	getChatWindow(msg.getSender()).recibirMensaje(msg.getContent());
    }
    
    protected void enviarDifusion(String text) {
    	Message msg = new Message(MessageType.DIFUSION, username, text, "Todos");
    	try {
			out.writeObject(msg);
			out.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }
    
    protected void enviarMensaje(String text, String recipient) {
    	Message msg = new Message(MessageType.MENSAJE, username, text, recipient);
    	try {
			out.writeObject(msg);
			out.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }
    
    private String getDifusionTextDialog() {
        return JOptionPane.showInputDialog(
            frame,
            "Ingrese texto a enviar:",
            "DIFUSIÓN",
            JOptionPane.OK_CANCEL_OPTION);
    }
    
    private String getNameDialog() {
        return JOptionPane.showInputDialog(
            frame,
            "Ingrese nombre de usuario:",
            "LOGIN",
            JOptionPane.PLAIN_MESSAGE);
    }
    
    private void getUsername() throws IOException, ClassNotFoundException {
    	do {
        	String user = getNameDialog();
        	out.writeObject(new Message(MessageType.LOGIN, user));
        	out.flush();
        	Message response = (Message) in.readObject();
        	if (response.getType() != MessageType.LOGIN) continue; // Lo ignoro.
        	if (response.getSuccess()) {
        		this.username = user;
        	} else {
        		JOptionPane.showMessageDialog(frame, response.getContent());
        	}
        } while (this.username == null);
    }
    
    private void getOnlineUsers() throws IOException, ClassNotFoundException {
    	out.writeObject(new Message(MessageType.USERS));
        out.flush();
        Message msgUsers = (Message) in.readObject();
        if (msgUsers.getType() == MessageType.USERS) {
        	DefaultListModel<String> userList = new DefaultListModel<String>();
        	for (String user : msgUsers.getContent().split(",")) {
        		if (!user.equals(this.username))
        			userList.addElement(user);
        	}
        	usersList.setModel(userList);
        }
    }
    
    private void run() {
        String serverAddress = "localhost";
        try (Socket socket = new Socket(serverAddress, MessengerServer.PUERTO)) {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            
            getUsername();
            this.frame.setTitle("Chat. Logeado como: " + this.username);
            getOnlineUsers();
            
            while (true) {
                Message msg = (Message) in.readObject();
                switch (msg.getType()) {
                case LOGIN:	// Alguien inició sesión
                	DefaultListModel<String> onlineUsersList = (DefaultListModel<String>) this.usersList.getModel();
                	onlineUsersList.addElement(msg.getContent());
                	this.usersList.setModel(onlineUsersList);
                	break;
                case MENSAJE: // Recibí un mensaje
                	handleReceivedMessage(msg);
                	break;
                case DIFUSION:
                	messageArea.append(msg.getSender() + " > " + msg.getContent() + "\n");
                	break;
            	default:
            		break;
                }
            }
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + serverAddress);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " + serverAddress);
			System.exit(1);
		} catch (Exception e) {
			System.err.println("Error heavy ");
			System.exit(1);
		}
    }
    
    public static void main(String[] args) {
		Client client = new Client();
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.setVisible(true);
        client.run();
	}
}
