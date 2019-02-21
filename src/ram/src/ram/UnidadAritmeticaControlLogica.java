package ram;

import ram.UnidadMemoriaInstrucciones.Instruccion;

public class UnidadAritmeticaControlLogica {

	private int ip;

	public UnidadAritmeticaControlLogica() {
		this.ip = 0;
	}

	/*
	 * private int identificarOperador(String operador, UnidadMemoriaDatos
	 * unidadMemoriaDatos) { if (operador.matches("^[1-9]*$")) { return
	 * unidadMemoriaDatos.get(Integer.valueOf(operador)); } else if
	 * (operador.matches("^=[1-9]*$")) { return Integer.valueOf(operador); } else if
	 * (operador.matches("^\\*[1-9]*$")) { return
	 * unidadMemoriaDatos.get(unidadMemoriaDatos.get(Integer.valueOf(operador.
	 * substring(1)))); } else { throw new
	 * IllegalArgumentException("linea invalida"); } }
	 */

	private void ejecutarInstruccion(Instruccion instruccion) {
		switch (instruccion.instruccion) {
			case ADD:
				break;
			case DIV:
				break;
			case HALT:
				break;
			case JGTZ:
				break;
			case JUMP:
				break;
			case JZERO:
				break;
			case LOAD:
				break;
			case MUL:
				break;
			case READ:
				break;
			case STORE:
				break;
			case SUB:
				break;
			case WRITE:
				break;
			default:
				throw new IllegalArgumentException("Instrucción no encontrada.");
		}
	}

	public void ejecutarPrograma(UnidadSalida unidadSalida, UnidadEntrada unidadEntrada,
			UnidadMemoriaDatos unidadMemoriaDatos, UnidadMemoriaInstrucciones unidadMemoriaInstrucciones) {

		while (unidadMemoriaInstrucciones.get(ip) != null) {
			ejecutarInstruccion(unidadMemoriaInstrucciones.get(ip));
		}
	}
}
