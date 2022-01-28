package com.revature.repository;

import com.revature.model.User;
import com.revature.model.UserRole;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDAOImplTest {

    private static UserDAO userDAO = new UserDAOImpl();

    private static User testUser = new User(
            50000,
            "edwinfeliz",
            "password".getBytes(StandardCharsets.UTF_8),
            "Edwin",
            "Feliz",
            "edwinfeliz@yahoo.com",
            1,
            UserRole.EMPLOYEE);

    private static User testUser2 = new User(
            50001,
            "edwinfeliz2",
            "password".getBytes(StandardCharsets.UTF_8),
            "Edwin2",
            "Feliz2",
            "edwinfeliz2@yahoo.com",
            1,
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
        testUser = userDAO.getUser(testUser.getUsername());
    }

    @Test
    @Order(3)
    void testGetUserById(){
        assertNotNull(userDAO.getUserById(testUser.getUserId()));
        assertEquals(testUser.getUsername(), userDAO.getUserById(testUser.getUserId()).getUsername());
    }

    @Test
    @Order(4)
    void testUpdateUser(){
        assertTrue(userDAO.updateUser(testUser2, testUser.getUserId()));
        assertEquals(testUser2.getFirstName(), userDAO.getUserById(testUser.getUserId()).getFirstName());
    }

    @Test
    @Order(5)
    void testDeleteUser(){
        assertTrue(userDAO.deleteUser(testUser.getUsername()));
        assertTrue(userDAO.deleteUser(testUser2.getUsername()));
        assertNull(userDAO.getUser(testUser.getUsername()));
    }

}
