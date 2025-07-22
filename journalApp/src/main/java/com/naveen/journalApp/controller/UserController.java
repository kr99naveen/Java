package com.naveen.journalApp.controller;

import com.naveen.journalApp.entity.JournalEntry;
import com.naveen.journalApp.entity.User;
import com.naveen.journalApp.service.JournalEntryService;
import com.naveen.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userService.getAll();
    }

//    @PutMapping("/{userName}")
//    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName){
//        User userInDb = userService.findByUserName(userName);
//        if(userInDb!=null){
//            userInDb.setUserName(user.getUserName());
//            userInDb.setPassword((user.getPassword()));
//            userService.saveEntry((userInDb));
//        }
//
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.out.println("updating user details :::::::::::::::::::::::::::");
        User userInDb = userService.findByUserName(username);
        if(userInDb!=null){
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword((user.getPassword()));
            userService.saveEntry((userInDb));
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
