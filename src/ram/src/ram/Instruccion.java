package ram;

/**
 * Clase para representar instrucciones ram.
 * 
 * @author Jorge González Cabrera
 */
public class Instruccion {

	/**
	 * Valor de la etiqueta. Será una cadena vacía si no la hay.
	 */
	private String etiqueta;

	/**
	 * Nombre de la instrucción. Será un valor nulo si no la hay.
	 */
	private InstruccionesValidas nombreInstruccion;

	/**
	 * Valor del operando. Será una cadena vacía si no lo hay.
	 */
	private String operando;

	/**
	 * Constructor.
	 * 
	 * @param etiqueta          Valor asignado para el atributo etiqueta. Si es nulo
	 *                          se asignará la cadena vacía.
	 * @param nombreInstruccion Valor asignado para el atributo nombreInstruccion.
	 * @param operando          Valor asignado para el atributo operando. Si es nulo
	 *                          se asignará la cadena vacía.
	 */
	public Instruccion(String etiqueta, InstruccionesValidas nombreInstruccion, String operando) {
		this.etiqueta = (etiqueta == null) ? "" : etiqueta;
		this.nombreInstruccion = nombreInstruccion;
		this.operando = (operando == null) ? "" : operando;
	}

	/**
	 * Getter del atributo etiqueta.
	 * 
	 * @return el valor actual del atributo etiqueta.
	 */
	public String getEtiqueta() {
		return etiqueta;
	}

	/**
	 * Getter del atributo nombreInstruccion.
	 * 
	 * @return el valor actual del atributo nombreInstruccion.
	 */
	public InstruccionesValidas getNombreInstruccion() {
		return nombreInstruccion;
	}

	/**
	 * Getter del atributo operando.
	 * 
	 * @return el valor actual del atributo operando.
	 */
	public String getOperando() {
		return operando;
	}

	/**
	 * Método para formatear la instrucción en una cadena de texto con la adecuada
	 * indentación.
	 * 
	 * @return la instrucción formateada.
	 */
	@Override
	public String toString() {
		String resultado = "";

		if (!etiqueta.isEmpty()) {
			resultado = etiqueta + ":\t";
			if (nombreInstruccion != null) {
				resultado += nombreInstruccion + " " + operando;
			}
		} else {
			resultado = "\t" + nombreInstruccion + " " + operando;
		}

		return resultado;
	}

}