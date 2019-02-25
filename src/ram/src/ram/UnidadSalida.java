package ram;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase para simular una unidad de salida.
 * 
 * @author Jorge González Cabrera
 */
public class UnidadSalida {

	/**
	 * Datos presentes en la cinta de salida.
	 */
	private ArrayList<Integer> cintaSalida;

	/**
	 * Nombre del archivo donde volcar los datos cuando acabe el programa.
	 */
	private String nombreArchivoSalida;

	/**
	 * Constructor. Comprueba que en el archivo dado se puede escribir.
	 * 
	 * @param nombreArchivoSalida Valor asignado para el atributo
	 *                            nombreArchivoSalida.
	 * 
	 * @exception IllegalArgumentException si no se puede escribir en el archivo de
	 *                                     salida.
	 */
	public UnidadSalida(String nombreArchivoSalida) {
		File archivoSalida = new File(nombreArchivoSalida);
		if (!archivoSalida.canWrite()) {
			throw new IllegalArgumentException("Ha habido un problema con el fichero de salida.");
		}

		this.cintaSalida = new ArrayList<Integer>();
		this.nombreArchivoSalida = nombreArchivoSalida;
	}

	/**
	 * Añade un nuevo valor a la cinta de salida.
	 * 
	 * @param valor Valor que se quiere añadir a la cinta de salida.
	 */
	public void set(int valor) {
		cintaSalida.add(valor);
	}

	/**
	 * Introduce los datos de la cinta de salida en el archivo dado.
	 * 
	 * @exception IOException si el archivo de salida da algún problema.
	 */
	public void cerrar() throws IOException {
		try {
			BufferedWriter archivoSalida = new BufferedWriter(new FileWriter(nombreArchivoSalida));
			for (Integer i : cintaSalida) {
				archivoSalida.write(String.valueOf(i));
				archivoSalida.newLine();
			}
			archivoSalida.close();
		} catch (IOException e) {
			throw new IOException("Ha habido un problema con el fichero de salida.");
		}
	}

	/**
	 * Método para formatear los datos de la cinta de salida en una cadena de texto.
	 * 
	 * @return los datos formateados.
	 */
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
