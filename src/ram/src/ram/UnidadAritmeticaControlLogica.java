package ram;

import java.util.Scanner;

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
		ip++;
		if (instruccion.instruccion != null) {
			switch (instruccion.instruccion) {
				case ADD:
					add(instruccion.operando);
					break;
				case DIV:
					div(instruccion.operando);
					break;
				case HALT:
					halt(instruccion.operando);
					break;
				case JGTZ:
					jgtz(instruccion.operando);
					break;
				case JUMP:
					jump(instruccion.operando);
					break;
				case JZERO:
					jzero(instruccion.operando);
					break;
				case LOAD:
					load(instruccion.operando);
					break;
				case MUL:
					mul(instruccion.operando);
					break;
				case READ:
					read(instruccion.operando);
					break;
				case STORE:
					store(instruccion.operando);
					break;
				case SUB:
					sub(instruccion.operando);
					break;
				case WRITE:
					write(instruccion.operando);
					break;
				default:
					throw new IllegalArgumentException("Instrucción no encontrada.");
			}
		}
	}

	private void add(String operando) {
		int valorRegistroCero = unidadMemoriaDatos.get(0);
		int valorParaSumar = 0;

		if (operando.matches("[0-9]+")) {
			valorParaSumar = unidadMemoriaDatos.get(Integer.valueOf(operando));
		} else if (operando.matches("=[0-9]+")) {
			valorParaSumar = Integer.valueOf(operando.substring(1));
		} else if (operando.matches("\\*[0-9]+")) {
			valorParaSumar = unidadMemoriaDatos.get(unidadMemoriaDatos.get(Integer.valueOf(operando.substring(1))));
		} else {
			throw new IllegalArgumentException("operando invalida");
		}

		unidadMemoriaDatos.set(0, valorRegistroCero + valorParaSumar);
	}

	private void div(String operando) {
		int valorRegistroCero = unidadMemoriaDatos.get(0);
		int valorParaSumar = 0;

		if (operando.matches("[0-9]+")) {
			valorParaSumar = unidadMemoriaDatos.get(Integer.valueOf(operando));
		} else if (operando.matches("=[0-9]+")) {
			valorParaSumar = Integer.valueOf(operando.substring(1));
		} else if (operando.matches("\\*[0-9]+")) {
			valorParaSumar = unidadMemoriaDatos.get(unidadMemoriaDatos.get(Integer.valueOf(operando.substring(1))));
		} else {
			throw new IllegalArgumentException("operando invalida");
		}

		unidadMemoriaDatos.set(0, valorRegistroCero / valorParaSumar);
	}

	private void mul(String operando) {
		int valorRegistroCero = unidadMemoriaDatos.get(0);
		int valorParaSumar = 0;

		if (operando.matches("[0-9]+")) {
			valorParaSumar = unidadMemoriaDatos.get(Integer.valueOf(operando));
		} else if (operando.matches("=[0-9]+")) {
			valorParaSumar = Integer.valueOf(operando.substring(1));
		} else if (operando.matches("\\*[0-9]+")) {
			valorParaSumar = unidadMemoriaDatos.get(unidadMemoriaDatos.get(Integer.valueOf(operando.substring(1))));
		} else {
			throw new IllegalArgumentException("operando invalida");
		}

		unidadMemoriaDatos.set(0, valorRegistroCero * valorParaSumar);
	}

	private void sub(String operando) {
		int valorRegistroCero = unidadMemoriaDatos.get(0);
		int valorParaSumar = 0;

		if (operando.matches("[0-9]+")) {
			valorParaSumar = unidadMemoriaDatos.get(Integer.valueOf(operando));
		} else if (operando.matches("=[0-9]+")) {
			valorParaSumar = Integer.valueOf(operando.substring(1));
		} else if (operando.matches("\\*[0-9]+")) {
			valorParaSumar = unidadMemoriaDatos.get(unidadMemoriaDatos.get(Integer.valueOf(operando.substring(1))));
		} else {
			throw new IllegalArgumentException("operando invalida");
		}

		unidadMemoriaDatos.set(0, valorRegistroCero - valorParaSumar);
	}

	private void load(String operando) {
		int valorParaCargar;

		if (operando.matches("[0-9]+")) {
			valorParaCargar = unidadMemoriaDatos.get(Integer.valueOf(operando));
		} else if (operando.matches("=[0-9]+")) {
			valorParaCargar = Integer.valueOf(operando.substring(1));
		} else if (operando.matches("\\*[0-9]+")) {
			valorParaCargar = unidadMemoriaDatos.get(unidadMemoriaDatos.get(Integer.valueOf(operando.substring(1))));
		} else {
			throw new IllegalArgumentException("operando invalida");
		}

		unidadMemoriaDatos.set(0, valorParaCargar);
	}

	private void store(String operando) {
		int registroDondeAlmacenar;
		int valorParaAlmacenar = unidadMemoriaDatos.get(0);

		if (operando.matches("[0-9]+")) {
			registroDondeAlmacenar = Integer.valueOf(operando);
		} else if (operando.matches("\\*[0-9]+")) {
			registroDondeAlmacenar = unidadMemoriaDatos.get(Integer.valueOf(operando.substring(1)));
		} else {
			throw new IllegalArgumentException("operando invalida");
		}

		unidadMemoriaDatos.set(registroDondeAlmacenar, valorParaAlmacenar);
	}

	private void jump(String operando) {
		if (operando.matches("[a-zA-Z_0-9]+")) {
			int posicionEtiqueta = unidadMemoriaInstrucciones.get(operando);
			if (posicionEtiqueta >= 0) {
				ip = posicionEtiqueta;
			} else {
				throw new IllegalArgumentException("operando invalido");
			}
		} else {
			throw new IllegalArgumentException("operando invalido");
		}
	}

	private void jzero(String operando) {
		if (operando.matches("[a-zA-Z_0-9]+")) {
			int posicionEtiqueta = unidadMemoriaInstrucciones.get(operando);
			if (posicionEtiqueta >= 0) {
				if (unidadMemoriaDatos.get(0) == 0) {
					ip = posicionEtiqueta;
				}
			} else {
				throw new IllegalArgumentException("operando invalido");
			}
		} else {
			throw new IllegalArgumentException("operando invalido");
		}
	}

	private void jgtz(String operando) {
		if (operando.matches("[a-zA-Z_0-9]+")) {
			int posicionEtiqueta = unidadMemoriaInstrucciones.get(operando);
			if (posicionEtiqueta >= 0) {
				if (unidadMemoriaDatos.get(0) > 0) {
					ip = posicionEtiqueta;
				}
			} else {
				throw new IllegalArgumentException("operando invalido");
			}
		} else {
			throw new IllegalArgumentException("operando invalido");
		}
	}

	private void read(String operando) {
		int registroDondeAlmacenar;
		int valorParaAlmacenar = unidadEntrada.get();

		if (operando.matches("[0-9]+")) {
			registroDondeAlmacenar = Integer.valueOf(operando);
		} else if (operando.matches("\\*[0-9]+")) {
			registroDondeAlmacenar = unidadMemoriaDatos.get(Integer.valueOf(operando.substring(1)));
		} else {
			throw new IllegalArgumentException("operando invalida");
		}

		unidadMemoriaDatos.set(registroDondeAlmacenar, valorParaAlmacenar);
	}

	private void write(String operando) {
		int valorParaAlmacenar;

		if (operando.matches("[0-9]+")) {
			valorParaAlmacenar = unidadMemoriaDatos.get(Integer.valueOf(operando));
		} else if (operando.matches("=[0-9]+")) {
			valorParaAlmacenar = Integer.valueOf(operando.substring(1));
		} else if (operando.matches("\\*[0-9]+")) {
			valorParaAlmacenar = unidadMemoriaDatos.get(unidadMemoriaDatos.get(Integer.valueOf(operando.substring(1))));
		} else {
			throw new IllegalArgumentException("operando invalida");
		}

		unidadSalida.set(valorParaAlmacenar);
	}

	private void halt(String operando) {
		if (operando.isEmpty()) {
			ip = unidadMemoriaInstrucciones.getUltimaInstruccion();
		} else {
			throw new IllegalArgumentException("operando invalido");
		}
	}

	public void ejecutarPrograma(boolean modoDepurador) {
		try {
			int contadorInstruccionesEjecutadas = 0;
			Scanner s = new Scanner(System.in);

			if (modoDepurador) {
				System.out.println("Estado inicial\n");
				System.out.println(toString());
			}

			while (unidadMemoriaInstrucciones.get(ip) != null && (!modoDepurador || !s.nextLine().equals("q"))) {
				ejecutarInstruccion(unidadMemoriaInstrucciones.get(ip));
				contadorInstruccionesEjecutadas++;
				if (modoDepurador) {
					System.out.println(String.valueOf(contadorInstruccionesEjecutadas) + "ª operación ejecutada\n");
					System.out.println(toString());
				}
			}

			System.out
					.println("\nOperaciones ejecutadas en total = " + String.valueOf(contadorInstruccionesEjecutadas));
			s.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			unidadSalida.cerrar();
		}
	}

	@Override
	public String toString() {
		String resultado = "Registro IP: " + String.valueOf(ip) + "\n";
		resultado += "\n" + unidadMemoriaDatos.toString();
		resultado += "\n" + unidadMemoriaInstrucciones.toString();
		resultado += "\n" + unidadEntrada.toString();
		resultado += unidadSalida.toString();
		resultado += "\n" + (new String(new char[50])).replace("\0", "-");
		return resultado;
	}

}

//TODO: el contador de instrucciones no tiene en cuenta etiquetas sueltas
//TODO: mejorar salida por pantalla
//TODO: errores con instruccion y linea donde se encuentra en el programa ram
//TODO: read/write evitan registro 0, pero y la forma indirecta?