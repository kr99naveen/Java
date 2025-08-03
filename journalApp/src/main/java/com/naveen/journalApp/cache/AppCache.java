package com.naveen.journalApp.cache;

import com.naveen.journalApp.entity.ConfigJournalAppEntity;
import com.naveen.journalApp.repository.ConfigJournalAppRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{
        WEATHER_API;
    }

    public Map<String, String> APP_CACHE;
    @Autowired
    private ConfigJournalAppRepo configJournalAppRepo;

    //this annotation i.e @PostConstruct tell spring to invoke this function
    //as soon as the bean of this class is made
    @PostConstruct
    public void init(){

        APP_CACHE = new HashMap<>();
        List <ConfigJournalAppEntity> all = configJournalAppRepo.findAll();
        for(ConfigJournalAppEntity configJournalAppEntity : all){
            APP_CACHE.put(configJournalAppEntity.getKey(),configJournalAppEntity.getValue());
        }
    }
}
