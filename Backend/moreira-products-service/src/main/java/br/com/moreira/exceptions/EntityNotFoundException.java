package br.com.moreira.exceptions;

public class CategoryException extends GenericException {
    private static final long serialVersionUID = 1L;

    public CategoryException(String message) {
        super(message);
    }

    public CategoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public static CategoryException categoryNotFoundException(Long categoryId) {
        return new CategoryException("Category with id " + categoryId + " not found.");
    }
}