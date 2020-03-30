package com.banyuan.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BlogController {

    @GetMapping("blogs/{username}")
    @ResponseBody
    public String showBlogsByUserName(@PathVariable("username") String username){
        return "list";
    }
}
