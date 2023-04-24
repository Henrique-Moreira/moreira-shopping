package br.com.moreira.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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
	
	Pageable pageable;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		
		service = new ProductsServiceImpl(repository);
		pageable = PageRequest.of(0, 2);
	}	
	
	@Test
	void shouldReturnPageableListOfProducs() {
		
		when(repository.findAll(any(Pageable.class))).thenReturn(TestMass.getPageProduct());
		
		Page<Product> response = this.service.allProducts(pageable);
		
		verify(repository, times(1)).findAll(any(Pageable.class));
		assertEquals(TestMass.getPageProduct(), response);		
	}
	
	@Test
	void shouldReturnEmptyPageableListOfProducs() {
		
		when(repository.findAll(any(Pageable.class))).thenReturn(TestMass.getEmptyPageProduct());
		
		Page<Product> response = this.service.allProducts(pageable);
		
		verify(repository, times(1)).findAll(any(Pageable.class));	
		assertEquals(0, response.getTotalElements());
		assertEquals(1, response.getTotalPages());	
	}
	
	@Test
	void shouldReturnPageOfProductsById() {
		when(repository.findById(anyInt(), any(Pageable.class))).thenReturn(TestMass.getPageProduct());

		Page<Product> response = this.service.findById(1, pageable);

		verify(repository, times(1)).findById(anyInt(), any(Pageable.class));
		assertEquals(TestMass.getPageProduct(), response);		
	}
	
	@Test
	void shouldReturnEmptyById() {		
		when(repository.findById(anyInt(), any(Pageable.class))).thenReturn(TestMass.getEmptyPageProduct());
		
		Page<Product> response = this.service.findById(1, pageable);
		
		verify(repository, times(1)).findById(anyInt(), any(Pageable.class));	
		assertEquals(0, response.getTotalElements());
		assertEquals(1, response.getTotalPages());	
	}
	
	@Test
	void shouldReturnPageableListOfProducsByCategoryId() {		
		when(repository.findByCategoryId(anyInt(), any(Pageable.class))).thenReturn(TestMass.getPageProduct());

		Page<Product> response = this.service.findByCategoryId(1, pageable);

		verify(repository, times(1)).findByCategoryId(anyInt(), any(Pageable.class));
		assertEquals(TestMass.getPageProduct(), response);		
	}
	
	@Test
	void shouldReturnEmptyPageableListOfProducsByCategoryId() {		
		when(repository.findByCategoryId(anyInt(), any(Pageable.class))).thenReturn(TestMass.getEmptyPageProduct());
		
		Page<Product> response = this.service.findByCategoryId(1, pageable);
		
		verify(repository, times(1)).findByCategoryId(anyInt(), any(Pageable.class));	
		assertEquals(0, response.getTotalElements());
		assertEquals(1, response.getTotalPages());	
	}

}
