package ram;

/**
 * Instrucciones que pueden ser usadas en la el sistema ram.
 * 
 * @author Jorge González Cabrera
 * 
 *         <li>{@link #LOAD}</li>
 *         <li>{@link #STORE}</li>
 *         <li>{@link #ADD}</li>
 *         <li>{@link #SUB}</li>
 *         <li>{@link #MUL}</li>
 *         <li>{@link #DIV}</li>
 *         <li>{@link #READ}</li>
 *         <li>{@link #WRITE}</li>
 *         <li>{@link #JUMP}</li>
 *         <li>{@link #JZERO}</li>
 *         <li>{@link #JGTZ}</li>
 *         <li>{@link #HALT}</li>
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