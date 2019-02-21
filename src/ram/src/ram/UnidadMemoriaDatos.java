package ram;

import java.util.HashMap;
import java.util.Map;

public class UnidadMemoriaDatos {

	private Map<Integer, Integer> datos;

	public UnidadMemoriaDatos() {
		this.datos = new HashMap<Integer, Integer>();
	}

	public Integer get(int pos) {
		Integer resultado = datos.get(pos);
		return (resultado == null) ? 0 : resultado;
	}

	public void set(int pos, Integer dato) {
		datos.put(pos, dato);
	}
}
