package com.revature.service;

import com.revature.model.Reimbursement;
import com.revature.model.Status;
import com.revature.repository.ReimbursementDAO;
import com.revature.repository.ReimbursementDAOImpl;

import java.util.ArrayList;

public class ReimbursementService {
    private ReimbursementDAO reimbursementDAO = new ReimbursementDAOImpl();

    public ReimbursementService() {
    }

    public ReimbursementService(ReimbursementDAO reimbursementDAO) {
        this.reimbursementDAO = reimbursementDAO;
    }

    public ArrayList<Reimbursement> getAllReimbursements(){
        return reimbursementDAO.getAllReimbursements();
    }

    public ArrayList<Reimbursement> getAllReimbursementsByAuthor(int authorUserId){
        return reimbursementDAO.getAllReimbursementsByAuthor(authorUserId);
    }

    public ArrayList<Reimbursement> getAllReimbursementsByResolver(int resolverUserId){
        return reimbursementDAO.getAllReimbursementsByResolver(resolverUserId);
    }

    public ArrayList<Reimbursement> getAllReimbursementsByStatus(Status status){
        return reimbursementDAO.getAllReimbursementsByStatus(status);
    }

    public Reimbursement getReimbursementById(int reimbId){
        return reimbursementDAO.getReimbursementById(reimbId);
    }

    public boolean updateReimbursement(Reimbursement reimbursement){
        return true;
    }

    public boolean addReimbursement(Reimbursement reimbursement){
        return true;
    }

    public boolean deleteReimbursement(int reimbId){
        return true;
    }

}
