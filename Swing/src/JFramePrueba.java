import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

public class JFramePrueba extends JFrame {

	private JPanel contentPane;
	Integer contador = 0;
	private JTextField txtIncremento;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFramePrueba frame = new JFramePrueba();
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
	public JFramePrueba() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel labelPrueba = new JLabel("mensaje");
		labelPrueba.setBounds(163, 91, 113, 69);
		contentPane.add(labelPrueba);
		
		JButton btnNewButton = new JButton("Clicks");
		btnNewButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					contador += 100;
					labelPrueba.setText(contador.toString());
				}
					
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contador++;
				labelPrueba.setText(contador.toString());
			}
		});
		btnNewButton.setBounds(165, 227, 89, 23);
		contentPane.add(btnNewButton);
		
		txtIncremento = new JTextField();
		txtIncremento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(arg0.getKeyChar() == 'd' || arg0.getKeyChar() == 'D') {
					contador = 0;
					labelPrueba.setText(contador.toString());
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyChar() == 'i' || arg0.getKeyChar() == 'I') {
					contador += 1000;
					labelPrueba.setText(contador.toString());
				}
			}
		});
		txtIncremento.setBounds(168, 162, 86, 20);
		contentPane.add(txtIncremento);
		txtIncremento.setColumns(10);
		
		
	}
}
