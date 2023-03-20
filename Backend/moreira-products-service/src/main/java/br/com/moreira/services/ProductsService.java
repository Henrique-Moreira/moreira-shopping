package br.com.moreira.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.moreira.models.Product;

public interface ProductsService {

	/**
	 * Method that returns all products
	 * @return
	 */
	public Page<Product> allProducts(Pageable pageable);
}
