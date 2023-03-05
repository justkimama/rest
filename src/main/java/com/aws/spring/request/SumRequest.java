package com.aws.spring.request;

import com.aws.spring.entity.Inventory;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonProperty;

@Data
public class SumRequest {
    private String name;
    private int amount;
    public SumRequest (Inventory inventory) {
        name = inventory.getName();
        amount = inventory.getAmount();
    }
}
