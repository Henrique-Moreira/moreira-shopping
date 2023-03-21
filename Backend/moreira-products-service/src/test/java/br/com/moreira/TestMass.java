package br.com.moreira;

import org.springframework.boot.test.context.TestComponent;

import br.com.moreira.models.Category;
import br.com.moreira.models.Product;

@TestComponent
public class TestMass {

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
}
