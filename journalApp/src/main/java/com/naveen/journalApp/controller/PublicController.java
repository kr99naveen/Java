package com.naveen.journalApp.controller;

import com.naveen.journalApp.common.response.ApiResponse;
import com.naveen.journalApp.entity.User;
import com.naveen.journalApp.service.RedisService;
import com.naveen.journalApp.service.UserDetailsServiceImpl;
import com.naveen.journalApp.service.UserService;
import com.naveen.journalApp.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisService redisService;

    @Value("${profile_key}")
    private String profileKey;

    @PostMapping("/signup")
    public void signup(@RequestBody User user){
        System.out.println("creating user ::::: "+user);
        userService.saveNewEntry(user);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody User user){
        try{
            System.out.println("loggin in user :::: ");
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            redisService.set(userDetails.getUsername()+"_token",jwt,(long)60*10);
            Map<String, String> token = Map.of("token", jwt);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true,"Login Successfull", token));
        }
        catch (Exception e){
            log.error("exception while loggin in :: ",e.getMessage());
//            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, "Incorrect username or password"));

        }
    }

    @GetMapping("health")
    public ResponseEntity<?> healthCheck(){
        System.out.println("value of key :::: "+profileKey);
        return ResponseEntity.ok(new ApiResponse<>(true,"OK"));
    }


    @GetMapping("csrf")
    public CsrfToken getCsrf(HttpServletRequest request){
        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
        return token;
    }
}
