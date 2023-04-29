package br.com.moreira.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.moreira.dto.ProductDto;
import br.com.moreira.models.Product;

public interface ProductsService {

	/**
	 * Returns a page of products according to the specified pagination information.
	 * @param pageable the paging information of how the data should be returned in the request.
	 * @return A page of {@link Product} objects that match the pagination information provided.
	 */
	public Page<Product> allProducts(Pageable pageable);
	
	/**
	 * Returns a product according to the specified pagination information and id.
	 * @param pageable the paging information of how the data should be returned in the request.
     * @param id the product id.
	 * @return A page of {@link Product} objects that match the pagination and id of product provided.
	 */
	public Page<Product> findById(int id, Pageable pageable);
	
	/**
	 * Returns a page of products according to the specified pagination information and category id.
	 * @param pageable the paging information of how the data should be returned in the request.
     * @param id the category id.
	 * @return A page of {@link Product} objects that match the pagination and id of category provided.
	 */
	public Page<Product> findByCategoryId(int id, Pageable pageable);
	
	/**
	 * Returns the product data that was saved in the request.
	 * @param product data to be saved
	 * @return The product that was saved.
	 */
	public Product save(ProductDto productDto);
}
