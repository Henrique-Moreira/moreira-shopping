package br.com.moreira.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import br.com.moreira.TestMass;
import br.com.moreira.models.Product;
import br.com.moreira.services.impl.ProductsServiceImpl;

class ProductsControllerTest {
	
	private MockMvc mockMvc;

	@Mock
	private ProductsServiceImpl service;

	@InjectMocks
	private ProductsController controller;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		
		this.mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers(new ViewResolver() {
                    @Override
                    public View resolveViewName(String viewName, Locale locale) throws Exception {
                        return new MappingJackson2JsonView();
                    }
                })
                .build();
	}

	@Test
	void shouldReturnPageOfProducts() throws Exception {
		Pageable pageable = PageRequest.of(0, 2);
		
		when(service.allProducts(any(Pageable.class))).thenReturn(TestMass.getPageProduct());
		
		ResponseEntity<Page<Product>> response = controller.products(pageable);
		
	    assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals(TestMass.getPageProduct(), response.getBody());
	    
		mockMvc.perform(get("/products")				
        .contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", notNullValue()));
	}
	
	@Test
	void shouldReturnPageOfProductsByCategory() throws Exception {		
		when(service.findByCategoryId(anyInt(), any(Pageable.class))).thenReturn(TestMass.getPageProduct());
		
		mockMvc.perform(get("/products/1")				
        .contentType(MediaType.APPLICATION_JSON)
        .param("page", "0")
        .param("size", "2"))
		.andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", notNullValue()))
        .andExpect(jsonPath("$.totalElements", is(2)))
        .andExpect(jsonPath("$.content[0].id", is(1)));
	}
	
	@Test
	void shouldReturnEmptyPageOfProductsByCategory() throws Exception {		
		when(service.findByCategoryId(anyInt(), any(Pageable.class))).thenReturn(TestMass.getEmptyPageProduct());
		ArrayList<Object> emptyArrayList = new ArrayList<>();
		
		mockMvc.perform(get("/products/100")				
        .contentType(MediaType.APPLICATION_JSON)
        .param("page", "0")
        .param("size", "2"))
		.andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", notNullValue()))
        .andExpect(jsonPath("$.totalElements", is(0)))
        .andExpect(jsonPath("$.content", is(emptyArrayList)));
	}
}
