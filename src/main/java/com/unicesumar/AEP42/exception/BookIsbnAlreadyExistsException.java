package com.unicesumar.AEP42.exception;

public class BookIsbnAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BookIsbnAlreadyExistsException(String isbn) {
        super("O livro já existe com o ISBN: '" + isbn + "'");
    }

}
