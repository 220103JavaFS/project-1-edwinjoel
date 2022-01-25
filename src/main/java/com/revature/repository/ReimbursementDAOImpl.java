package com.revature.repository;

import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementDTO;
import com.revature.model.Status;

import java.util.ArrayList;

public class ReimbursementDAOImpl implements ReimbursementDAO {

    @Override
    public ArrayList<ReimbursementDTO> getAllReimbursements() {
        return null;
    }

    @Override
    public ArrayList<ReimbursementDTO> getAllReimbursementsByAuthor(int authorUserId) {
        return null;
    }

    @Override
    public ArrayList<ReimbursementDTO> getAllReimbursementsByResolver(int resolverUserId) {
        return null;
    }

    @Override
    public ArrayList<ReimbursementDTO> getAllReimbursementsByStatus(Status status) {
        return null;
    }

    @Override
    public ReimbursementDTO getReimbursementById(int reimbId) {
        return null;
    }

    @Override
    public boolean updateReimbursement(ReimbursementDTO reimbursement, int reimbId) {
        return false;
    }

    @Override
    public boolean addReimbursement(ReimbursementDTO reimbursement) {
        return false;
    }

    @Override
    public boolean deleteReimbursement(int reimbId) {
        return false;
    }
}
