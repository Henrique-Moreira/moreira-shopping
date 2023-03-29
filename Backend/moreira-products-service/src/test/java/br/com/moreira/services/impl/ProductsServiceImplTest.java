package br.com.moreira.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import br.com.moreira.TestMass;
import br.com.moreira.models.Product;
import br.com.moreira.repositories.ProductsRepository;

class ProductsServiceImplTest {
	
	@Mock
	private ProductsRepository repository;
		
	@Mock
	private ProductsServiceImpl service;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		
		service = new ProductsServiceImpl(repository);
	}	
	
	@Test
	void shouldReturnPageableListOfProducs() {
		Pageable pageable = PageRequest.of(0, 2);
		
		when(repository.findAll(any(Pageable.class))).thenReturn(TestMass.getPageProduct());
		
		Page<Product> response = this.service.allProducts(pageable);
		
		verify(repository, times(1)).findAll(any(Pageable.class));
		assertEquals(TestMass.getPageProduct(), response);		
	}
	
	@Test
	void shouldReturnEmptyPageableListOfProducs() {
		Pageable pageable = PageRequest.of(0, 2);
		
		when(repository.findAll(any(Pageable.class))).thenReturn(TestMass.getEmptyPageProduct());
		
		Page<Product> response = this.service.allProducts(pageable);
		
		verify(repository, times(1)).findAll(any(Pageable.class));	
		assertEquals(0, response.getTotalElements());
		assertEquals(1, response.getTotalPages());	
	}

}
