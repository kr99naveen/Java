package com.naveen.journalApp.controller;

import com.naveen.journalApp.entity.User;
import com.naveen.journalApp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){
        System.out.println("creating user ::::: "+user);
        userService.saveNewEntry(user);
    }

    @GetMapping("health")
    public String healthCheck(){
        return "OK";
    }


    @GetMapping("csrf")
    public CsrfToken getCsrf(HttpServletRequest request){
        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
        return token;
    }
}
