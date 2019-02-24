package ram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class UnidadEntrada {

	private ArrayList<Integer> cintaEntrada;
	private int posicion;

	public UnidadEntrada(String nombreArchivoEntrada) throws IOException {
		this.cintaEntrada = new ArrayList<Integer>();
		this.posicion = 0;

		String elementoParaParsear = "";
		try {
			String contenidoCintaEntrada = "";

			BufferedReader archivoEntrada = new BufferedReader(new FileReader(nombreArchivoEntrada));
			while (archivoEntrada.ready()) {
				contenidoCintaEntrada += archivoEntrada.readLine() + " ";
			}
			archivoEntrada.close();

			String[] tokens = contenidoCintaEntrada.trim().split("\\s+");
			for (String s : tokens) {
				if (!s.matches("\\s*")) {
					elementoParaParsear = s;
					this.cintaEntrada.add(Integer.valueOf(s));
				}
			}
		} catch (IOException e) {
			throw new IOException("Ha habido un problema con el fichero de entrada.");
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("El elemento " + elementoParaParsear + " de la cinta de entrada no es válido.");
		}
	}

	public Integer get() {
		if (posicion >= cintaEntrada.size()) {
			throw new IllegalStateException("la cinta de entrada no tiene más valores.");
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
