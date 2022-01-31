package com.revature;

import com.revature.controller.Controller;
import com.revature.controller.LoginController;
import com.revature.controller.ReimbursementController;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    private static Logger log = LoggerFactory.getLogger(App.class);
    private static Javalin javalinApp;

    public static void main(String args[]){
        log.info("Application Starting");

        javalinApp = Javalin.create();
        configureRoutes(new LoginController(), new ReimbursementController());

        javalinApp.start(7000);

    }

    private static void configureRoutes(Controller... controllers){
        for(Controller c : controllers){
            c.addRoutes(javalinApp);
        }
    }

}
