import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaAyuda extends JFrame {

	private JPanel contentPane;

	public VentanaAyuda(JFrame ventanaPadre) {
		
		setResizable(false);
		setTitle("Acerca");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 337, 263);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Messenger 1.0");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 11, 153, 31);
		contentPane.add(lblNewLabel);
		
		String text = "<html><body>Autores:<br>Enrique Lamberto<br>Lautaro Aimar Bobadilla <br> Federico Marino Dragoset <br> Luciano Tonlorenzi <br> Mariano Gambacorta</body></html>";
		JLabel lblNewLabel_1 = new JLabel(text);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(10, 89, 137, 91);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Autores:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(10, 64, 68, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Salir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton.setBounds(118, 191, 89, 23);
		contentPane.add(btnNewButton);
		setVisible(true);
		setLocationRelativeTo(ventanaPadre);
	}
}
