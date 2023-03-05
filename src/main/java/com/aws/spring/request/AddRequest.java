package com.aws.spring.request;

import lombok.Data;

@Data
public class AddRequest {
    private String name;
    private int amount;
}
