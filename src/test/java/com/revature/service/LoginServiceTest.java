package com.revature.service;

import com.revature.model.User;
import com.revature.model.UserRole;
import com.revature.repository.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

public class LoginServiceTest {

    private LoginService testService;

    @Mock
    private UserDAO testDAO;

    private User testUser = new User();



    @BeforeEach
    public void setup(){
        testUser.setUserId(100);
        testUser.setEmail("joesmith@gmail.com");
        testUser.setFirstName("Joe");
        testUser.setLastName("Smtih");
        testUser.setUsername("jsmith1");
        testUser.setRole(UserRole.EMPLOYEE);
        testUser.setHash("password".getBytes(StandardCharsets.UTF_8));

        MockitoAnnotations.openMocks(this);
        testService = new LoginService(testDAO);

        Mockito.when(testDAO.getUser("jsmith1")).thenReturn(testUser);


    }

    @Test
    public void testLoginSuccess(){
        User user = testService.login("jsmith1", "password");
        assertEquals("Joe", user.getFirstName());
        assertEquals(UserRole.EMPLOYEE, user.getRole());

    }

    @Test void testLoginFailPassword(){
        User user = testService.login("jsmith1", "wrongpassword");
        assertNotEquals("Joe", user.getFirstName());
        assertNotEquals("Smith", user.getLastName());
    }

    @Test void testLoginFailUsername(){
        User user = testService.login("Notjsmith1", "password");
        assertNull(user);
    }

    @Test void testLoginFailBoth(){
        User user = testService.login("Notjsmith1", "wrongpassword");
        assertNull(user);
    }

}
