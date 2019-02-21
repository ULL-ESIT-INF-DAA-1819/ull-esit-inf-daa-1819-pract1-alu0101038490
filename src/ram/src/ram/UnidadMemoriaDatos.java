package ram;

import java.util.ArrayList;

public class UnidadMemoriaDatos {

	private ArrayList<Integer> datos;

	public UnidadMemoriaDatos() {
		this.datos = new ArrayList<>(20);
	}

	public Integer get(int pos) {
		return datos.get(pos);
	}
}
