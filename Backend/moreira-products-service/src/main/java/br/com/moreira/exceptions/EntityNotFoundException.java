package br.com.moreira.exceptions;

public class EntityNotFoundException extends GenericException {
	private static final long serialVersionUID = 1L;
    
	public EntityNotFoundException(String message) {
		super(message);
	}

    public static EntityNotFoundException entityNotFoundException(Long categoryId) {
        return new EntityNotFoundException("Entity with id " + categoryId + " not found.");
    }
}