package com.unicesumar.AEP42.exception;

public class BookNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BookNotFoundException(String isbn) {
        super("O livro não pôde ser encontrado com o ISBN: '" + isbn + "'");
    }

}
