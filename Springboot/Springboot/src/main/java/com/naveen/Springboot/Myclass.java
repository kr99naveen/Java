package com.naveen.Springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("spring")
public class Myclass {

    @GetMapping("hello")
    public String sayHello(){
        return "Hello";
    }
}
