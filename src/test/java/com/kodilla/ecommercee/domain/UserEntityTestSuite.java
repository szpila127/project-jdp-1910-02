package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.service.UserService;
import org.h2.tools.Server;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserEntityTestSuite {

    @Autowired
    private UserService userService;

    @BeforeClass
    public static void initTest() throws SQLException {
        Server webServer = Server.createWebServer("-web",
                "-webAllowOthers", "-webPort", "8082");
        webServer.start();
    }

    @Test
    public void testNewUser() {
        //Given
        User tom = new User("Tom",1L,false);
        userService.saveUser(tom);
        //When
        long checkId = tom.getUserId();
        //Then
        Assert.assertEquals(1,checkId);
    }

    @Test
    public void testGetAllUsers() {
        //Given
        User john = new User("John",false);
        User rick = new User("Rick",false);
        User piotr = new User("Piotr",false);
        userService.saveUser(john);
        userService.saveUser(rick);
        userService.saveUser(piotr);
        //When
        List<User> expectedResult = userService.getAllUsers();
        //Then
        Assert.assertEquals(3,expectedResult.size());
    }
    @Test
    public void findUserTest() {
        //Given
        User john = new User("John",false);
        User rick = new User("Rick",false);
        User piotr = new User("Piotr",false);
        userService.saveUser(john);
        userService.saveUser(rick);
        userService.saveUser(piotr);
        //When
        long isRick = rick.getUserId();
        long findRick = userService.findUser(isRick).get().getUserId();
        //Then
        Assert.assertEquals(isRick,findRick);
    }

}
