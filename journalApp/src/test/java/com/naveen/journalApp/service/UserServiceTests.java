package com.naveen.journalApp.service;

import com.naveen.journalApp.entity.User;
import com.naveen.journalApp.repository.UserRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

//this annotation to tell spring to context outside main appliaction
//other scanning will not be done and so not beans will be provided even if
//class declared as @Component
@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepo userRepo;

    //annotation for telling spring that this method is
    //for testing
    @Test
    public void testAdd(){
        assertEquals(4,2+2);
    }

    @Disabled  //to disable the test
    @Test
    public void testFindByUserName(){
        assertNotNull(userRepo.findByUserName("abcd"));
        assertTrue(5>3);
        User user = userRepo.findByUserName("naveen");
        assertTrue(!user.getJournalEntries().isEmpty());
    }


    @ParameterizedTest
    @ValueSource(strings = {
            "naveen",
            "admin",
//            "kumar"
    })
    public void testFindByUserName(String name){
        assertNotNull(userRepo.findByUserName(name),"failed for ::: " + name);
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
//            "3,3,9"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected,a+b, "failed for :: "+a+", "+b+", "+expected);
    }

    /*
    there are different test annotation as per requirements
    @BeforeEach
    @BeforeAll
    @AfterEach
    @AfterAll
    ===============================
    Meanings are clear as with name.
     */
}
