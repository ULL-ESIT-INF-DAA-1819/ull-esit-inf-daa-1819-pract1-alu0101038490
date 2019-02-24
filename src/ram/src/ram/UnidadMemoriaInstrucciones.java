package ram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase para simular una unidad de memoria que almacena instrucciones.
 * 
 * @author Jorge González Cabrera
 */
public class UnidadMemoriaInstrucciones {

	/**
	 * Instrucciones que contiene el programa.
	 */
	private ArrayList<Instruccion> instrucciones;

	/**
	 * Constructor. Formatea las instrucciones a partir del fichero dado,
	 * comprobando si están bien escritas.
	 * 
	 * @param archivoInstrucciones Nombre del fichero donde está escrito el
	 *                             programa.
	 */
	public UnidadMemoriaInstrucciones(String archivoInstrucciones) throws IOException {
		this.instrucciones = new ArrayList<>();

		try {
			BufferedReader bufferInstrucciones = new BufferedReader(new FileReader(archivoInstrucciones));

			while (bufferInstrucciones.ready()) {
				String instruccion = bufferInstrucciones.readLine();

				// Quita los comentarios de una instrucción recogida del fichero
				Pattern expresionRegularComentario = Pattern.compile("#");
				Matcher encuentroComentario = expresionRegularComentario.matcher(instruccion);
				if (encuentroComentario.find()) {
					instruccion = instruccion.substring(0, encuentroComentario.start());
				}

				// Separa la sentencia en palabras, evitando aquellos tokens vacíos.
				String[] tokens = instruccion.matches("\\s*") ? new String[0] : instruccion.trim().split("\\s+");

				// Obviamos las líneas vacías
				if (tokens.length != 0) {
					String etiqueta = "";
					InstruccionesValidas nombreInstruccion = null;
					String operador = "";

					if (tokens[0].matches("[a-zA-Z][a-zA-Z_0-9]*:")) {
						// Casos en los que el primer elemento es una etiqueta
						etiqueta = tokens[0].substring(0, tokens[0].length() - 1);

						if (tokens.length > 1) {
							try {
								nombreInstruccion = InstruccionesValidas.valueOf(tokens[1].toUpperCase());
							} catch (IllegalArgumentException e) {
								throw new IllegalArgumentException("Error en la instrucción " + instrucciones.size()
										+ ": '" + instruccion.replaceAll("\\s+", " ")
										+ "', el nombre de la instrucción no se ha encontrado.");
							}
							if (tokens.length > 2) {
								if (tokens.length == 3) {
									if (tokens[2].matches("([=*]?[0-9]+)|([a-zA-Z][a-zA-Z_0-9]*)")) {
										operador = tokens[2];
									} else {
										throw new IllegalArgumentException("Error en la instrucción "
												+ instrucciones.size() + ": '" + instruccion.replaceAll("\\s+", " ")
												+ "', operando no corresponde con ningún tipo aceptado.");
									}
								} else {
									throw new IllegalArgumentException("Error en la instrucción " + instrucciones.size()
											+ ": '" + instruccion.replaceAll("\\s+", " ")
											+ "', más palabras de las esperadas.");
								}
							}
						}
					} else {
						// Casos en los que no hay etiqueta
						try {
							nombreInstruccion = InstruccionesValidas.valueOf(tokens[0].toUpperCase());
						} catch (IllegalArgumentException e) {
							throw new IllegalArgumentException("Error en la instrucción " + instrucciones.size() + ": '"
									+ instruccion.replaceAll("\\s+", " ")
									+ "', el nombre de la instrucción no se ha encontrado.");
						}
						if (tokens.length > 1) {
							if (tokens.length == 2) {
								if (tokens[1].matches("([=*]?[0-9]+)|([a-zA-Z][a-zA-Z_0-9]*)")) {
									operador = tokens[1];
								} else {
									throw new IllegalArgumentException("Error en la instrucción " + instrucciones.size()
											+ ": '" + instruccion.replaceAll("\\s+", " ")
											+ "', operando no corresponde con ningún tipo aceptado.");
								}
							} else {
								throw new IllegalArgumentException("Error en la instrucción " + instrucciones.size()
										+ ": '" + instruccion.replaceAll("\\s+", " ")
										+ "', más palabras de las esperadas.");
							}
						}
					}

					instrucciones.add(new Instruccion(etiqueta, nombreInstruccion, operador));
				}
			}

			bufferInstrucciones.close();
		} catch (IOException e) {
			throw new IOException("Ha habido un problema con el fichero del programa.");
		}
	}

	/**
	 * Accede a la instrucción en una posición concreta.
	 * 
	 * @param ip Posición de la instrucción a la que se quiere acceder.
	 * @return la instrucción requerida o null si no es una posición válida.
	 */
	public Instruccion get(int ip) {
		if (ip < instrucciones.size() && ip >= 0) {
			return instrucciones.get(ip);
		} else if (ip == instrucciones.size()) {
			return null;
		}

		throw new IllegalArgumentException("ip no valida");
	}

	/**
	 * Accede a la posición de la instrucción en la que se encuentra una etiqueta.
	 * 
	 * @param etiqueta Nombre de la etiqueta que se quiere buscar.
	 * @return posición de la instrucción donde se encuentra esa etiqueta o -1 si no
	 *         se encuentra.
	 */
	public int posicionEtiqueta(String etiqueta) {
		if (etiqueta != null) {
			for (int i = 0; i < instrucciones.size(); i++) {
				if (etiqueta.equals(instrucciones.get(i).getEtiqueta())) {
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * Accede a una posición mayor a la de cualquier instrucción. De está forma si
	 * se intenta conseguir la instrucción y sale null sabremos que le programa a
	 * finalizado.
	 * 
	 * @return una posición mayor a la última instrucción.
	 */
	public int ultimaInstruccion() {
		return instrucciones.size();
	}

	/**
	 * Método para formatear las instrucciones del programa en una cadena de texto.
	 * 
	 * @return las instrucciones formateadas.
	 */
	@Override
	public String toString() {
		String resultado = "Instrucciones: \n";
		Integer contador = 0;
		for (Instruccion i : instrucciones) {
			resultado += "   " + String.valueOf(contador) + " " + i.toString() + "\n";
			contador++;
		}
		return resultado;
	}
}
