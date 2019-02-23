package ram;

public class Instruccion {

	private String etiqueta;
	private InstruccionesValidas nombreInstruccion;
	private String operando;

	public Instruccion(String etiqueta, InstruccionesValidas instruccion, String operando) {
		this.etiqueta = (etiqueta == null) ? "" : etiqueta;
		this.nombreInstruccion = instruccion;
		this.operando = (operando == null) ? "" : operando;
	}

	@Override
	public String toString() {
		String resultado = "";

		if (!etiqueta.isEmpty()) {
			resultado = etiqueta + ":\t";
			if (nombreInstruccion != null) {
				resultado += nombreInstruccion + " " + operando;
			}
		} else {
			resultado = "\t" + nombreInstruccion + " " + operando;
		}

		return resultado;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public InstruccionesValidas getNombreInstruccion() {
		return nombreInstruccion;
	}

	public String getOperando() {
		return operando;
	}
}