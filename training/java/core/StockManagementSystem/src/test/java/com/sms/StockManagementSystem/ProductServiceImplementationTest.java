package com.sms.StockManagementSystem;

import com.sms.Dao.Interfaces.ProductDao;
import com.sms.Exceptions.ProductExceptions;
import com.sms.models.Product;

import com.sms.service.implementations.ProductServiceImplementation;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.*;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class ProductServiceImplementationTest {

    @Mock
    private ProductDao productDao;

    @InjectMocks
    private ProductServiceImplementation productService;


    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        
    }

    @Test
    void testAddProductSuccess() {
        Product product = new Product();
        product.setProductId("P001");
        product.setName("Test Product");
        product.setStatus("Available");
        when(productDao.addProduct(product)).thenReturn("Product added successfully");
        String result = productService.addProduct(product);
        assertEquals("Product added successfully", result);
        verify(productDao, times(1)).addProduct(product);
    }

    @Test
    void testAddProductNull_ThrowsException() {
        Exception exception = assertThrows(ProductExceptions.class, () -> {
            productService.addProduct(null);
        });
        assertEquals("Product cannot be null", exception.getMessage());
    }

    @Test
    void testAddProductMissingId_ThrowsException() {
        Product product = new Product();
        product.setName("Test");
        product.setStatus("Available");

        Exception exception = assertThrows(ProductExceptions.class, () -> {
            productService.addProduct(product);
        });
        assertEquals("Product ID is required", exception.getMessage());
    }

    @Test
    void testAddProductMissingName_ThrowsException() {
        Product product = new Product();
        product.setProductId("P001");
        product.setStatus("Available");

        Exception exception = assertThrows(ProductExceptions.class, () -> {
            productService.addProduct(product);
        });
        assertEquals("Product name is required", exception.getMessage());
    }

    @Test
    void testAddProductMissingStatus_ThrowsException() {
        Product product = new Product();
        product.setProductId("P001");
        product.setName("Test");

        Exception exception = assertThrows(ProductExceptions.class, () -> {
            productService.addProduct(product);
        });
        assertEquals("Product status is required", exception.getMessage());
    }

    @Test
    void testGetProducts() {
        Product p1 = new Product("P001", "Pen", "Available");
        Product p2 = new Product("P002", "Pencil", "Available");

        when(productDao.getProducts(null)).thenReturn(List.of(p1, p2));

        List<Product> products = productService.getProducts(null);
        assertEquals(2, products.size());
        verify(productDao, times(1)).getProducts(null);
    }
}

