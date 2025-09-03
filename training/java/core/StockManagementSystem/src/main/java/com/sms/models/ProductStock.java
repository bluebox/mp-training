package com.sms.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductStock {

    
    private String productId;  // Assuming product_id is a VARCHAR(15)

    private String name;
    private long totalQty;

}

