package com.revature.service;

import com.revature.model.User;
import com.revature.repository.UserDAO;
import com.revature.repository.UserDAOImpl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginService {

    private UserDAO userDAO = new UserDAOImpl();

    public LoginService() {
    }

    public LoginService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User login(String username, String password){
        //Get the user from the DAO
        User user = userDAO.getUser(username);
        if(user == null){
            return null;
        }

        //Get a hash of the given password
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        byte[] hash = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));

        //Compare the given password's hash to the hash of the user object retrieved from the database
        if(compareHash(user.getHash(), hash)){
            return user;
        }
        else {
            return null;
        }
    }


    //Compares two byte arrays
    private static boolean compareHash(byte[] hash1, byte[] hash2){
        if(hash1.length != hash2.length){
            return false;
        }
        for(int i = 0; i<hash1.length; i+=1){
            if(hash1[i] != hash2[i]){
                return false;
            }
        }
        return true;
    }


}
