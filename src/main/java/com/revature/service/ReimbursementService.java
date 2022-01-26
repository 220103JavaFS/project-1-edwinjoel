package com.revature.service;

import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementDTO;
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



    public boolean addReimbursement(ReimbursementDTO reimbursement){
        return reimbursementDAO.addReimbursement(reimbursement);
    }

    public ArrayList<ReimbursementDTO> getAllReimbursements(){
        return reimbursementDAO.getAllReimbursements();
    }

    public ReimbursementDTO getReimbursementById(int reimbId){
        if(reimbId <= 0){
            return null;
        }
        return reimbursementDAO.getReimbursementById(reimbId);
    }

    public ArrayList<ReimbursementDTO> getAllReimbursementsByAuthor(int authorUserId){
        if(authorUserId <= 0){
            return null;
        }
        return reimbursementDAO.getAllReimbursementsByAuthor(authorUserId);
    }

    public ArrayList<ReimbursementDTO> getAllReimbursementsByResolver(int resolverUserId){
        if(resolverUserId <= 0){
            return null;
        }
        return reimbursementDAO.getAllReimbursementsByResolver(resolverUserId);
    }

    public ArrayList<ReimbursementDTO> getAllReimbursementsByStatus(Status status){
        if(status == null){
            return null;
        }
        return reimbursementDAO.getAllReimbursementsByStatus(status);
    }

    public boolean updateReimbursement(ReimbursementDTO reimbursement, int reimbId){
        if(reimbursement == null || reimbId <= 0){
            //TODO: validate the reimbursement object fields
            return false;
        }
        return reimbursementDAO.updateReimbursement(reimbursement, reimbId);
    }

    public boolean deleteReimbursement(int reimbId){
        if(reimbId <= 0){
            return false;
        }
        return reimbursementDAO.deleteReimbursement(reimbId);
    }

}
