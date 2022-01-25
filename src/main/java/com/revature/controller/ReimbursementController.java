package com.revature.controller;

import com.revature.App;
import com.revature.service.ReimbursementService;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReimbursementController extends Controller{

    private ReimbursementService reimbursementService = new ReimbursementService();
    private static Logger log = LoggerFactory.getLogger(App.class);


    @Override
    public void addRoutes(Javalin app) {

    }
}
