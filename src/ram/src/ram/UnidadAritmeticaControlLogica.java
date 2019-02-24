package ram;

import java.io.IOException;
import java.util.Scanner;

/**
 * Clase para simular una unidad aritmética, control y lógica.
 * 
 * @author Jorge González Cabrera
 */
public class UnidadAritmeticaControlLogica {

	/**
	 * Registro para guardar el número de la siguiente instrucción a ejecutar.
	 */
	private int ip;

	/**
	 * Unidad de salida en la que se escribirán los datos de salida.
	 */
	private UnidadSalida unidadSalida;

	/**
	 * Unidad de entrada de la que se recibirán los datos de entrada.
	 */
	private UnidadEntrada unidadEntrada;

	/**
	 * Unidad de memoria de datos con la que manipular los registros.
	 */
	private UnidadMemoriaDatos unidadMemoriaDatos;

	/**
	 * Unidad de memoria de instrucciones en la que se sitúan las instrucciones a
	 * ejecutar.
	 */
	private UnidadMemoriaInstrucciones unidadMemoriaInstrucciones;

	/**
	 * Constructor.
	 * 
	 * @param archivoInstrucciones Nombre del archivo del programa para inicializar
	 *                             el atributo unidadMemoriaInstrucciones.
	 * @param archivoEntrada       Nombre del archivo de entrada para inicializar el
	 *                             atributo unidadEntrada.
	 * @param archivoSalida        Nombre del archivo de salida para inicializar el
	 *                             atributo unidadSalida.
	 */
	public UnidadAritmeticaControlLogica(String archivoInstrucciones, String archivoEntrada, String archivoSalida)
			throws IOException {
		this.ip = 0;
		this.unidadEntrada = new UnidadEntrada(archivoEntrada);
		this.unidadSalida = new UnidadSalida(archivoSalida);
		this.unidadMemoriaInstrucciones = new UnidadMemoriaInstrucciones(archivoInstrucciones);
		this.unidadMemoriaDatos = new UnidadMemoriaDatos();
	}

	/**
	 * Se ejecuta el programa almacenado. Puede ir paso a paso mostrando una traza o
	 * ejecutarse directamente por completo.
	 * 
	 * @param modoDepurador True para el mostrar la traza paso a paso.
	 */
	public void ejecutarPrograma(boolean modoDepurador) {
		int ultimaIpEjecutada = 0;

		try {
			int contadorInstruccionesEjecutadas = 0;
			Scanner input = new Scanner(System.in);

			if (modoDepurador) {
				System.out.println("Estado inicial\n");
				System.out.println(toString());
			}

			while (unidadMemoriaInstrucciones.get(ip) != null && (!modoDepurador || !input.nextLine().equals("q"))) {
				Instruccion instruccion = unidadMemoriaInstrucciones.get(ip);
				ultimaIpEjecutada = ip;
				ejecutarInstruccion(instruccion);

				if (instruccion.getNombreInstruccion() != null) {
					contadorInstruccionesEjecutadas++;
				}

				if (modoDepurador) {
					System.out.println(String.valueOf(contadorInstruccionesEjecutadas) + "ª operación ejecutada\n");
					System.out.println(toString());
				}
			}

			System.out
					.println("\nOperaciones ejecutadas en total = " + String.valueOf(contadorInstruccionesEjecutadas));
			input.close();
			unidadSalida.cerrar();
		} catch (Exception e) {
			unidadSalida.cerrar();
			System.out.println("Error en la instrucción " + ultimaIpEjecutada + ": '"
					+ unidadMemoriaInstrucciones.get(ultimaIpEjecutada).toString().replaceAll("\\s+", " ") + "', "
					+ e.getMessage());
		}
	}

	/**
	 * Maneja el registro ip y decide que ejecutar con la instrucción dada.
	 * 
	 * @param instruccion Instrucción que se desea ejecutar.
	 */
	private void ejecutarInstruccion(Instruccion instruccion) {
		ip++;
		if (instruccion.getNombreInstruccion() != null) {
			switch (instruccion.getNombreInstruccion()) {
				case ADD:
					add(instruccion.getOperando());
					break;
				case DIV:
					div(instruccion.getOperando());
					break;
				case HALT:
					halt(instruccion.getOperando());
					break;
				case JGTZ:
					jgtz(instruccion.getOperando());
					break;
				case JUMP:
					jump(instruccion.getOperando());
					break;
				case JZERO:
					jzero(instruccion.getOperando());
					break;
				case LOAD:
					load(instruccion.getOperando());
					break;
				case MUL:
					mul(instruccion.getOperando());
					break;
				case READ:
					read(instruccion.getOperando());
					break;
				case STORE:
					store(instruccion.getOperando());
					break;
				case SUB:
					sub(instruccion.getOperando());
					break;
				case WRITE:
					write(instruccion.getOperando());
					break;
				default:
					throw new IllegalArgumentException("nombre de instrucción no encontrada");
			}
		}
	}

