package com.revature.controller;

import com.revature.App;
import com.revature.model.User;
import com.revature.service.LoginService;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

public class LoginController extends Controller {

    private LoginService loginService = new LoginService();
    private static Logger log = LoggerFactory.getLogger(App.class);

    private Handler loginAttempt = ctx -> {
        User user = ctx.bodyAsClass(User.class);
        if(loginService.login(user.getUsername(), )){
            ctx.req.getSession();
            ctx.status(200);
        }else {
            ctx.req.getSession().invalidate();
            ctx.status(401);
        }
    };

    @Override
    public void addRoutes(Javalin app) {
        app.post("/login",this.loginAttempt);
        //app.get("/logout");
    }
}
