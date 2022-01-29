package com.revature.controller;

import com.revature.App;
import com.revature.model.UserDTO;
import com.revature.service.LoginService;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginController extends Controller {

    private LoginService loginService = new LoginService();
    private static Logger log = LoggerFactory.getLogger(App.class);

    private Handler loginAttempt = ctx -> {
        UserDTO userDTO = ctx.bodyAsClass(UserDTO.class);
        if(loginService.login(userDTO.username, userDTO.password) != null){
            ctx.req.getSession();
            ctx.status(200);
        }else {
            ctx.req.getSession().invalidate();
            ctx.status(401);
        }
    };

    private final Handler loginout = ctx -> {
        if (ctx.req.getSession(false) != null) {
            ctx.req.getSession().invalidate();
            ctx.status(200);
        } else {
            ctx.status(400);
        }
    };

    @Override
    public void addRoutes(Javalin app) {
        app.post("/login",this.loginAttempt);
        app.post("/logout", loginout);
    }
}
