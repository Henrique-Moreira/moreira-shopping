package br.com.moreira.services.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.moreira.dto.ProductDto;
import br.com.moreira.exceptions.EntityNotFoundException;
import br.com.moreira.models.Category;
import br.com.moreira.models.Product;
import br.com.moreira.repositories.ProductsRepository;
import br.com.moreira.services.CategoryService;
import br.com.moreira.services.ProductsService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductsServiceImpl implements ProductsService {

	private final ProductsRepository repository;
	private final CategoryService categoryService;
	
	public ProductsServiceImpl(ProductsRepository repository, CategoryService categoryService) {
		this.repository = repository;
		this.categoryService = categoryService;
	}

	@Override
	public Page<Product> allProducts(Pageable pageable) {	
		log.info("allProducts: searching all products in the base");
		return repository.findAll(pageable);
	}
	
	@Override
	public Page<Product> findById(int id, Pageable pageable) {
		log.info("findById: searching an product in the base with id = {}", id);
		return repository.findById(id, pageable);
	}

	@Override
	public Page<Product> findByCategoryId(int id, Pageable pageable) {
		log.info("findByCategoryId: searching products in the base with category id = {}", id);
		return repository.findByCategoryId(id, pageable);
	}

	@Override
	public Product save(ProductDto productDto) {
		Optional<Category> category = categoryService.findById(productDto.getCategoryId()); 
		if(category.isPresent()) {
			return Product.builder()
					.id(productDto.getId())
					.name(productDto.getName())
					.price(productDto.getPrice())
					.category(category.get())
					.build();
		}
		throw new EntityNotFoundException(productDto.getId().toString());
	}
}
