package ram;

import ram.UnidadMemoriaInstrucciones.Instruccion;

public class UnidadAritmeticaControlLogica {

	private int ip;
	private UnidadSalida unidadSalida;
	private UnidadEntrada unidadEntrada;
	private UnidadMemoriaDatos unidadMemoriaDatos;
	private UnidadMemoriaInstrucciones unidadMemoriaInstrucciones;

	public UnidadAritmeticaControlLogica(String archivoInstrucciones, String archivoEntrada, String archivoSalida) {
		this.ip = 0;
		this.unidadEntrada = new UnidadEntrada(archivoEntrada);
		this.unidadSalida = new UnidadSalida(archivoSalida);
		this.unidadMemoriaInstrucciones = new UnidadMemoriaInstrucciones(archivoInstrucciones);
		this.unidadMemoriaDatos = new UnidadMemoriaDatos();
	}

	private void ejecutarInstruccion(Instruccion instruccion) {
		System.out.println(instruccion.etiqueta + " " + instruccion.instruccion.name() + " " + instruccion.operando);
		switch (instruccion.instruccion) {
			case ADD:
				add(instruccion);
				break;
			case DIV:
				div(instruccion);
				break;
			case HALT:
				halt(instruccion);
				break;
			case JGTZ:
				jgtz(instruccion);
				break;
			case JUMP:
				jump(instruccion);
				break;
			case JZERO:
				jzero(instruccion);
				break;
			case LOAD:
				load(instruccion);
				break;
			case MUL:
				mul(instruccion);
				break;
			case READ:
				read(instruccion);
				break;
			case STORE:
				store(instruccion);
				break;
			case SUB:
				sub(instruccion);
				break;
			case WRITE:
				write(instruccion);
				break;
			default:
				throw new IllegalArgumentException("Instrucción no encontrada.");
		}
	}

	/*
	 * if (instruccion.operando.matches("[0-9]+")) {
	 * unidadMemoriaDatos.get(Integer.valueOf(instruccion.operando)) } else if
	 * (instruccion.operando.matches("=[0-9]+")) {
	 * Integer.valueOf(instruccion.operando) } else if
	 * (instruccion.operando.matches("\\*[0-9]+")) {
	 * unidadMemoriaDatos.get(unidadMemoriaDatos.get(Integer.valueOf(instruccion.
	 * operando.substring(1)))) } else if (instruccion.operando == null) {
	 * 
	 * } else if (instruccion.operando.matches("[a-zA-Z_0-9]+")) {
	 * 
	 * } else { throw new IllegalArgumentException("operando invalid0"); }
	 */

	private void add(Instruccion instruccion) {
		int valorRegistroCero = unidadMemoriaDatos.get(0);
		int valorParaSumar = 0;

		if (instruccion.operando.matches("[0-9]+")) {
			valorParaSumar = unidadMemoriaDatos.get(Integer.valueOf(instruccion.operando));
		} else if (instruccion.operando.matches("=[0-9]+")) {
			valorParaSumar = Integer.valueOf(instruccion.operando);
		} else if (instruccion.operando.matches("\\*[0-9]+")) {
			valorParaSumar = unidadMemoriaDatos
					.get(unidadMemoriaDatos.get(Integer.valueOf(instruccion.operando.substring(1))));
		} else {
			throw new IllegalArgumentException("operando invalida");
		}

		unidadMemoriaDatos.set(0, valorRegistroCero + valorParaSumar);
		ip++;
	}

	private void div(Instruccion instruccion) {
		int valorRegistroCero = unidadMemoriaDatos.get(0);
		int valorParaSumar = 0;

		if (instruccion.operando.matches("[0-9]+")) {
			valorParaSumar = unidadMemoriaDatos.get(Integer.valueOf(instruccion.operando));
		} else if (instruccion.operando.matches("=[0-9]+")) {
			valorParaSumar = Integer.valueOf(instruccion.operando);
		} else if (instruccion.operando.matches("\\*[0-9]+")) {
			valorParaSumar = unidadMemoriaDatos
					.get(unidadMemoriaDatos.get(Integer.valueOf(instruccion.operando.substring(1))));
		} else {
			throw new IllegalArgumentException("operando invalida");
		}

		unidadMemoriaDatos.set(0, valorRegistroCero / valorParaSumar);
		ip++;
	}

	private void mul(Instruccion instruccion) {
		int valorRegistroCero = unidadMemoriaDatos.get(0);
		int valorParaSumar = 0;

		if (instruccion.operando.matches("[0-9]+")) {
			valorParaSumar = unidadMemoriaDatos.get(Integer.valueOf(instruccion.operando));
		} else if (instruccion.operando.matches("=[0-9]+")) {
			valorParaSumar = Integer.valueOf(instruccion.operando);
		} else if (instruccion.operando.matches("\\*[0-9]+")) {
			valorParaSumar = unidadMemoriaDatos
					.get(unidadMemoriaDatos.get(Integer.valueOf(instruccion.operando.substring(1))));
		} else {
			throw new IllegalArgumentException("operando invalida");
		}

		unidadMemoriaDatos.set(0, valorRegistroCero * valorParaSumar);
		ip++;
	}

