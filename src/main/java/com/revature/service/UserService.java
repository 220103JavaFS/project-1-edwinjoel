package com.revature.service;

import com.revature.model.Status;
import com.revature.model.User;
import com.revature.model.UserDTO;
import com.revature.model.UserRole;
import com.revature.repository.UserDAO;
import com.revature.repository.UserDAOImpl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserService {

    private UserDAO userDAO = new UserDAOImpl();

    public UserService() {
    }

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    public boolean addUser(UserDTO userDTO) {
        User user = new User();

        //Get a hash of the given password
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        byte[] hash = messageDigest.digest(userDTO.getPassword().getBytes(StandardCharsets.UTF_8));

        UserRole userRole = null;
        try {
            userRole = UserRole.valueOf(userDTO.getUserRoleString());
        }
        catch (IllegalArgumentException e){
            return false;
        }
        catch (NullPointerException e){
            return false;
        }

        switch(userRole){
            case EMPLOYEE:
                user.setRoleId(1);
                break;
            case MANAGER:
                user.setRoleId(2);
                break;
            default:
                return false;
        }

        user.setUsername(userDTO.getUsername());
        user.setHash(hash);
        user.setRole(userRole);
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());

        return userDAO.addUser(user);
    }
}
