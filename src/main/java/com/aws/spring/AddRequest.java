package com.aws.spring;

import lombok.Data;

@Data
public class AddRequest {
    private String name;
    private int amount;
}
