package com.aws.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
