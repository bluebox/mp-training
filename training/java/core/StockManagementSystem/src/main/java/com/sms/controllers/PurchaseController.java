package com.sms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sms.models.PurchaseDetails;
import com.sms.models.PurchaseHeader;
import com.sms.service.Interfaces.PurchaseServices;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/purchase")
//@CrossOrigin("http://localhost:5173/")
public class PurchaseController {

    
    private PurchaseServices psi;
    @Autowired
	public PurchaseController(PurchaseServices psi) {
		super();
		this.psi = psi;
	}
    

    @PostMapping
    public ResponseEntity<PurchaseHeader> createPurchase(@Valid @RequestBody PurchaseHeader purchaseHeader) {
//    	System.out.println(purchaseHeader);
//    	System.out.println(purchaseHeader);
        PurchaseHeader createdPurchase = psi.createPurchase(purchaseHeader);
        return ResponseEntity.ok(createdPurchase);
       
    }
    
    
    @GetMapping
    public ResponseEntity<List<PurchaseHeader>> getAllPurchases() {
        List<PurchaseHeader> purchases = psi.viewAllPurchases();
        return ResponseEntity.ok(purchases);
    }
    
    
    @GetMapping("/{purchaseId}")
    public ResponseEntity<List<PurchaseDetails>> getPurchaseDetails(  @Valid @PathVariable Long purchaseId) {
        List<PurchaseDetails> details = psi.viewPurchaseInDetail(purchaseId);
        return ResponseEntity.ok(details);
    }
}

