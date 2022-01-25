package com.revature.service;

import com.revature.model.User;
import com.revature.repository.UserDAO;
import com.revature.repository.UserDAOImpl;

public class LoginService {

    private UserDAO userDAO = new UserDAOImpl();

    public LoginService() {
    }

    public LoginService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User login(String username, String password){
        return null;
    }


}
