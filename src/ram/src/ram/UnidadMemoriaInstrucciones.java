package ram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnidadMemoriaInstrucciones {

	private ArrayList<Instruccion> instrucciones;

	enum InstruccionesValidas {
		LOAD, STORE, ADD, SUB, MUL, DIV, READ, WRITE, JUMP, JZERO, JGTZ, HALT
	}

	class Instruccion {

		String etiqueta;
		InstruccionesValidas instruccion;
		String operando;

		public Instruccion(String etiqueta, InstruccionesValidas instruccion, String operando) {
			this.etiqueta = etiqueta;
			this.instruccion = instruccion;
			this.operando = operando;
		}

	}

	public UnidadMemoriaInstrucciones(String archivoInstrucciones) {
		this.instrucciones = new ArrayList<>();

		try {
			BufferedReader bufferInstrucciones = new BufferedReader(new FileReader(archivoInstrucciones));

			while (bufferInstrucciones.ready()) {
				String instruccion = bufferInstrucciones.readLine();

				Pattern expresionRegular = Pattern.compile("#");
				Matcher encuentro = expresionRegular.matcher(instruccion);
				if (encuentro.find()) {
					instruccion = instruccion.substring(0, encuentro.start());
				}

				String[] token = instruccion.matches("\\s*") ? new String[0] : instruccion.trim().split("\\s+");

				if (token.length != 0) {
					String etiqueta = "";
					InstruccionesValidas nombreInstruccion = null;
					String operador = "";

					if (token[0].matches("[a-zA-Z_0-9]+:")) {
						etiqueta = token[0].substring(0, token[0].length() - 1);

						if (token.length > 1) {
							try {
								nombreInstruccion = InstruccionesValidas.valueOf(token[1].toUpperCase());
								if (token.length > 2) {
									if (token.length == 3 && token[2].matches("([=*]?[0-9]+)|([a-zA-Z_0-9]+)")) {
										operador = token[2];
									} else {
										throw new IllegalArgumentException("Instrucción " + instruccion + " no válida");
									}
								}
							} catch (IllegalArgumentException e) {
								throw new IllegalArgumentException("Instrucción " + instruccion + " no válida");
							}
						}
					} else {
						try {
							nombreInstruccion = InstruccionesValidas.valueOf(token[0].toUpperCase());
							if (token.length > 1) {
								if (token.length == 2 && token[1].matches("([=*]?[0-9]+)|([a-zA-Z_0-9]+)")) {
									operador = token[1];
								} else {
									throw new IllegalArgumentException("Instrucción " + instruccion + " no válida");
								}
							}
						} catch (IllegalArgumentException e) {
							throw new IllegalArgumentException("Instrucción " + instruccion + " no válida");
						}
					}

					instrucciones.add(new Instruccion(etiqueta, nombreInstruccion, operador));
				}
			}

			bufferInstrucciones.close();
		} catch (IOException e) {
			System.out.println("Ha habido un problema, con el fichero del programa.");
		}
	}

	public Instruccion get(int ip) {
		if (ip < instrucciones.size() && ip >= 0) {
			return instrucciones.get(ip);
		}
		return null;
	}

	public int get(String etiqueta) {
		if (etiqueta != null) {
			for (int i = 0; i < instrucciones.size(); i++) {
				if (etiqueta.equals(instrucciones.get(i).etiqueta)) {
					while (i < instrucciones.size() && (instrucciones.get(i).instruccion == null)) {
						i++;
					}
					return (i < instrucciones.size()) ? i : -1;
				}
			}
		}
		return -1;
	}

	public int getUltimaInstruccion() {
		return instrucciones.size();
	}
}
