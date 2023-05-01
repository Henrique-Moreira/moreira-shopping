package br.com.moreira.exceptions;

public class GenericException extends RuntimeException {
	private static final long serialVersionUID = 1L;

    public GenericException(String message) {
        super(message);
    }

    public GenericException(String message, Throwable cause) {
        super(message, cause);
    }
}
