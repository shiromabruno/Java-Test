package br.com.shiroma.exceptions;

public class FilmeSemEstoqueException extends Exception {

	private static final long serialVersionUID = -3806823119904757293L;
	
	public FilmeSemEstoqueException(String message) {
		super(message);
	}

}
