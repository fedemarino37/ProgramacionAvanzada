import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Secundaria extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	private String nombre;
	private String apellido;
	private Principal padre;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Secundaria frame = new Secundaria();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public Secundaria(Principal padre) {
		this.padre = padre;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(10, 72, 79, 19);
		contentPane.add(lblNewLabel);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(10, 102, 79, 19);
		contentPane.add(lblApellido);
		
		textField = new JTextField();
		textField.setBounds(99, 71, 266, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(99, 101, 266, 20);
		contentPane.add(textField_1);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nombre = textField.getText();
				apellido = textField_1.getText();
				padre.escribir();
				dispose();
			}
		});
		btnAceptar.setBounds(160, 172, 89, 23);
		contentPane.add(btnAceptar);
	}
	
	
	public String getNombreApellido() {
		return nombre + ", " + apellido;
	}
}
