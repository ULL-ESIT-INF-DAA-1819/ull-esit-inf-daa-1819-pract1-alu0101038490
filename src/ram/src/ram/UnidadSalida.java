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

	public void set(int valor) { 
		try {
			cintaSalida.write(String.valueOf(valor));
			cintaSalida.newLine();
		} catch (IOException e) {
			System.out.println("Ha habido un problema, con el fichero de salida.");
		}
	}

	public void cerrar() {
		try {
			cintaSalida.close();
		} catch (IOException e) {
			System.out.println("Ha habido un problema cerrando el fichero de salida.");
		}
	}
}
