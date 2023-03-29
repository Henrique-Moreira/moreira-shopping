package br.com.moreira.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.moreira.models.Product;
import br.com.moreira.repositories.ProductsRepository;
import br.com.moreira.services.ProductsService;

@Service
public class ProductsServiceImpl implements ProductsService {

	@Autowired
	private ProductsRepository repository;
	
	public ProductsServiceImpl(ProductsRepository repository) {
		this.repository = repository;
	}

	@Override
	public Page<Product> allProducts(Pageable pageable) {		
		return repository.findAll(pageable);
	}

	@Override
	public Page<Product> findByCategoryId(Long id, Pageable pageable) {
		return repository.findByCategoryId(id, pageable);
	}

}
