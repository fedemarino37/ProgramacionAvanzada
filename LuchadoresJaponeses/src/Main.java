import java.io.FileNotFoundException;

import luchadores.*;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String path = "C:/Users/VisionStudio/Documents/Github/ProgramacionAvanzada/LuchadoresJaponeses/archivos/in.txt";
		
		Torneo torneo = new Torneo(path);
		torneo.printLuchadores();
		torneo.evaluarLuchadores();
		torneo.printDominaciones();
	}

}
