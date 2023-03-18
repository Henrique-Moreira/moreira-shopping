package br.com.moreira.services;

import java.util.List;

import br.com.moreira.models.Product;

public interface ProductsService {

	/**
	 * Method that returns all products
	 * @return
	 */
	public List<Product> allProducts();
}
