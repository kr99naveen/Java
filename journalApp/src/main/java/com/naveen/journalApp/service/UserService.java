package com.naveen.journalApp.service;

import com.naveen.journalApp.entity.JournalEntry;
import com.naveen.journalApp.entity.TestUser;
import com.naveen.journalApp.entity.User;
import com.naveen.journalApp.entity.enums.Role;
import com.naveen.journalApp.repository.JournalEntryRepo;
import com.naveen.journalApp.repository.TestUserRepo;
import com.naveen.journalApp.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
//usingg slf4j annotation we do not need to write this again n again
//private static final Logger logger = LoggerFactory.getLogger(UserService.class);
//and instance created is as 'log'
//use it like : log.error("Error occured for {}:::: ",user.getUserName(),e);

@Slf4j
public class UserService {


    // dependency injection that JournalClassRepo is component (IOS container), interface will be
    // converted to implementation at runtime by spring itself
    @Autowired
    private UserRepo userRepo;

//    injecting bean by constructor, more control
    private final TestUserRepo testUserRepo;
    public UserService(TestUserRepo testUserRepo) {
        this.testUserRepo = testUserRepo;
    }

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public void saveNewEntry(User user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            if(user.getJournalEntries() == null){
                user.setJournalEntries(new ArrayList<>());
            }
            userRepo.save(user);
            TestUser u = new TestUser();
            u.setName(user.getUserName());
            u.setRole(Role.USER);
            this.testUserRepo.save(u);
        } catch (Exception e) {
//            logger.error("Error occured for {}:::: ",user.getUserName(),e);
            log.error("Error occured for {}:::: ",user.getUserName(),e);

//            logger.warn("warning triggered :: ",e);
//            logger.info("hahahahaahahahahaah info");
            log.debug("hahahahaahahahahaah debug");
//            logger.trace("hahahahaahahahahaah trace");
//            throw new RuntimeException(e);
        }
    }
    public void saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepo.save(user);
    }

    public void saveUser(User user){
        userRepo.save(user);
    }

    public List<User> getAll(){
        return userRepo.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepo.findById(id);
    }

    public void deleteById(ObjectId id){
        userRepo.deleteById(id);
    }

    public User findByUserName(String userName){
        return userRepo.findByUserName(userName);
    }

    public Map<String,Object> getTestUser(String username){
        TestUser user = testUserRepo.findByName(username);
        return Map.of(
                "username", user.getName(),
                "email", user.getRole()
        );
    }
}


// flow :::::::::::::::::::
// controller ---> service ---> repository
