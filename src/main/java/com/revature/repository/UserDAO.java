package com.revature.repository;

import com.revature.model.User;

import java.util.ArrayList;

public interface UserDAO {
    ArrayList<User> getAllUsers();

    User getUser(String username);

    User getUserById(int userId);

    boolean updateUser(User user);

    boolean addUser(User user);

    boolean deleteUser(String username);
}
