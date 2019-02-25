package ram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase para simular una unidad de entrada.
 * 
 * @author Jorge González Cabrera
 */
public class UnidadEntrada {

	/**
	 * Datos presentes en la cinta de entrada.
	 */
	private ArrayList<Integer> cintaEntrada;

	/**
	 * Posición del dato preparado para ser leído.
	 */
	private int posicion;

	/**
	 * Constructor. Inicializa el atributo cintaEntrada a partir del fichero dado.
	 * 
	 * @param nombreArchivoEntrada Nombre del archivo en el que se encuentran los
	 *                             datos de la cinta de entrada.
	 *                             
	 * @exception IOException si el archivo de entrada da algún problema.
	 * @exception IllegalArgumentException si uno de los valores de la cinta no es un entero.
	 */
	public UnidadEntrada(String nombreArchivoEntrada) throws IOException {
		this.cintaEntrada = new ArrayList<Integer>();
		this.posicion = 0;

		String elementoParaParsear = "";
		try {
			String contenidoCintaEntrada = "";

			// Se convierte el archivo de entrada en una única cadena de texto.
			BufferedReader archivoEntrada = new BufferedReader(new FileReader(nombreArchivoEntrada));
			while (archivoEntrada.ready()) {
				contenidoCintaEntrada += archivoEntrada.readLine() + " ";
			}
			archivoEntrada.close();

			// Se convierte en entero cada elemento encontrado en contenidoCintaEntrada.
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
			throw new IllegalArgumentException(
					"El elemento " + elementoParaParsear + " de la cinta de entrada no es válido.");
		}
	}

	/**
	 * Accede al siguiente dato de la cinta de entrada.
	 * 
	 * @return el siguiente dato de la cinta de entrada.
	 * 
	 * @exception IllegalStateException si la cinta de entrada no tiene más valores.
	 */
	public Integer get() {
		if (posicion >= cintaEntrada.size()) {
			throw new IllegalStateException("la cinta de entrada no tiene más valores.");
		}
		posicion++;
		return cintaEntrada.get(posicion - 1);
	}

	/**
	 * Método para formatear los datos de la cinta de entrada en una cadena de
	 * texto.
	 * 
	 * @return los datos formateados.
	 */
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
