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

    public ArrayList<ReimbursementDTO> getAllReimbursements(){
        return reimbursementDAO.getAllReimbursements();
    }

    public ArrayList<ReimbursementDTO> getAllReimbursementsByAuthor(int authorUserId){
        return reimbursementDAO.getAllReimbursementsByAuthor(authorUserId);
    }

    public ArrayList<ReimbursementDTO> getAllReimbursementsByResolver(int resolverUserId){
        return reimbursementDAO.getAllReimbursementsByResolver(resolverUserId);
    }

    public ArrayList<ReimbursementDTO> getAllReimbursementsByStatus(Status status){
        return reimbursementDAO.getAllReimbursementsByStatus(status);
    }

    public ReimbursementDTO getReimbursementById(int reimbId){
        return reimbursementDAO.getReimbursementById(reimbId);
    }

    public boolean updateReimbursement(ReimbursementDTO reimbursement, int reimbId){
        return reimbursementDAO.updateReimbursement(reimbursement, reimbId);
    }

    public boolean addReimbursement(ReimbursementDTO reimbursement){
        return reimbursementDAO.addReimbursement(reimbursement);
    }

    public boolean deleteReimbursement(int reimbId){
        return reimbursementDAO.deleteReimbursement(reimbId);
    }

}
