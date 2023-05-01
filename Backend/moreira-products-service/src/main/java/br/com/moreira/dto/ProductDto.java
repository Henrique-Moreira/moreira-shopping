package br.com.moreira.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

	@NotBlank(message = "product name must not be blank.")
	private String name;
	
	@NotNull(message = "product price must not be null.")
	private float price;
	
	@NotNull(message = "category id must not be null.")
	private Long categoryId;
}