	/**
	 * El operando se suma a R0 y el resultado se almacena en R0. Comprueba si el
	 * operando es válido.
	 * 
	 * @param operando Operando que utilizará la instrucción.
	 */
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
			throw new IllegalArgumentException("operando no válido para esta instrucción");
		}

		unidadMemoriaDatos.set(0, valorRegistroCero + valorParaSumar);
	}

	/**
	 * El operando divide a R0 y el resultado se almacena en R0. Comprueba si el
	 * operando es válido.
	 * 
	 * @param operando Operando que utilizará la instrucción.
	 */
	private void div(String operando) {
		int valorRegistroCero = unidadMemoriaDatos.get(0);
		int divisor = 0;

		if (operando.matches("[0-9]+")) {
			divisor = unidadMemoriaDatos.get(Integer.valueOf(operando));
		} else if (operando.matches("=[0-9]+")) {
			divisor = Integer.valueOf(operando.substring(1));
		} else if (operando.matches("\\*[0-9]+")) {
			divisor = unidadMemoriaDatos.get(unidadMemoriaDatos.get(Integer.valueOf(operando.substring(1))));
		} else {
			throw new IllegalArgumentException("operando no válido para esta instrucción");
		}

		unidadMemoriaDatos.set(0, valorRegistroCero / divisor);
	}

	/**
	 * El operando multiplica a R0 y el resultado se almacena en R0. Comprueba si el
	 * operando es válido.
	 * 
	 * @param operando Operando que utilizará la instrucción.
	 */
	private void mul(String operando) {
		int valorRegistroCero = unidadMemoriaDatos.get(0);
		int valorParaMultiplicar = 0;

		if (operando.matches("[0-9]+")) {
			valorParaMultiplicar = unidadMemoriaDatos.get(Integer.valueOf(operando));
		} else if (operando.matches("=[0-9]+")) {
			valorParaMultiplicar = Integer.valueOf(operando.substring(1));
		} else if (operando.matches("\\*[0-9]+")) {
			valorParaMultiplicar = unidadMemoriaDatos
					.get(unidadMemoriaDatos.get(Integer.valueOf(operando.substring(1))));
		} else {
			throw new IllegalArgumentException("operando no válido para esta instrucción");
		}

		unidadMemoriaDatos.set(0, valorRegistroCero * valorParaMultiplicar);
	}

	/**
	 * El operando se resta a R0 y el resultado se almacena en R0. Comprueba si el
	 * operando es válido.
	 * 
	 * @param operando Operando que utilizará la instrucción.
	 */
	private void sub(String operando) {
		int valorRegistroCero = unidadMemoriaDatos.get(0);
		int valorParaRestar = 0;

		if (operando.matches("[0-9]+")) {
			valorParaRestar = unidadMemoriaDatos.get(Integer.valueOf(operando));
		} else if (operando.matches("=[0-9]+")) {
			valorParaRestar = Integer.valueOf(operando.substring(1));
		} else if (operando.matches("\\*[0-9]+")) {
			valorParaRestar = unidadMemoriaDatos.get(unidadMemoriaDatos.get(Integer.valueOf(operando.substring(1))));
		} else {
			throw new IllegalArgumentException("operando no válido para esta instrucción");
		}

		unidadMemoriaDatos.set(0, valorRegistroCero - valorParaRestar);
	}

	/**
	 * El operando se carga en R0. Comprueba si el operando es válido.
	 * 
	 * @param operando Operando que utilizará la instrucción.
	 */
	private void load(String operando) {
		int valorParaCargar;

		if (operando.matches("[0-9]+")) {
			valorParaCargar = unidadMemoriaDatos.get(Integer.valueOf(operando));
		} else if (operando.matches("=[0-9]+")) {
			valorParaCargar = Integer.valueOf(operando.substring(1));
		} else if (operando.matches("\\*[0-9]+")) {
			valorParaCargar = unidadMemoriaDatos.get(unidadMemoriaDatos.get(Integer.valueOf(operando.substring(1))));
		} else {
			throw new IllegalArgumentException("operando no válido para esta instrucción");
		}

		unidadMemoriaDatos.set(0, valorParaCargar);
	}

	/**
	 * El contenido de R0 se almacena en la memoria seg´un el operando. Comprueba si
	 * el operando es válido.
	 * 
	 * @param operando Operando que utilizará la instrucción.
	 */
	private void store(String operando) {
		int registroDondeAlmacenar;
		int valorParaAlmacenar = unidadMemoriaDatos.get(0);

		if (operando.matches("[0-9]+")) {
			registroDondeAlmacenar = Integer.valueOf(operando);
		} else if (operando.matches("\\*[0-9]+")) {
			registroDondeAlmacenar = unidadMemoriaDatos.get(Integer.valueOf(operando.substring(1)));
		} else {
			throw new IllegalArgumentException("operando no válido para esta instrucción");
		}

		unidadMemoriaDatos.set(registroDondeAlmacenar, valorParaAlmacenar);
	}

	/**
	 * El valor del registro IP se modifica para apuntar a la instrucción
	 * identificada por la etiqueta. Comprueba si el operando es válido.
	 * 
	 * @param operando Operando que utilizará la instrucción.
	 */
	private void jump(String operando) {
		if (operando.matches("[a-zA-Z][a-zA-Z_0-9]*")) {
			int posicionEtiqueta = unidadMemoriaInstrucciones.posicionEtiqueta(operando);
			if (posicionEtiqueta >= 0) {
				ip = posicionEtiqueta;
			} else {
				throw new IllegalArgumentException("no se ha encontrado la etiqueta en el programa");
			}
		} else {
			throw new IllegalArgumentException("operando no válido para esta instrucción");
		}
	}

	/**
	 * El valor del registro IP se modifica para apuntar a la instrucción
	 * identificada por la etiqueta (si R0 == 0). Comprueba si el operando es
	 * válido.
	 * 
	 * @param operando Operando que utilizará la instrucción.
	 */
	private void jzero(String operando) {
		if (operando.matches("[a-zA-Z][a-zA-Z_0-9]*")) {
			int posicionEtiqueta = unidadMemoriaInstrucciones.posicionEtiqueta(operando);
			if (posicionEtiqueta >= 0) {
				if (unidadMemoriaDatos.get(0) == 0) {
					ip = posicionEtiqueta;
				}
			} else {
				throw new IllegalArgumentException("no se ha encontrado la etiqueta en el programa");
			}
		} else {
			throw new IllegalArgumentException("operando no válido para esta instrucción");
		}
	}

	/**
	 * El valor del registro IP se modifica para apuntar a la instrucción
	 * identificada por la etiqueta (si R0 > 0). Comprueba si el operando es válido.
	 * 
	 * @param operando Operando que utilizará la instrucción.
	 */
	private void jgtz(String operando) {
		if (operando.matches("[a-zA-Z][a-zA-Z_0-9]*")) {
			int posicionEtiqueta = unidadMemoriaInstrucciones.posicionEtiqueta(operando);
			if (posicionEtiqueta >= 0) {
				if (unidadMemoriaDatos.get(0) > 0) {
					ip = posicionEtiqueta;
				}
			} else {
				throw new IllegalArgumentException("no se ha encontrado la etiqueta en el programa");
			}
		} else {
			throw new IllegalArgumentException("operando no válido para esta instrucción");
		}
	}

	/**
	 * Se lee un valor de la cinta de entrada y se almacena en la memoria según el
	 * operando. Comprueba si el operando es válido (no puede usar el R0).
	 * 
	 * @param operando Operando que utilizará la instrucción.
	 */
	private void read(String operando) {
		int registroDondeAlmacenar;
		int valorParaAlmacenar = unidadEntrada.get();

		if (operando.matches("[0-9]+")) {
			registroDondeAlmacenar = Integer.valueOf(operando);
		} else if (operando.matches("\\*[0-9]+")) {
			registroDondeAlmacenar = unidadMemoriaDatos.get(Integer.valueOf(operando.substring(1)));
		} else {
			throw new IllegalArgumentException("operando no válido para esta instrucción");
		}

		if (registroDondeAlmacenar == 0) {
			throw new IllegalArgumentException("no se puede usar el registro 0 en está instrucción");
		}
		unidadMemoriaDatos.set(registroDondeAlmacenar, valorParaAlmacenar);
	}

	/**
	 * Se escribe el operando en la cinta de salida. Comprueba si el operando es
	 * válido (no puede usar el R0).
	 * 
	 * @param operando Operando que utilizará la instrucción.
	 */
	private void write(String operando) {
		int valorParaAlmacenar;

		if (operando.matches("[0-9]+")) {
			int registroApuntado = Integer.valueOf(operando);
			if (registroApuntado == 0) {
				throw new IllegalArgumentException("no se puede usar el registro 0 en está instrucción");
			}
			valorParaAlmacenar = unidadMemoriaDatos.get(registroApuntado);
		} else if (operando.matches("=[0-9]+")) {
			valorParaAlmacenar = Integer.valueOf(operando.substring(1));
		} else if (operando.matches("\\*[0-9]+")) {
			int registroApuntado = unidadMemoriaDatos.get(Integer.valueOf(operando.substring(1)));
			if (registroApuntado == 0) {
				throw new IllegalArgumentException("no se puede usar el registro 0 en está instrucción");
			}
			valorParaAlmacenar = unidadMemoriaDatos.get(registroApuntado);
		} else {
			throw new IllegalArgumentException("operando no válido para esta instrucción");
		}

		unidadSalida.set(valorParaAlmacenar);
	}

	/**
	 * Detiene la ejecución del programa. Comprueba si el operando es válido (no
	 * debería tener ninguno).
	 * 
	 * @param operando Operando que utilizará la instrucción.
	 */
	private void halt(String operando) {
		if (operando.isEmpty()) {
			ip = unidadMemoriaInstrucciones.ultimaInstruccion();
		} else {
			throw new IllegalArgumentException("operando no válido para esta instrucción");
		}
	}

	/**
	 * Método para formatear todos los datos usados en la ejecución del programa.
	 * 
	 * @return los datos formateados.
	 */
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

//TODO: comentarios de excepciones
//TODO: get ip deberia dar nulo solo al final
//TODO: eficiencia cintas, programa principal y excepciones