package com.banyuan.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class indexController {
    @GetMapping("/")
    String getIndex(){
        return "about";
    }
}

