package com.aws.spring;

import com.aws.spring.Inventory;
import lombok.Data;

@Data
public class SumRequest {
    private String name;
    private int amount;
    public SumRequest (Inventory inventory) {
        name = inventory.getName();
        amount = inventory.getAmount();
    }
}
