package com.naveen.journalApp;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
//this annotation is added to manage transactions in functions
//where transactional queries are made like deleteById in JournalEntryService
@EnableTransactionManagement
//this annotaion for enabling the scheduling of the cron jobs
@EnableScheduling
//@Profile("dev")
public class JournalApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(JournalApplication.class, args);
		ConfigurableEnvironment environment = context.getEnvironment();
		System.out.println("Application started ::: "+ Arrays.toString(environment.getActiveProfiles()));
	}

	@Bean
	public PlatformTransactionManager mongoTxnManager(MongoDatabaseFactory dbfactory){
		return new MongoTransactionManager(dbfactory);
	}

	@Bean("transactionManager")
	public PlatformTransactionManager jpaTxnManager(EntityManagerFactory dbfactory){
		return new JpaTransactionManager(dbfactory);
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
