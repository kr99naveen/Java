package com.naveen.journalApp.repository;


import com.naveen.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

//extending MongoRepository interface to provide structure and implementation
// here entity class as JournalEntry and key as String, the id or primary key
// that's all
public interface JournalEntryRepo extends MongoRepository<JournalEntry, ObjectId> {
}
