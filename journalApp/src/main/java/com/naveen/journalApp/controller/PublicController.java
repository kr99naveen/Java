package com.naveen.journalApp.controller;

import com.naveen.journalApp.entity.User;
import com.naveen.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){
        System.out.println("creating user ::::: "+user);
        userService.saveEntry(user);
    }

    @RequestMapping("health")
    public String healthCheck(){
        return "OK";    
    }
}
