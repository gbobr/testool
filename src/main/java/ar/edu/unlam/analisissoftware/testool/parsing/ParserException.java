package ar.edu.unlam.analisissoftware.testool.parsing;

public class ParserException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5918319315622960340L;

	public ParserException(Throwable cause){
		this.initCause(cause);
	}
}
