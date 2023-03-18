package br.com.moreira.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.moreira.models.Product;
import br.com.moreira.repositories.ProductsRepository;
import br.com.moreira.services.ProductsService;

@Service
public class ProductsServiceImpl implements ProductsService{

	@Autowired
	private ProductsRepository repository;

	@Override
	public List<Product> allProducts() {
		return repository.findAll();
	}

}
