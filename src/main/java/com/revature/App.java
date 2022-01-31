package com.revature;

import com.revature.controller.Controller;
import com.revature.controller.LoginController;
import com.revature.controller.ReimbursementController;
import com.revature.controller.UserController;
import com.revature.model.UserRole;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class App {

    private static Logger log = LoggerFactory.getLogger(App.class);
    private static Javalin javalinApp;

    public static void main(String args[]){
        log.info("Application Starting");

        javalinApp = Javalin.create(config -> {
            config.addStaticFiles("./src/main/resources/html", Location.EXTERNAL);
            config.accessManager((handler, ctx, routeRoles) -> {

                //GENERAL ACCESS MANAGER

                //if the user does not have a session deny request unless it's for the login or logout or open endpoints
                ArrayList<String> openList = new ArrayList<>();
                openList.add("/login");
                openList.add("/logout");
                openList.add("/users/new");
                openList.add("/reimbursements");


                if(ctx.req.getSession(false)==null && !openList.contains(ctx.path())){
                    ctx.status(401);
                    return;
                }

                //CHECKING USERS ACCESS LEVEL
                UserRole userRole = UserRole.EMPLOYEE;
                try{
                    userRole = (UserRole)(ctx.req.getSession(false).getAttribute("userRole"));
                }
                catch (Exception e){
                    //attribute "accessLevel" didn't exist or is not an Account object
                }



                //if user is an MANAGER
                if(userRole.equals(UserRole.MANAGER)) {
                    handler.handle(ctx);
                }
                //if user is EMPLOYEE and route is for EMPLOYEE
                else if(userRole.equals(UserRole.EMPLOYEE) && routeRoles.contains(UserRole.EMPLOYEE)){
                    handler.handle(ctx);
                }
                else {
                    ctx.status(403).result("Forbidden");
                }

            });
        }


        );

        configureRoutes(new LoginController(), new ReimbursementController(), new UserController());

        javalinApp.start(7000);

    }

    private static void configureRoutes(Controller... controllers){
        for(Controller c : controllers){
            c.addRoutes(javalinApp);
        }
    }

}
