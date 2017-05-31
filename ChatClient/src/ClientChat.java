import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ClientChat extends JFrame {

    private static final long serialVersionUID = 4280526833873026237L;
	private JTextArea textArea;
	private JTextField textField;
	private String recipient;
	
	public ClientChat(final Client principal, final String recipient) {
		setTitle("Chat con " + recipient);
		this.recipient = recipient;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		textArea = new JTextArea(8, 40);
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.append("YO > " + textField.getText() + "\n");
				principal.enviarMensaje(textField.getText(), recipient);
				textField.setText("");
			}
		});
		
		this.getContentPane().add(textField, "South");
		this.getContentPane().add(textArea, "North");
		this.getContentPane().add(new JScrollPane(textArea), "Center");
	}
	
	protected String getRecipient() {
		return this.recipient;
	}
	
	protected void recibirMensaje(String mensaje) {
		textArea.append(recipient + " > " + mensaje + "\n");
	}
}