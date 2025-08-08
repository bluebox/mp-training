package com.eazybytes.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/products")
@Validated
public class ProductsController {
	
	@GetMapping("/{id}")
	public String getId(@PathVariable("id") @Positive(message="id should be positive only") int productId) {
		return "fetched the product with id :"+productId;	
		
	}
	
	@GetMapping("/search")
	public String searchProduct(@RequestParam("category") String Category,
								@RequestParam(value="minPrice",required=true,defaultValue="0") @Digits(integer=3,fraction=2,message="price should contain atmost 3 integer places with 2 fractional places")double price) {
		return "The product with category: "+Category +"\nprice : "+price;
	}

}
