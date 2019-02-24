package ram;

/**
 * Programa que ejecuta la ram a partir de los fichero recibidos.
 * 
 * @author Jorge González Cabrera
 */
public class Main {

	public static void main(String[] args) {
		UnidadAritmeticaControlLogica uacl;

		try {
			if (args.length == 3) {
				uacl = new UnidadAritmeticaControlLogica(args[0], args[1], args[2]);
				uacl.ejecutarPrograma(false);
			} else if ((args.length == 4) && args[3].equals("debug")) {
				uacl = new UnidadAritmeticaControlLogica(args[0], args[1], args[2]);
				uacl.ejecutarPrograma(true);
			} else {
				System.out.println("Uso: \n\tMain códigoFuente cintaEntrada cintaSalida [debug]");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
