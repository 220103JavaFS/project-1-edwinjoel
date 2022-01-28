package com.revature.controller;

import com.revature.App;
import com.revature.model.ReimbursementDTO;
import com.revature.model.Status;
import com.revature.service.ReimbursementService;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReimbursementController extends Controller{

    private ReimbursementService reimbursementService = new ReimbursementService();
    private static Logger log = LoggerFactory.getLogger(App.class);

    Handler getAllReimbursements = ctx ->{
        if(ctx.req.getSession(false)!=null) { //getSession(false) will only return a Session object if the client
                                                    //sent a cookie along with the request that matches an open session.
            ctx.json(reimbursementService.getAllReimbursements());
            ctx.status(200);
        }else {
            ctx.status(401);
        }
    };

    Handler getReimbursements = ctx ->{
        if(ctx.req.getSession(false)!=null) { //getSession(false) will only return a Session object if the client
            //sent a cookie along with the request that matches an open session.
            ctx.json(reimbursementService.getAllReimbursements());
            ctx.status(200);
        }else {
            ctx.status(401);
        }
    };

    Handler getStatus = ctx ->{
        if(ctx.req.getSession(false)!=null) { //getSession(false) will only return a Session object if the client
            //sent a cookie along with the request that matches an open session.
            Status status = ctx.bodyAsClass(Status.class);
            ctx.json(reimbursementService.getAllReimbursementsByStatus(status));
            ctx.status(200);
        }else {
            ctx.status(401);
        }
    };

    Handler updateReimbursement = ctx ->{
        if(ctx.req.getSession(false)!=null) { //getSession(false) will only return a Session object if the client
            //sent a cookie along with the request that matches an open session.
            ReimbursementDTO reimbursement = ctx.bodyAsClass(ReimbursementDTO.class);
            if(reimbursementService.updateReimbursement(reimbursement, reimbursement.getId())){
                ctx.status(202);
            }else {
                ctx.status(400);
            }
        }else {
            ctx.status(401);
        }
    };

    Handler addReimbursement = ctx ->{
        if(ctx.req.getSession(false)!=null) { //getSession(false) will only return a Session object if the client
            //sent a cookie along with the request that matches an open session.
            ReimbursementDTO reimbursement = ctx.bodyAsClass(ReimbursementDTO.class);
            if(reimbursementService.addReimbursement(reimbursement)){
                ctx.status(202);
            }else {
                ctx.status(400);
            }
        }else {
            ctx.status(401);
        }
    };

    Handler deleteReimbursement = ctx ->{
        if(ctx.req.getSession(false)!=null) { //getSession(false) will only return a Session object if the client
            //sent a cookie along with the request that matches an open session.
            ReimbursementDTO reimbursementDTO = new ReimbursementDTO();
            if(reimbursementService.deleteReimbursement(reimbursementDTO.getId())){
                ctx.status(202);
            }else {
                ctx.status(400);
            }
        }else {
            ctx.status(401);
        }
    };


    @Override
    public void addRoutes(Javalin app) {
        app.get("/reimbursements ", getAllReimbursements); //gets all reimbursements tickets.
        app.get("/reimbursements/user/{userId}", getReimbursements); //gets all reimbursements tickets for one user/author.
        app.get("/reimbursements/status/{statusString}", getStatus); //gets all reimbursements tickets by status.
        app.post("/reimbursements/add", addReimbursement); //Create a new reimbursement ticket.
        app.put("/reimbursements/update/{reimbId}", updateReimbursement); //update reimbursement ticket by reimbursement ID.
        app.post("/reimbursements/delete/{reimbId}", deleteReimbursement); //Delete a reimbursement ticket by id.
    }
}
