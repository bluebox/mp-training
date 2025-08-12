package com.example.springBootSample.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springBootSample.models.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {

}
