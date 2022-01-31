package com.revature.controller;

import com.revature.App;
import com.revature.model.ReimbursementDTO;
import com.revature.model.Status;
import com.revature.model.UserRole;
import com.revature.service.ReimbursementService;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Locale;

public class ReimbursementController extends Controller{

    private ReimbursementService reimbursementService = new ReimbursementService();
    private static Logger log = LoggerFactory.getLogger(App.class);

    @Override
    public void addRoutes(Javalin app) {


        //gets all reimbursements tickets.
        app.get("/reimbursements", ctx -> {
            ArrayList<ReimbursementDTO> reimbList = reimbursementService.getAllReimbursements();
            if(reimbList != null && !reimbList.isEmpty()){
                ctx.status(200);
                ctx.json(reimbList);
            }
            else{
                ctx.status(400);
            }
        }, UserRole.EMPLOYEE);

        //gets all reimbursements tickets for one user/author.
        app.get("/reimbursements/id/{reimbId}", ctx -> {
            int reimbId = Integer.parseInt(ctx.pathParam("reimbId"));
            ReimbursementDTO reimb = reimbursementService.getReimbursementById(reimbId);
            if(reimb != null){
                ctx.status(200);
                ctx.json(reimb);
            }
            else{
                ctx.status(400);
            }
        }, UserRole.MANAGER);


        //gets all reimbursements tickets for one user/author.
        app.get("/reimbursements/author/{userId}", ctx -> {
            int userId = Integer.parseInt(ctx.pathParam("userId"));
            ArrayList<ReimbursementDTO> reimbList = reimbursementService.getAllReimbursementsByAuthor(userId);
            if(reimbList != null && !reimbList.isEmpty()){
                ctx.status(200);
                ctx.json(reimbList);
            }
            else{
                ctx.status(400);
            }
        }, UserRole.MANAGER);

        //gets all reimbursements tickets for one user/resolver.
        app.get("/reimbursements/resolver/{userId}", ctx -> {
            int userId = Integer.parseInt(ctx.pathParam("userId"));
            ArrayList<ReimbursementDTO> reimbList = reimbursementService.getAllReimbursementsByResolver(userId);
            if(reimbList != null && !reimbList.isEmpty()){
                ctx.status(200);
                ctx.json(reimbList);
            }
            else{
                ctx.status(400);
            }
        }, UserRole.MANAGER);

        //gets all reimbursements tickets for one user/author.
        app.get("/reimbursements/myreimbursements", ctx -> {
            int userId = 0;
            try{
                userId = (Integer)ctx.req.getSession(false).getAttribute("userId");
            } catch (ClassCastException e){
                e.printStackTrace();
                ctx.status(400);
                return;
            }
            ArrayList<ReimbursementDTO> reimbList = reimbursementService.getAllReimbursementsByAuthor(userId);
            if(reimbList != null && !reimbList.isEmpty()){
                ctx.status(200);
                ctx.json(reimbList);
            }
            else{
                ctx.status(400);
            }
        }, UserRole.EMPLOYEE);


        //gets all reimbursements tickets by status.
        app.get("/reimbursements/status/{statusString}", ctx -> {
            String statusString = ctx.pathParam("statusString").toUpperCase();
            Status status = null;
            try {
                status = Status.valueOf(statusString);
            }
            catch (IllegalArgumentException e){
            }
            ArrayList<ReimbursementDTO> reimbList = reimbursementService.getAllReimbursementsByStatus(status);
            if(reimbList != null && !reimbList.isEmpty()){
                ctx.status(201);
                ctx.json(reimbList);
            }
            else{
                ctx.status(400);
            }



        }, UserRole.MANAGER);


        //Create a new reimbursement ticket.
        app.post("/reimbursements/add", ctx -> {
            ReimbursementDTO reimbursement = ctx.bodyAsClass(ReimbursementDTO.class);
            reimbursement.setTimeSubmitted(new Timestamp(System.currentTimeMillis()));
            int userId = 0;
            try{
                userId = (Integer)ctx.req.getSession(false).getAttribute("userId");
            } catch (ClassCastException e){
                e.printStackTrace();
                ctx.status(400);
                return;
            }
            reimbursement.setAuthorUserId(userId);

            if(reimbursementService.addReimbursement(reimbursement)){
                ctx.status(202);
            }else {
                ctx.status(400);
            }
        }, UserRole.EMPLOYEE);


        //update reimbursement ticket by reimbursement ID.
        app.put("/reimbursements/update/{reimbId}", ctx -> {
            int reimbId = Integer.parseInt(ctx.pathParam("reimbId"));
            ReimbursementDTO reimbursement = ctx.bodyAsClass(ReimbursementDTO.class);
            if(reimbursementService.updateReimbursement(reimbursement, reimbId)){
                ctx.status(202);
            }else {
                ctx.status(400);
            }

        }, UserRole.MANAGER);


        //Approve reimbursement ticket by reimbursement ID.
        app.put("/reimbursements/approve/{reimbId}", ctx -> {

            int reimbId = Integer.parseInt(ctx.pathParam("reimbId"));
            ReimbursementDTO reimbursement = new ReimbursementDTO();
            reimbursement.setTimeResolved(new Timestamp(System.currentTimeMillis()));
            int userId = 0;
            try{
                userId = (Integer)ctx.req.getSession(false).getAttribute("userId");
            } catch (ClassCastException e){
                e.printStackTrace();
                ctx.status(400);
                return;
            }
            reimbursement.setResolverUserId(userId);
            reimbursement.setStatusId(2);

            if(reimbursementService.updateReimbursement(reimbursement, reimbId)){
                ctx.status(202);
            }else {
                ctx.status(400);
            }

        }, UserRole.MANAGER);

        //Deny reimbursement ticket by reimbursement ID.
        app.put("/reimbursements/deny/{reimbId}", ctx -> {

            int reimbId = Integer.parseInt(ctx.pathParam("reimbId"));
            ReimbursementDTO reimbursement = new ReimbursementDTO();
            reimbursement.setTimeResolved(new Timestamp(System.currentTimeMillis()));
            int userId = 0;
            try{
                userId = (Integer)ctx.req.getSession(false).getAttribute("userId");
            } catch (ClassCastException e){
                e.printStackTrace();
                ctx.status(400);
                return;
            }
            reimbursement.setResolverUserId(userId);
            reimbursement.setStatusId(3);

            if(reimbursementService.updateReimbursement(reimbursement, reimbId)){
                ctx.status(202);
            }else {
                ctx.status(400);
            }

        }, UserRole.MANAGER);



        //Delete a reimbursement ticket by id.
        app.delete("/reimbursements/delete/{reimbId}", ctx -> {
            int reimbId = Integer.parseInt(ctx.pathParam("reimbId"));

            if(reimbursementService.deleteReimbursement(reimbId)){
                ctx.status(202);
            }else {
                ctx.status(400);
            }

        }, UserRole.MANAGER);
    }
}
