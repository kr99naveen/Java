package com.naveen.journalApp.service;

import com.naveen.journalApp.api.response.WeatherResponse;
import com.naveen.journalApp.cache.AppCache;
import com.naveen.journalApp.constants.Placeholders;
import com.naveen.journalApp.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class WeatherService {

    //this Value annotation is for getting keys from .yml or application.properties file, just like envs in js
    @Value("${weatherApiKey}")
    private String apiKey;

//    private static final String weather_api ="https://api.weatherstack.com/current?access_key=API_KEY&query=CITY";
    //the rest template class in spring
    //using it that provide us to process http request
    //and bring response to us
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    AppCache appCache;

    @Autowired
    private RedisService redisService;


    //GET api call from spring
    public WeatherResponse getWeather(String city){
        WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);
        if(weatherResponse != null){
            return  weatherResponse;
        }else{
            String url = appCache.APP_CACHE.get(AppCache.keys.WEATHER_API.toString()).replace(Placeholders.CITY,city).replace(Placeholders.API_KEY,apiKey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(url, HttpMethod.GET,null, WeatherResponse.class);
            log.info("Response fetched from weather stack ::::"+response);
            WeatherResponse body = response.getBody();
            if(body!=null){
                redisService.set("weather_of_" + city, body, 300l);
            }
            return body;
        }
    }

    //POST api call from spring
    //just a smaple this function will not work as api is not valid

//    public WeatherResponse getWeatherPost(String city){
//        String url = api.replace("CITY",city).replace("API_KEY",apiKey);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set("key","value");
//        User user  = User.builder().userName("naveen").password("Admin@123").build();
//
//        HttpEntity<User> httpEntity = new HttpEntity<>(user, httpHeaders);
//        ResponseEntity<WeatherResponse> response = restTemplate.exchange(url, HttpMethod.POST,httpEntity, WeatherResponse.class);
//        log.info("Response fetched from weather stack ::::"+response);
//        WeatherResponse body = response.getBody();
//        return body;
//    }
}
