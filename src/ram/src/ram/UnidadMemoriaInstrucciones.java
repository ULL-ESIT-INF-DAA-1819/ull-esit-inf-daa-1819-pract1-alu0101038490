package ram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnidadMemoriaInstrucciones {

	private ArrayList<Instruccion> instrucciones;

	public UnidadMemoriaInstrucciones(String archivoInstrucciones) {
		this.instrucciones = new ArrayList<>();

		try {
			BufferedReader bufferInstrucciones = new BufferedReader(new FileReader(archivoInstrucciones));

			while (bufferInstrucciones.ready()) {
				String instruccion = bufferInstrucciones.readLine();

				Pattern expresionRegularComentario = Pattern.compile("#");
				Matcher encuentroComentario = expresionRegularComentario.matcher(instruccion);
				if (encuentroComentario.find()) {
					instruccion = instruccion.substring(0, encuentroComentario.start());
				}

				String[] tokens = instruccion.matches("\\s*") ? new String[0] : instruccion.trim().split("\\s+");

				if (tokens.length != 0) {
					String etiqueta = "";
					InstruccionesValidas nombreInstruccion = null;
					String operador = "";

					if (tokens[0].matches("[a-zA-Z][a-zA-Z_0-9]*:")) {
						etiqueta = tokens[0].substring(0, tokens[0].length() - 1);

						if (tokens.length > 1) {
							try {
								nombreInstruccion = InstruccionesValidas.valueOf(tokens[1].toUpperCase());
								if (tokens.length > 2) {
									if (tokens.length == 3 && tokens[2].matches("([=*]?[0-9]+)|([a-zA-Z][a-zA-Z_0-9]*)")) {
										operador = tokens[2];
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
							nombreInstruccion = InstruccionesValidas.valueOf(tokens[0].toUpperCase());
							if (tokens.length > 1) {
								if (tokens.length == 2 && tokens[1].matches("([=*]?[0-9]+)|([a-zA-Z][a-zA-Z_0-9]*)")) {
									operador = tokens[1];
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

	public int posicionEtiqueta(String etiqueta) {
		if (etiqueta != null) {
			for (int i = 0; i < instrucciones.size(); i++) {
				if (etiqueta.equals(instrucciones.get(i).getEtiqueta())) {
					return i;
				}
			}
		}
		return -1;
	}

	public int ultimaInstruccion() {
		return instrucciones.size();
	}

	@Override
	public String toString() {
		String resultado = "Instrucciones: \n";
		Integer contador = 0;
		for (Instruccion i : instrucciones) {
			resultado += "   " + String.valueOf(contador) + " " + i.toString() + "\n";
			contador++;
		}
		return resultado;
	}
}
