package br.com.moreira.dto;

import java.io.Serializable;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private float price;
	private Long categoryId;
}

