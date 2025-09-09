package com.vardhan.springbootwiththymeleaf.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vardhan.springbootwiththymeleaf.models.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{

}
