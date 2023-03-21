package br.com.moreira.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.moreira.TestMass;
import br.com.moreira.models.Product;
import br.com.moreira.services.impl.ProductsServiceImpl;

class ProductsControllerTest {

	@Mock
	private ProductsServiceImpl service;

	@InjectMocks
	private ProductsController controller;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);		
	}

	@Test
	void shouldReturnPageOfProducts() throws Exception {
		Pageable pageable = PageRequest.of(0, 2);
		
		when(service.allProducts(any(Pageable.class))).thenReturn(TestMass.getPageProduct());
		
		ResponseEntity<Page<Product>> response = controller.products(pageable);
		
	    assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals(TestMass.getPageProduct(), response.getBody());
	}
}
