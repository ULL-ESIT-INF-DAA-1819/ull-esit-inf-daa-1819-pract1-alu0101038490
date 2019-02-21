package ram;

public class Main {

	public static void main(String[] args) {
		UnidadAritmeticaControlLogica uacl;
		if (args.length == 3) {
			uacl = new UnidadAritmeticaControlLogica(args[0], args[1], args[2]);
			uacl.ejecutarPrograma();
		} else {
			System.out.println("Para la correcta ejecución del programa se tienen que recibir tres ficheros.");
		}
	}
}
