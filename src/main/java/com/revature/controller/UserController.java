package com.revature.controller;

import com.revature.App;
import com.revature.model.User;
import com.revature.model.UserDTO;
import com.revature.model.UserRole;
import com.revature.service.LoginService;
import com.revature.service.UserService;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserController extends Controller{


    private UserService userService = new UserService();
    private static Logger log = LoggerFactory.getLogger(App.class);

    @Override
    public void addRoutes(Javalin app) {

        //create a new account
        app.post("/users/new", ctx -> {
            log.info("received /users/new request");
            UserDTO userDTO = ctx.bodyAsClass(UserDTO.class);
            if (userService.addUser(userDTO)) {
                ctx.status(201);
            } else {
                ctx.status(400);
            }
        }, UserRole.EMPLOYEE);


    }
}
