package com.aws.spring;

import lombok.Data;

@Data
public class PriceRequest {
    private double sales;   //return 100.0
    public PriceRequest (Inventory inventory) {
        sales = inventory.getPrice();
    }
}
