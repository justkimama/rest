package com.aws.spring.request;

import com.aws.spring.entity.Inventory;
import lombok.Data;

@Data
public class PriceRequest {
    private double sales;
    public PriceRequest (Inventory inventory) {
        sales = inventory.getPrice();
    }
}
