package com.naveen.journalApp.repository;
import com.naveen.journalApp.entity.TestUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestUserRepo extends JpaRepository<TestUser, Long> {
    TestUser findByName(String name);
}
