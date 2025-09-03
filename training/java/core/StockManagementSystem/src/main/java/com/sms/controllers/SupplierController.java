package com.sms.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sms.models.Supplier;
import com.sms.service.Interfaces.SupplierService;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/suppliers")
//@CrossOrigin("http://localhost:5173/")
public class SupplierController {
	@Autowired
	private  SupplierService psi;
//    @Autowired
//	public SupplierController(SupplierService psi) {
//		this.psi = psi;
//	}
    @PostMapping
    public ResponseEntity<?> newSupplier(@Valid @RequestBody Supplier supplier, BindingResult result) {
    	//System.out.println(supplier);
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        Long id = psi.addSupplier(supplier);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }
    
    @GetMapping
    public List<Supplier> getSuppliers(@RequestParam(required = false) String searchKey) {
        return psi.getSuppliers(searchKey);
    }
    

    

}
