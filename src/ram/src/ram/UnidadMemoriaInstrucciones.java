package ram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class UnidadMemoriaInstrucciones {

	private ArrayList<String> instrucciones;

	public UnidadMemoriaInstrucciones(String archivoInstrucciones, String archivoEntrada, String archivoSalida) {
			BufferedReader bufferInstrucciones;
			try {
				bufferInstrucciones = new BufferedReader(new FileReader(archivoInstrucciones));
				while (bufferInstrucciones.ready()) {
					instrucciones.add(bufferInstrucciones.readLine());
				}
				bufferInstrucciones.close();
			} catch (IOException e) {
				System.out.println("Ha habido un problema, con el fichero del programa.");
			}

	}
}
