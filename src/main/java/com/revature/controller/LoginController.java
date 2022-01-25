package com.revature.controller;

import com.revature.App;
import com.revature.service.LoginService;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginController extends Controller {

    private LoginService loginService = new LoginService();
    private static Logger log = LoggerFactory.getLogger(App.class);


    @Override
    public void addRoutes(Javalin app) {

    }
}
