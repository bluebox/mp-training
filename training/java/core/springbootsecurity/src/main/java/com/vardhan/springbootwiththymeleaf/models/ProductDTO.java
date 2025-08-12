package com.vardhan.springbootwiththymeleaf.models;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ProductDTO {
	
	@NotEmpty(message = "Name should not be empty")
	private String name;

	@NotEmpty(message = "Name Should Not Be empty")
	private String brand;
	
	@NotEmpty(message = "Please Select the Category")
	private String category;
	
	@Min(0)
	private double price;
	
	@Size(min = 10, message = "The Deccription Should Be minumum of 10 Characters")
	@Size(max = 2000, message = "The Deccription Should Be maximum of 2000 Characters")
	private String description;
	
	private static MultipartFile imageFile;
	
	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String decription) {
		this.description = decription;
	}

	public static MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}
}
