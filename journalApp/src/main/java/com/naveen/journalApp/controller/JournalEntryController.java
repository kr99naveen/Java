package com.naveen.journalApp.controller;

import com.naveen.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class    JournalEntryController {

    private Map<Long, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll(){
        System.out.println("getting all journals");
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry){
        System.out.println("creating journal");
        journalEntries.put(myEntry.getId(), myEntry);
        System.out.println("Journal created!!!!");
        return true;
    }

    @GetMapping("{myId}")
    public JournalEntry getJournalById(@PathVariable long myId){
        return journalEntries.get(myId);
    }

    @DeleteMapping("{myId}")
    public JournalEntry deleteJournalById(@PathVariable long myId){
        return journalEntries.remove(myId);
    }

    @PutMapping("{myId}")
    public JournalEntry updateJournalById(@PathVariable long myId, @RequestBody JournalEntry myEntry){
        return journalEntries.put(myId, myEntry );
    }

}
