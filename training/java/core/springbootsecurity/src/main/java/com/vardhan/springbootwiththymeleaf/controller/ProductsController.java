package com.vardhan.springbootwiththymeleaf.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.vardhan.springbootwiththymeleaf.models.Product;
import com.vardhan.springbootwiththymeleaf.models.ProductDTO;
import com.vardhan.springbootwiththymeleaf.service.ProductRepo;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepo repo;

    @GetMapping({"", "/"})
    public String showProductList(Model model) {
        List<Product> products = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("products", products);
        return "products/index";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        ProductDTO productDTO = new ProductDTO();
        model.addAttribute("productDTO", productDTO);
        return "products/createproduct";
    }

    @PostMapping("/create")
    public String createProduct(@Valid @ModelAttribute ProductDTO productDTO, BindingResult result) {
        if (productDTO.getImageFile().isEmpty()) {
            result.addError(new FieldError("productDTO", "imageFile", "The image file is required"));
        }

        if (result.hasErrors()) {
            return "products/createproduct";
        }

        MultipartFile image = productDTO.getImageFile();
        Date createdAt = new Date();
        String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();

        try {
            String uploadDir = "public/images/";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = image.getInputStream()) {
                Files.copy(inputStream, uploadPath.resolve(storageFileName), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

        Product product = new Product();
        product.setName(productDTO.getName());
        product.setBrand(productDTO.getBrand());
        product.setCategory(productDTO.getCategory());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setCreatedAt(createdAt);
        product.setImgUrl(storageFileName);

        repo.save(product);

        return "redirect:/products";
    }

    @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam int id) {
        try {
            Product product = repo.findById(id).get();
            model.addAttribute("product", product);

            ProductDTO productDTO = new ProductDTO();
            productDTO.setName(product.getName());
            productDTO.setBrand(product.getBrand());
            productDTO.setCategory(product.getCategory());
            productDTO.setPrice(product.getPrice());
            productDTO.setDescription(product.getDescription());

            model.addAttribute("productDTO", productDTO);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return "redirect:/products";
        }
        return "products/EditProduct";
    }

    @PostMapping("/edit")
    public String updateProduct(Model model, @RequestParam int id, @Valid @ModelAttribute ProductDTO productDTO, BindingResult result) {
        try {
            Product product = repo.findById(id).get();
            model.addAttribute("product", product);

            if (result.hasErrors()) {
                return "products/EditProduct";
            }

            if (!productDTO.getImageFile().isEmpty()) {
                String uploadDir = "public/images/";
                Path oldImagePath = Paths.get(uploadDir, product.getImgUrl());

                try {
                    Files.deleteIfExists(oldImagePath);
                } catch (Exception e) {
                    System.out.println("Exception: " + e.getMessage());
                }
                MultipartFile image = productDTO.getImageFile();
                Date createdAt = new Date();
                String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();

                try (InputStream inputStream = image.getInputStream()) {
                    Files.copy(inputStream, Paths.get(uploadDir, storageFileName), StandardCopyOption.REPLACE_EXISTING);
                }

                product.setImgUrl(storageFileName);
            }

            product.setName(productDTO.getName());
            product.setBrand(productDTO.getBrand());
            product.setCategory(productDTO.getCategory());
            product.setPrice(productDTO.getPrice());
            product.setDescription(productDTO.getDescription());

            repo.save(product);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

        return "redirect:/products";
    }
    
    @GetMapping("/delete")
    public String deleteProduct(@RequestParam int id) {
    	
    	
    	try {
			Product product = repo.findById(id).get();
			
			Path imagePath = Paths.get("public/images",product.getImgUrl());
			
			try {
				Files.delete(imagePath);
			} catch (Exception e) {
				System.out.println("Exception: " + e.getMessage());
			}
			
			repo.delete(product);
			
		} catch (Exception e) {
			System.out.println("Exception: " +e.getMessage());
		}
    	
    	return "redirect:/products";
    }
}
