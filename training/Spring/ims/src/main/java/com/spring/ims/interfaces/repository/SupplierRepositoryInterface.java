package com.spring.ims.interfaces.repository;

import java.util.List;

public interface SupplierRepositoryInterface {

    List<String> listOfSuppliers();

    String supplierName(int supplierId);
}

