package com.naveen.journalApp.repository;


import com.naveen.journalApp.entity.JournalEntry;
import com.naveen.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.swing.*;

//extending MongoRepository interface to provide structure and implementation
// here entity class as JournalEntry and key as String, the id or primary key
// that's all
public interface UserRepo extends MongoRepository<User, ObjectId> {
    User findByUserName(String username);
}
