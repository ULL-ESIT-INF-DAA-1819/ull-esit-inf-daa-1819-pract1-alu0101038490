package ram;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UnidadSalida {

	private ArrayList<Integer> cintaSalida;
	private String nombreArchivoSalida;

	public UnidadSalida(String nombreArchivoSalida) {
		this.cintaSalida = new ArrayList<Integer>();
		this.nombreArchivoSalida = nombreArchivoSalida;
	}

	public void set(int valor) {
		cintaSalida.add(valor);
	}

	public void cerrar() {
		try {
			BufferedWriter cintaSalida = new BufferedWriter(new FileWriter(nombreArchivoSalida));
			for (Integer i : this.cintaSalida) {
				cintaSalida.write(String.valueOf(i));
				cintaSalida.newLine();
			}
			cintaSalida.close();
		} catch (IOException e) {
			System.out.println("Ha habido un problema, con el fichero de salida.");
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
