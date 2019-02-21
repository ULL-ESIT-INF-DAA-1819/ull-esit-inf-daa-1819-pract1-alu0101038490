package ram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class UnidadEntrada {

	private ArrayList<Integer> cintaEntrada;
	private int posicion;

	public UnidadEntrada(String archivoEntrada) {
		cintaEntrada = new ArrayList<Integer>();
		
		String contenidoCintaEntrada = "";
		try {
			BufferedReader cintaEntrada = new BufferedReader(new FileReader(archivoEntrada));
			while (cintaEntrada.ready()) {
				contenidoCintaEntrada += cintaEntrada.readLine() + " ";
			}
			cintaEntrada.close();
		} catch (IOException e) {
			System.out.println("Ha habido un problema, con el fichero de entrada.");
		}

		String[] token = contenidoCintaEntrada.trim().split("\\s+");
		for (String s : token) {
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
}
