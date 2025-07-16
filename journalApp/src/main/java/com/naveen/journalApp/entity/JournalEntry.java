package com.naveen.journalApp.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;


// Document annotaion tells Spring that this is mapped entity with MongoDB Collectioni.e the Collection === table in NoSQL
// and the documemnt = row


@Document(collection = "journal_entries")
@Data
public class JournalEntry {
    //unique key in entries, not manadatory, if not provided, db will create it
    @Id
    private ObjectId id;
    private  String title;
    private String content;
    private LocalDateTime date;
}
