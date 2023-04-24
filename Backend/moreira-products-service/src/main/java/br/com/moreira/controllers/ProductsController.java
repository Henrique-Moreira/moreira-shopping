package br.com.moreira.controllers;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.moreira.models.Product;
import br.com.moreira.services.impl.ProductsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/product")
public class ProductsController {

	@Autowired
	private ProductsServiceImpl service;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "When does not found any product in the database.")})
    @Operation(summary = "List all products paginated",
    			description = "The default size is 20, use the parameter size to change the default value",
    			tags = {"product"})
	@GetMapping
	public ResponseEntity<Page<Product>> products(@ParameterObject Pageable pageable) {
		return ResponseEntity.ok(service.allProducts(pageable));
	}
    
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "When does not found any product in the database.")})
    @Operation(summary = "Product paginated according id",
    			description = "The default size is 20, use the parameter size to change the default value",
    			tags = {"product"})
	@GetMapping("/{productId}")
	public ResponseEntity<Page<Product>> findById(@PathVariable int productId , @ParameterObject Pageable pageable) {
		return ResponseEntity.ok(service.findById(productId, pageable));
	}
    
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "When does not found any product in the database.")})
    @Operation(summary = "List of products paginated according category id",
    			description = "The default size is 20, use the parameter size to change the default value",
    			tags = {"product"})
	@GetMapping("category/{categoryId}")
	public ResponseEntity<Page<Product>> findByCategoryId(@PathVariable int categoryId , @ParameterObject Pageable pageable) {
		return ResponseEntity.ok(service.findByCategoryId(categoryId, pageable));
	}
}
