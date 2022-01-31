package com.revature.controller;

import com.revature.App;
import com.revature.model.User;
import com.revature.model.UserDTO;
import com.revature.model.UserRole;
import com.revature.service.LoginService;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginController extends Controller {

    private LoginService loginService = new LoginService();
    private static Logger log = LoggerFactory.getLogger(App.class);

    @Override
    public void addRoutes(Javalin app) {

        //login and get a session
        app.post("/login", ctx -> {
            log.info("received /login request");
            UserDTO userDTO = ctx.bodyAsClass(UserDTO.class);
            User user  = loginService.login(userDTO.getUsername(), userDTO.getPassword());
            if(user != null){
                ctx.req.getSession();
                ctx.req.getSession(false).setAttribute("user", user);
                ctx.req.getSession(false).setAttribute("userRole", user.getRoleId());
                ctx.req.getSession(false).setAttribute("userRoleId", user.getRoleId());
                ctx.req.getSession(false).setAttribute("userId", user.getUserId());
                ctx.status(200);
            }
            else {
                ctx.req.getSession().invalidate();
                ctx.status(400);
            }
        }, UserRole.EMPLOYEE);

        //logout
        app.get("/logout", ctx -> {
            log.info("received /logout request");
            if (ctx.req.getSession(false) != null) {
                ctx.req.getSession().invalidate();
                ctx.status(200);
            } else {
                ctx.status(400);
            }
        }, UserRole.EMPLOYEE);

    }
}
