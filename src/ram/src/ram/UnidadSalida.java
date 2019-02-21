package ram;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class UnidadSalida {

	private BufferedWriter cintaSalida;

	public UnidadSalida(String nombreArchivoSalida) {
		try {
			cintaSalida = new BufferedWriter(new FileWriter(nombreArchivoSalida));
		} catch (IOException e) {
			System.out.println("Ha habido un problema, con el fichero de salida.");
		}
	}
}
