package br.com.moreira.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.moreira.models.Product;
import br.com.moreira.services.ProductsService;

@RestController
@RequestMapping(value = "/products")
public class ProductsController {

	@Autowired
	private ProductsService service;
	
	@GetMapping
	public List<Product> products() {
		return service.allProducts();
	}
}
