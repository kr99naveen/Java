package com.naveen.journalApp.controller;

import com.naveen.journalApp.api.response.WeatherResponse;
import com.naveen.journalApp.entity.JournalEntry;
import com.naveen.journalApp.entity.User;
import com.naveen.journalApp.repository.UserRepo;
import com.naveen.journalApp.service.JournalEntryService;
import com.naveen.journalApp.service.UserService;
import com.naveen.journalApp.service.WeatherService;
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

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private WeatherService weatherService;

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
            userService.saveNewEntry((userInDb));
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteByUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        userRepo.deleteByUserName(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("greeting")
    public  ResponseEntity<?> greeting(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWeather("Delhi");
        String greeting = "";
        if(weatherResponse!=null) {
            greeting = ", Weather feels like " + weatherResponse.getCurrent().getFeelslike();
        }

        return new ResponseEntity<>("Hi "+authentication.getName()+greeting, HttpStatus.OK);
    }

}
