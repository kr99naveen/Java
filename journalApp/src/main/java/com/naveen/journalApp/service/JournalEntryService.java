package com.naveen.journalApp.service;

import com.naveen.journalApp.entity.JournalEntry;
import com.naveen.journalApp.entity.User;
import com.naveen.journalApp.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class JournalEntryService {


    // dependency injection that JournalClassRepo is component (IOS container), interface will be
    // converted to implementation at runtime by spring itself
    @Autowired
    private JournalEntryRepo  journalEntryRepo;

    @Autowired
    private UserService userService;

    //logger, every class has there own logger, so private,
    //for refrain for accidental reassignment, keep it final
    //and just one instance required for JournalEntryService instance, so static
    //every logger is associated with some class, so we gave the class
    //Slf4j is logging abstraction framework of logback || Simple logging facade for java facade=>abstraction
    private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);

    @Transactional
    public void saveEntry(JournalEntry journalEntry,  String userName){
        try {
            User user = userService.findByUserName(userName);
            JournalEntry saved = journalEntryRepo.save(journalEntry);
            ObjectId prevId = saved.getId();
            List<JournalEntry> checkEntry = user.getJournalEntries().stream().filter(x -> x.getId().equals(prevId)).collect(Collectors.toList());
            if(!checkEntry.isEmpty()){
                user.getJournalEntries().removeIf(element -> element.getId().equals(checkEntry.get(0).getId()));
            }
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
        } catch (Exception e) {
//            throw new RuntimeException("Exception while creating journal entry ::: ",e);
        }
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepo.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName){
        try {
            User user = userService.findByUserName(userName);
            boolean removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if(removed){
                userService.saveUser(user);
                journalEntryRepo.deleteById(id);
            }
            return removed;
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error while deleting the journal");
        }
    }

//    public List<JournalEntry> findByUsername()
}


// flow :::::::::::::::::::
// controller ---> service ---> repository
