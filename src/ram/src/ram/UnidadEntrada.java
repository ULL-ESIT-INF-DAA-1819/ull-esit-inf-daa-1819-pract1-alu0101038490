package ram;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class UnidadEntrada {

	private BufferedReader cintaEntrada;

	public UnidadEntrada(String archivoEntrada) {
		try {
			cintaEntrada = new BufferedReader(new FileReader(archivoEntrada));
		} catch (FileNotFoundException e) {
			System.out.println("Ha habido un problema, con el fichero de entrada.");
		}
	}
}
