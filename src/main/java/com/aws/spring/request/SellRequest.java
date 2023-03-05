package com.aws.spring.request;

import com.aws.spring.entity.Inventory;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SellRequest {
    private String name;
    private String amount;
    private String price;
}
