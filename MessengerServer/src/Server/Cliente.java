package Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente{

	private final Socket socket;
	private final ObjectInputStream entrada;
	private final ObjectOutputStream salida;
	
	public Cliente(String ip, Socket socket, ObjectInputStream entrada, ObjectOutputStream salida) {
		this.socket = socket;
		this.entrada = entrada;
		this.salida = salida;
	}
}
