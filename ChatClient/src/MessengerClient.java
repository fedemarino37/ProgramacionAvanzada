import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

public class MessengerClient extends Thread {

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private String username;
	private Map<String, ClientChat> chatsAbiertos;

	public static void main(String[] args) {
		MessengerClient MC = new MessengerClient();
		MC.crearChat();
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
					socket = new Socket("localhost", MessengerServer.PUERTO);
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
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
				String mensaje = textInputField.getText().trim();
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
				String mensaje = textInputField.getText().trim();
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

}