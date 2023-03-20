package br.com.moreira.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.moreira.models.Product;
import br.com.moreira.services.impl.ProductsServiceImpl;

@RestController
@RequestMapping(value = "/products")
public class ProductsController {

	@Autowired
	private ProductsServiceImpl service;

	@GetMapping
	public ResponseEntity<Page<Product>> products(Pageable pageable) {
		return ResponseEntity.ok(service.allProducts(pageable));
	}
}
