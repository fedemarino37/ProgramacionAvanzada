import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MessengerClient extends Thread {

	private Socket mySocket;
	private EscuchaMensajes escuchaMensajes;
	private String username;
	private ArchivoDePropiedades properties;
	private ObjectInputStream in;
	private ObjectOutputStream out; 
	
	private JPanel contentPane;
	private JList<String> listUsuarios;
	private Map<String, VentanaChat> chatsAbiertos;
	private JLabel lblUsuarios;
	private JFrame VC;
	
	public static void main(String[] args) {
		MessengerClient MC = new MessengerClient();
		MC.crearVentanaCliente();
	}
	
	protected void cerrarTodo() {
		try {
			if (in != null) in.close();
			if (out != null) out.close();
			if (!mySocket.isClosed()) mySocket.close();
			if (this.escuchaMensajes != null) this.escuchaMensajes.stop();
			this.escuchaMensajes = null;
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
        		this.escuchaMensajes = new EscuchaMensajes(this, this.in);
        		this.escuchaMensajes.start();
        		this.VC.setTitle("Chat. Logeado como: " + username);
        		setUsuariosEnLista(response.getContent().split(","));
        	} else {
        		JOptionPane.showMessageDialog(VC, response.getContent());
        	}
        } while (this.username == null);
    }
	
	private String getNameDialog() {
        return JOptionPane.showInputDialog(
            VC,
            "Ingrese nombre de usuario:",
            "LOGIN",
            JOptionPane.PLAIN_MESSAGE);
    }
	
	public void crearVentanaCliente() {
		chatsAbiertos = new HashMap<String, VentanaChat>();
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
		JMenuItem mntmDesconectar_1 = new JMenuItem("Desconectar");
		mntmDesconectar_1.setEnabled(false);
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnArchivo.add(mntmConectar);
		mnArchivo.add(mntmDesconectar_1);
		mnArchivo.add(mntmSalir);
		
		mntmConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					iniciarConexion();
					mntmConectar.setEnabled(false);
					mntmDesconectar_1.setEnabled(true);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirVentanaConfirmaSalir();
			}
		});
		
		mntmDesconectar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarTodo();
				mntmConectar.setEnabled(true);
				mntmDesconectar_1.setEnabled(false);
			}
		});
		
		JMenu mnChat = new JMenu("Chat");
		menuBar.add(mnChat);
		
		JMenuItem mntmSalaDeChat = new JMenuItem("Sala de Chat");
		mntmSalaDeChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getChatWindow("SALA");
			}
		});
		mnChat.add(mntmSalaDeChat);
		
		JMenuItem mntmSesionPrivada = new JMenuItem("Sesión privada");
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
			if(this.escuchaMensajes != null) {
				cerrarTodo();
			}
			System.exit(0);
		}
	}
	
	private void iniciarConexion() throws UnknownHostException, IOException, ClassNotFoundException {
		properties = new ArchivoDePropiedades("config.properties");
		properties.lectura();
		mySocket = new Socket(properties.getIP(), properties.getPuerto());
		in = new ObjectInputStream(mySocket.getInputStream());
        out = new ObjectOutputStream(mySocket.getOutputStream());
        getUsername();
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
			getChatWindow(listUsuarios.getSelectedValue());
		else
			JOptionPane.showMessageDialog(VC, "Seleccione un elemento de la lista", "Seleccionar Usuario", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void abrirVentanaConfiguracion() {
		new VentanaConfiguracion(VC);
	}
	
	private void seleccionaDobleClickChat(MouseEvent me) {
		if(me.getClickCount() == 2)
			getChatWindow(listUsuarios.getSelectedValue());
	}
	
	private VentanaChat getChatWindow(String user) {
    	if (!chatsAbiertos.containsKey(user)) {
    		chatsAbiertos.put(user, openChatWindow(user));
    	}
    	return chatsAbiertos.get(user);
    }
	
	private VentanaChat openChatWindow(String recipient) {
		VentanaChat ventanaChat = new VentanaChat(this, recipient);
		ventanaChat.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				chatsAbiertos.remove(ventanaChat.getUsuarioDestino());
			}
		});
		ventanaChat.setVisible(true);
		return ventanaChat;
	}
	
	protected synchronized void recibirMensaje(Message msg) {
		String chatName = msg.getType().equals(MessageType.DIFUSION) ? "SALA" : msg.getSender();
		getChatWindow(chatName).recibirMensaje(msg.getSender(), msg.getContent());
	}
	
	protected synchronized void enviarMensaje(String texto, String destinatario) throws IOException {
		MessageType tipoMensaje = destinatario.equals("SALA") ? MessageType.DIFUSION : MessageType.MENSAJE;
		
		out.writeObject(new Message(tipoMensaje, this.username, texto, destinatario));
    	out.flush();
	}
}