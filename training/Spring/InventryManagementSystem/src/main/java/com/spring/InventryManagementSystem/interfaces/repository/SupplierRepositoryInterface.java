package com.spring.InventryManagementSystem.interfaces.repository;

import java.sql.SQLException;
import java.util.List;

import com.spring.InventryManagementSystem.domain.Supplier;

public interface SupplierRepositoryInterface {

	List<Supplier> getAllSuppliers()  throws SQLException;

}
