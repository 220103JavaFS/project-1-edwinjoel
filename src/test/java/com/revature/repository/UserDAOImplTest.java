package com.revature.repository;

import com.revature.model.User;
import com.revature.model.UserRole;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOImplTest {

    private static UserDAO userDAO = new UserDAOImpl();

    private static User testUser = new User(
            5,
            "edwinfeliz",
            "password".getBytes(StandardCharsets.UTF_8),
            "Edwin",
            "Feliz",
            "edwinfeliz@yahoo.com",
            UserRole.EMPLOYEE);

    private static User testUser2 = new User(
            6,
            "edwinfeliz",
            "password".getBytes(StandardCharsets.UTF_8),
            "Edwin",
            "Feliz",
            "edwinfeliz@yahoo.com",
            UserRole.EMPLOYEE);

    @Test
    @Order(1)
    void testCreateUser(){
        assertTrue(userDAO.addUser(testUser));
    }

    @Test
    @Order(2)
    void testGetUser(){
        assertNotNull(userDAO.getUser(testUser.getUsername()));
        assertEquals(testUser.getUsername(), userDAO.getUser(testUser.getUsername()).getUsername());
    }

    @Test
    @Order(3)
    void testGetUserById(){
        assertNotNull(userDAO.getUser(testUser.getUsername()));
        assertEquals(testUser.getUsername(), userDAO.getUserById(testUser.getUserId()).getUsername());
    }

    @Test
    @Order(4)
    void testUpdateUser(){
        assertTrue(userDAO.updateUser(testUser, testUser2.getUserId()));
    }

    @Test
    @Order(5)
    void testDeleteUser(){
        assertTrue(userDAO.deleteUser(testUser.getUsername()));
        assertNull(userDAO.getUser(testUser.getUsername()));
    }

}
