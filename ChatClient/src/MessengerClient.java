import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class MessengerClient extends Thread {

	private static ArrayList<Client> clientesConectados = new ArrayList<>();
	private Socket mySocket;
	private String mensaje;
	private Boolean conectado = false;
	private String username;
	private ArchivoDePropiedades properties;
	private ObjectInputStream in;
	private ObjectOutputStream out; 
	
	private JPanel contentPane;
	private JList<String> listUsuarios;
	private JLabel lblUsuarios;
	private JFrame VC;
	
	public static void main(String[] args) {
		MessengerClient MC = new MessengerClient();
		MC.crearVentanaCliente();
	}

	
	private void cerrarTodo() {
		try {
			if (in != null) in.close();
			if (out != null) out.close();
			if (!mySocket.isClosed()) mySocket.close();
		} catch (Exception e){
			e.printStackTrace();
		}
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
        		JOptionPane.showMessageDialog(VC, response.getContent());
        	}
        } while (this.username == null);
    }
	
	private void getOnlineUsers() throws IOException, ClassNotFoundException {
    	out.writeObject(new Message(MessageType.USERS));
        out.flush();
        Message msgUsers = (Message) in.readObject();
        if (msgUsers.getType() == MessageType.USERS) {
        	setUsuariosEnLista(msgUsers.getContent().split(","));
        }
    }
	
	private String getNameDialog() {
        return JOptionPane.showInputDialog(
            VC,
            "Ingrese nombre de usuario:",
            "LOGIN",
            JOptionPane.PLAIN_MESSAGE);
    }


	public void crearVentanaCliente() {
		VC = new JFrame();
		VC.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				abrirVentanaConfirmaSalir();
			}
		});
		
		try
		{
		   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
		   e.printStackTrace();
		}
		
		VC.setTitle("Chat");
		VC.setResizable(false);
		VC.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		VC.setBounds(100, 100, 379, 526);
		
		JMenuBar menuBar = new JMenuBar();
		VC.setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmConectar = new JMenuItem("Conectar");
		mntmConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					properties = new ArchivoDePropiedades("config.properties");
					properties.lectura();
					mySocket = new Socket(properties.getIP(), properties.getPuerto());
					conectado = true;
					in = new ObjectInputStream(mySocket.getInputStream());
		            out = new ObjectOutputStream(mySocket.getOutputStream());
		            getUsername();
		            VC.setTitle("Chat. Logeado como: " + username);
		            getOnlineUsers();
		            while (true) {
		                Message msg = (Message) in.readObject();
		                switch (msg.getType()) {
		                case LOGIN:	// Alguien inició sesión
		                	agregarUsuarioEnLista(msg.getContent());
		                	break;
		                case MENSAJE: // Recibí un mensaje
//		                	handleReceivedMessage(msg);
		                	break;
		                case DIFUSION:
//		                	messageArea.append(msg.getSender() + " > " + msg.getContent() + "\n");
		                	break;
		            	default:
		            		break;
		                }
		            }
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		mnArchivo.add(mntmConectar);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirVentanaConfirmaSalir();
			}
		});
		
		JMenuItem mntmDesconectar_1 = new JMenuItem("Desconectar");
		mntmDesconectar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mySocket.close();
				} catch (IOException f) {
					// TODO Auto-generated catch block
					f.printStackTrace();
				}
			}
		});
		mnArchivo.add(mntmDesconectar_1);
		mnArchivo.add(mntmSalir);
		
		JMenu mnChat = new JMenu("Chat");
		menuBar.add(mnChat);
		
		JMenuItem mntmSalaDeChat = new JMenuItem("Sala de Chat");
		mntmSalaDeChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new VentanaChat("", "Sala");
			}
		});
		mnChat.add(mntmSalaDeChat);
		
		JMenuItem mntmSesionPrivada = new JMenuItem("Sesi�n privada");
		mntmSesionPrivada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seleccionarElementoLista();
			}
		});
		mnChat.add(mntmSesionPrivada);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		JMenuItem mntmConfigIpPuerto = new JMenuItem("Configurar IP-Puerto");
		mntmConfigIpPuerto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaConfiguracion();
			}
		});
		mnAyuda.add(mntmConfigIpPuerto);
		
		JMenuItem mntmAcerca = new JMenuItem("Acerca");
		mnAyuda.add(mntmAcerca);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		VC.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 0, 373, 462);
		contentPane.add(scrollPane);
		
		listUsuarios = new JList<String>();
		listUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				seleccionaDobleClickChat(arg0);
			}
		});
		scrollPane.setViewportView(listUsuarios);

		lblUsuarios = new JLabel("Cantidad de Usuarios conectados: ");
		lblUsuarios.setBounds(0, 464, 373, 14);
		contentPane.add(lblUsuarios);
		
		VC.setVisible(true);
	}
	
	private void abrirVentanaConfirmaSalir() {
		int opcion = JOptionPane.showConfirmDialog(VC, "Desea salir del Chat", "Confirmación", JOptionPane.YES_NO_OPTION);
		if(opcion == JOptionPane.YES_OPTION) {
			if(conectado) {
				try {
					mySocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.exit(0);
				}
			}
			System.exit(0);
		}
	}
	
	public void agregarUsuarioEnLista(String user) {
		DefaultListModel<String> onlineUsersList = (DefaultListModel<String>) this.listUsuarios.getModel();
    	onlineUsersList.addElement(user);
    	listUsuarios.setModel(onlineUsersList);
	}
	
	public void setUsuariosEnLista(String str[]) {
		DefaultListModel<String> modeloLista = new DefaultListModel<String>();
		for(String item : str) {
    		if (!item.equals(this.username))
    			modeloLista.addElement(item);
    	}
		listUsuarios.setModel(modeloLista);
		lblUsuarios.setText("Cantidad de Usuarios Conectados: " + modeloLista.getSize());
	}
	
	private void seleccionarElementoLista() {
		if(!listUsuarios.isSelectionEmpty())
			new VentanaChat(listUsuarios.getSelectedValue(), "Chat");
		else
			JOptionPane.showMessageDialog(VC, "Seleccione un elemento de la lista", "Seleccionar Usuario", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void abrirVentanaConfiguracion() {
		new VentanaConfiguracion(VC);
	}
	
	private void seleccionaDobleClickChat(MouseEvent me) {
		if(me.getClickCount() == 2)
			new VentanaChat(listUsuarios.getSelectedValue(), "Chat");
	}
}