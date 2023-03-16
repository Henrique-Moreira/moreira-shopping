package br.com.moreira.models;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Product {

	@Id
	private Integer id;
	private String name;
	private float price;
}
