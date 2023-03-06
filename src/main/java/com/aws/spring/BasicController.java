package com.aws.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasicController {
    @GetMapping("/")
    public String home (){
        return "AWS";
    }
    @GetMapping("/secret")
    public String secret (){
        return "SUCCESS";
    }
}
