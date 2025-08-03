//playing with query and criteria ::: criteria API
//query method DSL has some boundation on naming syntax of methods, using

package com.naveen.journalApp.repository;

import com.naveen.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepoImpl {

    //MongoTemplate providing interface to interact with DB
    //without going to inner implementation
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUserForSA(){

        Query query = new Query();
//        query.addCriteria(Criteria.where("userName").is("vipul"));
        query.addCriteria((Criteria.where("email").exists(true)));
        query.addCriteria((Criteria.where("email").ne(null).ne("")));
        query.addCriteria(Criteria.where("email").regex("/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$/"));
        query.addCriteria((Criteria.where("sentimentAnalysis").is(true)));
        query.addCriteria((Criteria.where("roles").in("USER","ADMIN")));

        //there are many different methods like sequelize ORM in nest js, PLEASE EXPLORE :-)



        List<User> users = mongoTemplate.find(query,User.class);
        return users;
    }
}
