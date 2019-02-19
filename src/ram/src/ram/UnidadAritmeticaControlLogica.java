package ram;

import ram.*;

public class UnidadAritmeticaControlLogica {

	private final String[] instruccionesValidas = { "load", "store", "add", "sub", "mul", "div", "read", "write",
			"jump", "jzero", "jgtz", "halt" };

	private int ip;

	public UnidadAritmeticaControlLogica() {
		this.ip = 0;
	}

}
