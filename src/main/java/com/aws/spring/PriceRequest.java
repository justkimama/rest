package com.aws.spring;

import lombok.Data;

@Data
public class PriceRequest {
    private double sales;
    public PriceRequest (Inventory inventory) {
        sales = inventory.getPrice();
    }
}
