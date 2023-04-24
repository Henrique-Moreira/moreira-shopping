package br.com.moreira.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
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

	private final String initialUrl = "/product";
	private final String PARAM_PAGE = "page";
	private final String PARAM_SIZE = "size";
	private final String DEFAULT_PAGE = "0";
	private final String DEFAULT_SIZE = "2";

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);

		this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
				.setViewResolvers(new ViewResolver() {
					@Override
					public View resolveViewName(String viewName, Locale locale) throws Exception {
						return new MappingJackson2JsonView();
					}
				}).build();
	}

	@Test
	void shouldReturnPageOfProducts() throws Exception {
		Pageable pageable = PageRequest.of(0, 2);

		when(service.allProducts(any(Pageable.class))).thenReturn(TestMass.getPageProduct());

		ResponseEntity<Page<Product>> response = controller.products(pageable);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(TestMass.getPageProduct(), response.getBody());

		MockHttpServletRequestBuilder request = get(initialUrl).contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()));

		verify(service, times(2)).allProducts(any(Pageable.class));
	}

	@Test
	void shouldReturnPageOfProductsById() throws Exception {		
		when(service.findById(anyInt(), any(Pageable.class))).thenReturn(TestMass.getPageProduct());

		MockHttpServletRequestBuilder request = get(initialUrl.concat("/1"))
				.contentType(MediaType.APPLICATION_JSON)
				.param(PARAM_PAGE, String.valueOf(DEFAULT_PAGE))
				.param(PARAM_SIZE, String.valueOf(DEFAULT_SIZE));

		mockMvc.perform(request)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.totalElements", is(2)))
				.andExpect(jsonPath("$.content[0].id", is(1)));

		verify(service).findById(eq(1), any(Pageable.class));
	}

	@Test
	void shouldReturnEmptyPageOfProductsById() throws Exception {		
		when(service.findById(anyInt(), any(Pageable.class))).thenReturn(TestMass.getEmptyPageProduct());
		ArrayList<Object> emptyArrayList = new ArrayList<>();

		MockHttpServletRequestBuilder request = get(initialUrl.concat("/100"))				
		        .contentType(MediaType.APPLICATION_JSON)
		        .param(PARAM_PAGE, String.valueOf(DEFAULT_PAGE))
		        .param(PARAM_SIZE, String.valueOf(DEFAULT_SIZE));

		mockMvc.perform(request)
				.andDo(print())
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$", notNullValue()))
		        .andExpect(jsonPath("$.totalElements", is(0)))
		        .andExpect(jsonPath("$.content", is(emptyArrayList)));

		verify(service).findById(eq(100), any(Pageable.class));
	}

	@Test
	void shouldReturnPageOfProductsByCategoryId() throws Exception {
		when(service.findByCategoryId(anyInt(), any(Pageable.class))).thenReturn(TestMass.getPageProduct());
		
		MockHttpServletRequestBuilder request = get(initialUrl.concat("/category/1"))				
		        .contentType(MediaType.APPLICATION_JSON)
		        .param(PARAM_PAGE, String.valueOf(DEFAULT_PAGE))
		        .param(PARAM_SIZE, String.valueOf(DEFAULT_SIZE));

		mockMvc.perform(request)
				.andDo(print())
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$", notNullValue()))
		        .andExpect(jsonPath("$.totalElements", is(2)))
		        .andExpect(jsonPath("$.content[0].id", is(1)));

		verify(service).findByCategoryId(eq(1), any(Pageable.class));
	}

	@Test
	void shouldReturnEmptyPageOfProductsByCategoryId() throws Exception {		
		when(service.findByCategoryId(anyInt(), any(Pageable.class))).thenReturn(TestMass.getEmptyPageProduct());
		ArrayList<Object> emptyArrayList = new ArrayList<>();

		MockHttpServletRequestBuilder request = get(initialUrl.concat("/category/100"))				
		        .contentType(MediaType.APPLICATION_JSON)
		        .param(PARAM_PAGE, String.valueOf(DEFAULT_PAGE))
		        .param(PARAM_SIZE, String.valueOf(DEFAULT_SIZE));

		mockMvc.perform(request)
				.andDo(print())
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$", notNullValue()))
		        .andExpect(jsonPath("$.totalElements", is(0)))
		        .andExpect(jsonPath("$.content", is(emptyArrayList)));

		verify(service).findByCategoryId(eq(100), any(Pageable.class));
	}
}
