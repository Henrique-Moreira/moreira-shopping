package br.com.moreira.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.moreira.models.Product;
import br.com.moreira.repositories.ProductsRepository;

public class ProductsService {
	
	@Autowired
	private ProductsRepository repository;

	public List<Product> allProducts() {
		return repository.findAll();
	}

}
