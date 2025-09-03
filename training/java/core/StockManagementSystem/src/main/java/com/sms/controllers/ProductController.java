package com.sms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sms.models.Product;
import com.sms.models.ProductStock;
import com.sms.service.Interfaces.ProductService;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
//@CrossOrigin("http://localhost:5173/")
public class ProductController {
  @Autowired
    private  ProductService psi;

    @PostMapping
    public ResponseEntity<String> addProduct(@Valid @RequestBody Product product) {
        return new ResponseEntity<>(psi.addProduct(product), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Product> getSuppliers(@RequestParam(required = false) String searchKey) {
        return psi.getProducts(searchKey);
    }
    @GetMapping("/product-stock")
    public List<ProductStock> viewProductStock() {

        return  psi.ViewProductStock();
    }


}
