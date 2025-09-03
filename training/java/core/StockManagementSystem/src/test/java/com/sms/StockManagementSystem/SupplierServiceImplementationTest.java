package com.sms.StockManagementSystem;


import com.sms.Dao.Interfaces.SupplierDao;
import com.sms.Exceptions.SupplierExceptions;
import com.sms.models.Supplier;
import com.sms.service.implementations.SupplierServiceImplementation;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

class SupplierServiceImplementationTest {

    @InjectMocks
    private SupplierServiceImplementation supplierService;

    @Mock
    private SupplierDao supplierDao;
   

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    private Supplier getValidSupplier() {
        Supplier s = new Supplier();
        s.setName("John");
        s.setGender("M");
        s.setMobile(9876543210L);
        s.setEmail("john@example.com");
        s.setCountry("India");
        s.setState("TS");
        s.setCity("Hyderabad");
        s.setAddress("Some Address");
        s.setCreatedBy("Admin");
        return s;
    }

    @Test
    void testAddSupplierSuccess() {
        Supplier supplier = getValidSupplier();
        when(supplierDao.addSupplier(supplier)).thenReturn(1L);

        Long result = supplierService.addSupplier(supplier);
        
        assertEquals(1L, result);
        verify(supplierDao, times(1)).addSupplier(supplier);
    }

    @Test
    void testAddSupplier_NullSupplier_ThrowsException() {
        Exception ex = assertThrows(SupplierExceptions.class, () -> {
            supplierService.addSupplier(null);
        });
        assertEquals("supplier data is null", ex.getMessage());
    }

    @Test
    void testAddSupplier_BlankName_ThrowsException() {
        Supplier supplier = getValidSupplier();
        supplier.setName("  ");

        Exception ex = assertThrows(SupplierExceptions.class, () -> {
            supplierService.addSupplier(supplier);
        });
        assertEquals("Name field is empty", ex.getMessage());
    }

    @Test
    void testAddSupplier_InvalidGender_ThrowsException() {
        Supplier supplier = getValidSupplier();
        supplier.setGender("X");

        Exception ex = assertThrows(SupplierExceptions.class, () -> {
            supplierService.addSupplier(supplier);
        });
        assertEquals("Invalid Gender", ex.getMessage());
    }

    @Test
    void testAddSupplier_InvalidMobile_ThrowsException() {
        Supplier supplier = getValidSupplier();
        supplier.setMobile(12345L);

        Exception ex = assertThrows(SupplierExceptions.class, () -> {
            supplierService.addSupplier(supplier);
        });
        assertEquals("Invalid Mobile number please check", ex.getMessage());
    }

    @Test
    void testAddSupplier_BlankEmail_ThrowsException() {
        Supplier supplier = getValidSupplier();
        supplier.setEmail("   ");

        Exception ex = assertThrows(SupplierExceptions.class, () -> {
            supplierService.addSupplier(supplier);
        });
        assertEquals("Invalid email please check", ex.getMessage());
    }

    @Test
    void testAddSupplier_BlankCountry_ThrowsException() {
        Supplier supplier = getValidSupplier();
        supplier.setCountry(" ");

        Exception ex = assertThrows(SupplierExceptions.class, () -> {
            supplierService.addSupplier(supplier);
        });
        assertEquals("Invalid Country please select correct country", ex.getMessage());
    }

    @Test
    void testAddSupplier_BlankState_ThrowsException() {
        Supplier supplier = getValidSupplier();
        supplier.setState(" ");

        Exception ex = assertThrows(SupplierExceptions.class, () -> {
            supplierService.addSupplier(supplier);
        });
        assertEquals("Invalid State please select correct state", ex.getMessage());
    }

    @Test
    void testAddSupplier_BlankCity_ThrowsException() {
        Supplier supplier = getValidSupplier();
        supplier.setCity(" ");

        Exception ex = assertThrows(SupplierExceptions.class, () -> {
            supplierService.addSupplier(supplier);
        });
        assertEquals("invalid city please select city correctly", ex.getMessage());
    }

    @Test
    void testAddSupplier_BlankAddress_ThrowsException() {
        Supplier supplier = getValidSupplier();
        supplier.setAddress("");

        Exception ex = assertThrows(SupplierExceptions.class, () -> {
            supplierService.addSupplier(supplier);
        });
        assertEquals("Invalid Supplier address", ex.getMessage());
    }

    @Test
    void testAddSupplier_BlankCreatedBy_ThrowsException() {
        Supplier supplier = getValidSupplier();
        supplier.setCreatedBy(" ");

        Exception ex = assertThrows(SupplierExceptions.class, () -> {
            supplierService.addSupplier(supplier);
        });
        assertEquals("please mention who is creating this", ex.getMessage());
    }

    @Test
    void testGetSuppliers() {
        Supplier supplier = getValidSupplier();
        when(supplierDao.getSuppliers(null)).thenReturn(List.of(supplier));

        List<Supplier> result = supplierService.getSuppliers(null);
        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getName());
    }
}
 