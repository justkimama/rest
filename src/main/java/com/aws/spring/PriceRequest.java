package com.aws.spring;

import com.aws.spring.Inventory;
import lombok.Data;

@Data
public class PriceRequest {
    private double sales;
    public PriceRequest (Inventory inventory) {
        sales = inventory.getPrice();
    }
}
