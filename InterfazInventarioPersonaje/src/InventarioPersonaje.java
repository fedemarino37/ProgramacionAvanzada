import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.ImageIcon;

public class InventarioPersonaje extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InventarioPersonaje frame = new InventarioPersonaje();
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
	public InventarioPersonaje() {
		setResizable(false);
		setTitle("Inventario Personaje");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 230, 311);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Item1 = new JLabel(new ImageIcon("./src/Espada_super_poderosa.png"));
		
//		JPanel Item1 = new JPanel();
		Item1.setBounds(10, 34, 36, 36);
		
		contentPane.add(Item1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 81, 36, 36);
		contentPane.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 128, 36, 36);
		contentPane.add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 175, 36, 36);
		contentPane.add(panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 222, 36, 36);
		contentPane.add(panel_4);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(66, 34, 36, 36);
		contentPane.add(panel_5);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(66, 81, 36, 36);
		contentPane.add(panel_6);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(66, 128, 36, 36);
		contentPane.add(panel_7);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBounds(66, 175, 36, 36);
		contentPane.add(panel_8);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBounds(66, 222, 36, 36);
		contentPane.add(panel_9);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBounds(122, 34, 36, 36);
		contentPane.add(panel_10);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBounds(122, 81, 36, 36);
		contentPane.add(panel_11);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBounds(122, 128, 36, 36);
		contentPane.add(panel_12);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBounds(122, 175, 36, 36);
		contentPane.add(panel_13);
		
		JPanel panel_14 = new JPanel();
		panel_14.setBounds(122, 222, 36, 36);
		contentPane.add(panel_14);
		
		JPanel panel_15 = new JPanel();
		panel_15.setBounds(178, 34, 36, 36);
		contentPane.add(panel_15);
		
		JPanel panel_16 = new JPanel();
		panel_16.setBounds(178, 81, 36, 36);
		contentPane.add(panel_16);
		
		JPanel panel_17 = new JPanel();
		panel_17.setBounds(178, 128, 36, 36);
		contentPane.add(panel_17);
		
		JPanel panel_18 = new JPanel();
		panel_18.setBounds(178, 175, 36, 36);
		contentPane.add(panel_18);
		
		JPanel panel_19 = new JPanel();
		panel_19.setBounds(178, 222, 36, 36);
		contentPane.add(panel_19);
	}
}
