package ram;

import java.util.Map;
import java.util.TreeMap;

/**
 * Clase para simular una unidad de memoria que almacena registros de enteros.
 * 
 * @author Jorge González Cabrera
 */
public class UnidadMemoriaDatos {

	/**
	 * Datos que contiene el programa.
	 */
	private Map<Integer, Integer> datos;

	/**
	 * Constructor.
	 */
	public UnidadMemoriaDatos() {
		this.datos = new TreeMap<Integer, Integer>();
	}

	/**
	 * Accede al dato en una posición concreta. Si no se ha inicializado valdrá
	 * cero.
	 * 
	 * @param pos Número del registro al que se quiere acceder.
	 * @return el dato en el registro dado.
	 */
	public Integer get(Integer pos) {
		Integer resultado = datos.get(pos);
		return (resultado == null) ? 0 : resultado;
	}

	/**
	 * Cambia el valor de un registro concreto.
	 * 
	 * @param pos  Número del registro que se quiere modificar.
	 * @param dato Valor que se quiere establecer.
	 */
	public void set(Integer pos, Integer dato) {
		datos.put(pos, dato);
	}

	/**
	 * Método para formatear los datos del programa en una cadena de texto.
	 * 
	 * @return los datos formateados.
	 */
	@Override
	public String toString() {
		String resultado = "Registros: \n";
		for (Map.Entry<Integer, Integer> i : datos.entrySet()) {
			resultado += "   " + i.getKey().toString() + ": " + i.getValue().toString() + "\n";
		}

		return resultado;
	}
}
