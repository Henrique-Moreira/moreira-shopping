package br.com.moreira.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.moreira.models.Product;

public interface ProductsRepository extends JpaRepository<Product, Long>{

	Page<Product> findByCategoryId(int id, Pageable pageable);
	
	Page<Product> findById(int id, Pageable pageable);
}
