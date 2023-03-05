package com.aws.spring.request;

import com.aws.spring.entity.Inventory;
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
