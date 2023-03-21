package br.com.moreira;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.test.context.TestComponent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.moreira.models.Category;
import br.com.moreira.models.Product;

@TestComponent
public final class TestMass {

	private TestMass() { }
	
	public static Category getCategory() {
		return Category.builder()
				.id(1l)
				.name("Electronics")
				.build();
	}

	public static Product getProduct() {		
		return Product.builder()
				.id(1l)
				.name("Samsung Galaxy S22 Ultra")
				.price(8999)
				.category(getCategory())
				.build();
	}
	
	public static List<Product> getProductList() {
		List<Product> listProducts = new ArrayList<>();
		listProducts.add(getProduct());
		
		return listProducts;
	}
	
	public static Page<Product> getPageProduct() {
		return new PageImpl<>(getProductList(), PageRequest.of(0, 2), 2);
	}
}
