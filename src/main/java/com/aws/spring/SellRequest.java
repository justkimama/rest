package com.aws.spring;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)  //return not null
public class SellRequest {
    private String name;
    private String amount;
    private String price;
}
