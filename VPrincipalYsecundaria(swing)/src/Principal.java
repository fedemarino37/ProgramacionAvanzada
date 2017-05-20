import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private Secundaria sec;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(104, 76, 215, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		sec = new Secundaria(this);
		JButton btnFormulario = new JButton("Formulario");
		btnFormulario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
				sec.setVisible(true);
			}
		});
		btnFormulario.setBounds(151, 145, 121, 23);
		contentPane.add(btnFormulario);
	}
	
	public void escribir(){
		textField.setText(sec.getNombreApellido());
	}

}
