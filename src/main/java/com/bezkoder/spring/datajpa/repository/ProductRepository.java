package com.bezkoder.spring.datajpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.datajpa.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findByPrice(float price);
	List<Product> findBynameContaining(String name);

}
