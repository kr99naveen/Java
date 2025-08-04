package com.naveen.journalApp.scheduler;

import com.naveen.journalApp.cache.AppCache;
import com.naveen.journalApp.entity.JournalEntry;
import com.naveen.journalApp.entity.User;
import com.naveen.journalApp.repository.UserRepoImpl;
import com.naveen.journalApp.service.EmailService;
import com.naveen.journalApp.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepoImpl userRepoImpl;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    private AppCache appCache;


    //this annotation is basically doing the job of scheduling the task
    //THE CRON , derived from cronos, meaning time
    @Scheduled(cron = "0 0 9 * * SUN")     //Every Sunday 9 AM
//    @Scheduled(cron = "0 * * ? * *")      //every minute
    public void fetchUserAndSendMail(){
        List<User> users = userRepoImpl.getUserForSA();
        for(User user : users){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<String> filteredEntries = journalEntries.stream().filter(x-> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x->x.getContent()).collect(Collectors.toList());

            String entry = String.join(" ", filteredEntries);

            String sentiment = sentimentAnalysisService.getSentiment(entry);

            emailService.sendEmail(user.getEmail(),"sentiment for last 7 days", sentiment);
        }
    }

    @Scheduled(cron = "0 0/10 * ? * *")
    public  void  clearAppCache(){
        appCache.init();
    }
}
