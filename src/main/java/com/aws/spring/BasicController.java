package com.aws.spring;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasicController {
    @GetMapping("/")
    public ResponseEntity home (){
        return ResponseEntity.ok("AWS");
    }
    @GetMapping("/secret")
    public ResponseEntity secret (){
        return ResponseEntity.ok("SUCCESS");
    }
}