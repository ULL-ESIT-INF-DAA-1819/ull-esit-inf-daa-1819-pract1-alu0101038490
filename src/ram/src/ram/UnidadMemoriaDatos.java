package ram;

import java.util.Map;
import java.util.TreeMap;

public class UnidadMemoriaDatos {

	private Map<Integer, Integer> datos;

	public UnidadMemoriaDatos() {
		this.datos = new TreeMap<Integer, Integer>();
	}

	public Integer get(int pos) {
		Integer resultado = datos.get(pos);
		return (resultado == null) ? 0 : resultado;
	}

	public void set(int pos, Integer dato) {
		datos.put(pos, dato);
	}

	@Override
	public String toString() {
		String resultado = "Registros: \n";
		for (Map.Entry<Integer, Integer> i : datos.entrySet()) {
			resultado += "   " + i.getKey().toString() + ": " + i.getValue().toString() + "\n";
		}
		return resultado;
	}
}
