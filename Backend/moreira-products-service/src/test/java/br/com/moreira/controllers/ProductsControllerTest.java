package br.com.moreira.controllers;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.moreira.services.ProductsService;

class ProductsControllerTest {

	private MockMvc mockMvc;

	@Mock
	private ProductsService productsService;

	@InjectMocks
	private ProductsController productsController;

	private static final String initialUrl = "/products";

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		
		this.mockMvc = MockMvcBuilders.standaloneSetup(productsController).build();
	}
	
	@Test
	void testProducts() {
		assertTrue(true);
	}

}
