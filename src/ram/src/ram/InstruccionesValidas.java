package ram;

/**
 * Instrucciones que pueden ser usadas en la el sistema ram.
 * 
 * @author Jorge González Cabrera
 */
public enum InstruccionesValidas {

	/**
	 * Instrucción load.
	 */
	LOAD,

	/**
	 * Instrucción store;
	 */
	STORE,

	/**
	 * Instrucción add.
	 */
	ADD,

	/**
	 * Instrucción sub.
	 */
	SUB,

	/**
	 * Instrucción mul.
	 */
	MUL,

	/**
	 * Instrucción div.
	 */
	DIV,

	/**
	 * Instrucción read.
	 */
	READ,

	/**
	 * Instrucción write.
	 */
	WRITE,

	/**
	 * Instrucción jump.
	 */
	JUMP,

	/**
	 * Instrucción jzero.
	 */
	JZERO,

	/**
	 * Instrucción jgtz.
	 */
	JGTZ,

	/**
	 * Instrucción halt.
	 */
	HALT
}