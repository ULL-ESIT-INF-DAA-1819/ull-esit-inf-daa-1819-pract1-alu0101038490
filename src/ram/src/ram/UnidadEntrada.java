package ram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class UnidadEntrada {

	private ArrayList<Integer> cintaEntrada;
	private int posicion;

	public UnidadEntrada(String nombreArchivoEntrada) {
		cintaEntrada = new ArrayList<Integer>();
		posicion = 0;

		String contenidoCintaEntrada = "";
		try {
			BufferedReader archivoEntrada = new BufferedReader(new FileReader(nombreArchivoEntrada));
			while (archivoEntrada.ready()) {
				contenidoCintaEntrada += archivoEntrada.readLine() + " ";
			}
			archivoEntrada.close();
		} catch (IOException e) {
			System.out.println("Ha habido un problema con el fichero de entrada.");
		}

		String[] tokens = contenidoCintaEntrada.trim().split("\\s+");
		for (String s : tokens) {
			if (!s.matches("\\s*")) {
				cintaEntrada.add(Integer.valueOf(s));
			}
		}
	}

	public Integer get() {
		if (posicion >= cintaEntrada.size()) {
			throw new IllegalStateException("La cinta de entrada no tiene más valores.");
		}
		posicion++;
		return cintaEntrada.get(posicion - 1);
	}

	@Override
	public String toString() {
		String resultado = "Cinta de entrada: ";
		for (int i = 0; i < cintaEntrada.size(); i++) {
			resultado += (i == posicion) ? "*" : "";
			resultado += cintaEntrada.get(i).toString() + " ";
		}
		resultado += "\n";
		return resultado;
	}
}
