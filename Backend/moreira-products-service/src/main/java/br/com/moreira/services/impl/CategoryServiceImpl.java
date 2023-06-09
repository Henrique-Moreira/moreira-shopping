package br.com.moreira.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.moreira.exceptions.EntityNotFoundException;
import br.com.moreira.models.Category;
import br.com.moreira.repositories.CategoryRepository;
import br.com.moreira.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository repository;
	
	public CategoryServiceImpl(CategoryRepository repository) {
		this.repository = repository;
	}

	@Override
	public Optional<Category> findById(Long id) {
		Optional<Category> category =  repository.findById(id);
		if(category.isPresent()) {
			return category;
		}
		throw new EntityNotFoundException(id.toString());
	}
	
}