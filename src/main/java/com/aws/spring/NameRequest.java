package com.aws.spring;

import com.aws.spring.Inventory;
import lombok.Data;

@Data
public class NameRequest {
    public String name;
    public int amount;
    public NameRequest (Inventory inventory) {
        name = inventory.getName();
        amount = inventory.getAmount();
    }
}
