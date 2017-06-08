import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.ImageIcon;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

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
		ImageIcon espada = new ImageIcon("./Imagenes/Espada_super_poderosa.png");
		ImageIcon casco = new ImageIcon("./Imagenes/casco.png");
		ImageIcon botas = new ImageIcon("./Imagenes/botas.png");
		ImageIcon escudo = new ImageIcon("./Imagenes/escudo.png");
		ImageIcon armadura = new ImageIcon("./Imagenes/escudo.png");
		
		
		JLabel Item1 = new JLabel(espada);
		Item1.addMouseListener(new MouseAdapter() {
			JTextField descripcion = new JTextField();
			@Override
			public void mouseExited(MouseEvent arg0) {
				descripcion.setVisible(false);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				descripcion.setText("Texto de prueba");				
				descripcion.setBounds(Item1.getX(), Item1.getY(), 10, 30);
				contentPane.add(descripcion);
				descripcion.setVisible(true);
			}
		});
		
		Item1.setBounds(10, 34, espada.getIconWidth(), espada.getIconHeight());
		contentPane.add(Item1);
		
		JLabel Item2 = new JLabel(new ImageIcon("./Imagenes/armadura.png"));
		Item2.setBounds(10, 81, armadura.getIconWidth(), armadura.getIconHeight());
		contentPane.add(Item2);
		
		JLabel Item3 = new JLabel(new ImageIcon("./Imagenes/botas.png"));
		Item3.setBounds(66, 34, botas.getIconWidth(), botas.getIconHeight());
		contentPane.add(Item3);
		
		JLabel Item4= new JLabel(new ImageIcon("./Imagenes/casco.png"));
		Item4.setBounds(122, 34, casco.getIconWidth(), casco.getIconHeight());
		contentPane.add(Item4);
		
		JLabel Item5 = new JLabel(new ImageIcon("./Imagenes/escudo.png"));
		Item5.setBounds(178, 34, escudo.getIconWidth(), escudo.getIconHeight());
		contentPane.add(Item5);
	}
}
