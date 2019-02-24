package ram;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UnidadSalida {

	private ArrayList<Integer> cintaSalida;
	private String nombreArchivoSalida;

	public UnidadSalida(String nombreArchivoSalida) {
		File archivoSalida = new File(nombreArchivoSalida);
		if (!archivoSalida.canWrite()) {
			throw new IllegalArgumentException("Ha habido un problema con el fichero de salida.");
		}

		this.cintaSalida = new ArrayList<Integer>();
		this.nombreArchivoSalida = nombreArchivoSalida;
	}

	public void set(int valor) {
		cintaSalida.add(valor);
	}

	public void cerrar() {
		try {
			BufferedWriter archivoSalida = new BufferedWriter(new FileWriter(nombreArchivoSalida));
			for (Integer i : cintaSalida) {
				archivoSalida.write(String.valueOf(i));
				archivoSalida.newLine();
			}
			archivoSalida.close();
		} catch (IOException e) {
			throw new IllegalArgumentException("Ha habido un problema con el fichero de salida.");
		}
	}

	@Override
	public String toString() {
		String resultado = "Cinta de salida: ";
		for (Integer i : cintaSalida) {
			resultado += i.toString() + " ";
		}
		resultado += "\n";

		return resultado;
	}
}
