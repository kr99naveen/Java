package com.naveen.journalApp.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


// Document annotaion tells Spring that this is mapped entity with MongoDB Collectioni.e the Collection === table in NoSQL
// and the documemnt = row


@Document(collection = "users")
@Data  //Lombok data annotations, annotation that bundles several other Lombok annotations, including @Getter, @Setter, @ToString, @EqualsAndHashCode, and @RequiredArgsConstructor

@Builder
public class User {
    //unique key in entries, not manadatory, if not provided, db will create it
    @Id
    private ObjectId id;

    //configuring usrname as unique and not null, and for automatic creation of indexes we set configuration
    //spring.data.mongodb.auto-index-creation=true in application.properties
    @Indexed(unique = true)
    @NonNull
    private  String userName;

    private String email;

    private  boolean sentimentAnalysis;

    @NonNull
    private String password;

    private List<String> roles;


    //creating refernce to JournalEntry Table (Collections)
    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();
}
