package br.com.moreira.services;

import java.util.Optional;

import br.com.moreira.models.Category;

public interface CategoryService {

	/**
	 * Returns a {@link Category} according to the specified id.
     * @param id the category id.
	 * @return A category {@link Category} objects that match the id of provided.
	 */
	public Optional<Category> findById(Long id);
}
