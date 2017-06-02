package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.TextField;

import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class MessengerClient extends Thread {

	private static ArrayList<Cliente> clientesConectados = new ArrayList<>();
	Socket mySocket;
	String mensaje;
	private Boolean conectado = false;
	
	private JPanel contentPane;
	private JList<String> listUsuarios;
	private JLabel lblUsuarios;
	private JFrame VC;

	/*public static void main(String[] args) {
		MessengerClient MC = new MessengerClient();
		MC.crearChat();
	}*/
	
	public static void main(String[] args) {
		MessengerClient MC = new MessengerClient();
		MC.crearVentanaCliente();
	}

	public void crearChat() {
		JFrame frame = new JFrame();
		frame.setTitle("MessengerClient");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 618, 336);
		
		JMenuBar menuBar  = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnConexion = new JMenu("Conexion");
		menuBar.add(mnConexion);
		
		JMenuItem mntmConectar = new JMenuItem("Conectar");
		mntmConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mySocket = new Socket("127.0.0.1", 9999);
					conectado = true;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnConexion.add(mntmConectar);
		
		JMenuItem mntmDesconectar = new JMenuItem("Desconectar");
		mntmDesconectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(conectado) {
					try {
						mySocket.close();
						conectado  = false;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		mnConexion.add(mntmDesconectar);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{219, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel.add(scrollPane, gbc_scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		panel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] {212, 200};
		gbl_panel_1.rowHeights = new int[]{89, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 0.0};
		gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JTextField textInputField = new JTextField();
		textInputField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mensaje = textInputField.getText().trim();
				if(!mensaje.equals("")) {
					textInputField.setText("");
					textArea.append(mensaje + "\n");
				}
			}
		});
		textInputField.setColumns(10);
		GridBagConstraints gbc_textInputField = new GridBagConstraints();
		gbc_textInputField.fill = GridBagConstraints.BOTH;
		gbc_textInputField.insets = new Insets(0, 0, 0, 5);
		gbc_textInputField.gridx = 0;
		gbc_textInputField.gridy = 0;
		panel_1.add(textInputField, gbc_textInputField);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mensaje = textInputField.getText().trim();
				if(!mensaje.equals("")) {
					textInputField.setText("");
					textArea.append(mensaje + "\n");
				}
			}
		});
		GridBagConstraints gbc_btnEnviar = new GridBagConstraints();
		gbc_btnEnviar.fill = GridBagConstraints.BOTH;
		gbc_btnEnviar.gridx = 1;
		gbc_btnEnviar.gridy = 0;
		panel_1.add(btnEnviar, gbc_btnEnviar);
				
		frame.setVisible(true);
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
					mySocket = new Socket("127.0.0.1", 9999);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
		
		String str[] = {"Pepe", "Juan", "Julio", "Lucas", "Leo"}; //TODO: Estos Usuarios deberian recibirse desde el servidor por el socket.
		agregaUsuariosEnLista(str);
		
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
	
	public void agregaUsuariosEnLista(String str[]) {
		DefaultListModel<String> modeloLista = new DefaultListModel<String>();
		for(String item : str)
			modeloLista.addElement(item);
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