	private void sub(Instruccion instruccion) {
		int valorRegistroCero = unidadMemoriaDatos.get(0);
		int valorParaSumar = 0;

		if (instruccion.operando.matches("[0-9]+")) {
			valorParaSumar = unidadMemoriaDatos.get(Integer.valueOf(instruccion.operando));
		} else if (instruccion.operando.matches("=[0-9]+")) {
			valorParaSumar = Integer.valueOf(instruccion.operando.substring(1));
		} else if (instruccion.operando.matches("\\*[0-9]+")) {
			valorParaSumar = unidadMemoriaDatos
					.get(unidadMemoriaDatos.get(Integer.valueOf(instruccion.operando.substring(1))));
		} else {
			throw new IllegalArgumentException("operando invalida");
		}

		unidadMemoriaDatos.set(0, valorRegistroCero - valorParaSumar);
		ip++;
	}

	private void load(Instruccion instruccion) {
		int valorParaCargar;

		if (instruccion.operando.matches("[0-9]+")) {
			valorParaCargar = unidadMemoriaDatos.get(Integer.valueOf(instruccion.operando));
		} else if (instruccion.operando.matches("=[0-9]+")) {
			valorParaCargar = Integer.valueOf(instruccion.operando.substring(1)); 
		} else if (instruccion.operando.matches("\\*[0-9]+")) {
			valorParaCargar = unidadMemoriaDatos
					.get(unidadMemoriaDatos.get(Integer.valueOf(instruccion.operando.substring(1))));
		} else {
			throw new IllegalArgumentException("operando invalida");
		}

		unidadMemoriaDatos.set(0, valorParaCargar);
		ip++;
	}

	private void store(Instruccion instruccion) {
		int registroDondeAlmacenar;
		int valorParaAlmacenar = unidadMemoriaDatos.get(0);

		if (instruccion.operando.matches("[0-9]+")) {
			registroDondeAlmacenar = Integer.valueOf(instruccion.operando);
		} else if (instruccion.operando.matches("\\*[0-9]+")) {
			registroDondeAlmacenar = unidadMemoriaDatos.get(Integer.valueOf(instruccion.operando.substring(1)));
		} else {
			throw new IllegalArgumentException("operando invalida");
		}

		unidadMemoriaDatos.set(registroDondeAlmacenar, valorParaAlmacenar);
		ip++;
	}

	private void jump(Instruccion instruccion) {
		if (instruccion.operando.matches("[a-zA-Z_0-9]+")) {
			ip = unidadMemoriaInstrucciones.get(instruccion.operando);
		} else {
			throw new IllegalArgumentException("operando invalido");
		}
	}

	private void jzero(Instruccion instruccion) {
		if (instruccion.operando.matches("[a-zA-Z_0-9]+")) {
			ip = (unidadMemoriaDatos.get(0) == 0) ? unidadMemoriaInstrucciones.get(instruccion.operando) : (ip + 1);
		} else {
			throw new IllegalArgumentException("operando invalido");
		}
	}

	private void jgtz(Instruccion instruccion) {
		if (instruccion.operando.matches("[a-zA-Z_0-9]+")) {
			ip = (unidadMemoriaDatos.get(0) > 0) ? unidadMemoriaInstrucciones.get(instruccion.operando) : (ip + 1);
		} else {
			throw new IllegalArgumentException("operando invalido");
		}
	}

	private void read(Instruccion instruccion) {
		int registroDondeAlmacenar;
		int valorParaAlmacenar = unidadEntrada.get();

		if (instruccion.operando.matches("[0-9]+")) {
			registroDondeAlmacenar = Integer.valueOf(instruccion.operando);
		} else if (instruccion.operando.matches("\\*[0-9]+")) {
			registroDondeAlmacenar = unidadMemoriaDatos.get(Integer.valueOf(instruccion.operando.substring(1)));
		} else {
			throw new IllegalArgumentException("operando invalida");
		}

		unidadMemoriaDatos.set(registroDondeAlmacenar, valorParaAlmacenar);
		ip++;
	}

	private void write(Instruccion instruccion) {
		int valorParaAlmacenar;
		
		if (instruccion.operando.matches("[0-9]+")) {
			valorParaAlmacenar = unidadMemoriaDatos.get(Integer.valueOf(instruccion.operando));
		} else if (instruccion.operando.matches("=[0-9]+")) {
			valorParaAlmacenar = Integer.valueOf(instruccion.operando.substring(1));
		} else if (instruccion.operando.matches("\\*[0-9]+")) {
			valorParaAlmacenar = unidadMemoriaDatos
					.get(unidadMemoriaDatos.get(Integer.valueOf(instruccion.operando.substring(1))));
		} else {
			throw new IllegalArgumentException("operando invalida");
		}
		
		unidadSalida.set(valorParaAlmacenar);
		ip++;
	}
	
	private void halt(Instruccion instruccion) {
		if (instruccion.operando == null) {
			ip = unidadMemoriaInstrucciones.getUltimaInstruccion();
		} else {
			throw new IllegalArgumentException("operando invalido");
		}
	}

	public void ejecutarPrograma() {
		while (unidadMemoriaInstrucciones.get(ip) != null) {
			ejecutarInstruccion(unidadMemoriaInstrucciones.get(ip));
		}
		unidadSalida.cerrar();
	}
}

//TODO: poner ip en otros lados
//TODO: funcion para optener operador
//TODO: agrupar instrucciones
//TODO: tener cuidado con operadores nulos
//TODO: cambiar instruccion por solo operador?
//TODO: acabar programa
//TODO: instrucciones sin instruccion, solo con etiqueta
//TODO: comprobacion de que la posicion de la etiqueta es correcta