package br.com.moreira.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.moreira.models.Product;

public interface ProductsRepository extends JpaRepository<Product, Long>{

}
