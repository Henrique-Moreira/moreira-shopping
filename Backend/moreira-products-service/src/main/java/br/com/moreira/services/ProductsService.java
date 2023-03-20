package br.com.moreira.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.moreira.models.Product;

public interface ProductsService {

	/**
	 * Returns a page of products according to the specified pagination information.
	 * @param pageable the paging information of how the data should be returned in the request.
	 * @return A page of {@link Product} objects that match the pagination information provided.
	 */
	public Page<Product> allProducts(Pageable pageable);
}
