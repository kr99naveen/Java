package com.naveen.journalApp.service;

import com.naveen.journalApp.entity.JournalEntry;
import com.naveen.journalApp.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class JournalEntryService {


    // dependency injection that JournalClassRepo is component (IOS container), interface will be
    // converted to implementation at runtime by spring itself
    @Autowired
    private JournalEntryRepo  journalEntryRepo;

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepo.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepo.findById(id);
    }

    public void deleteById(ObjectId id){
        journalEntryRepo.deleteById(id);
    }
}


// flow :::::::::::::::::::
// controller ---> service ---> repository
