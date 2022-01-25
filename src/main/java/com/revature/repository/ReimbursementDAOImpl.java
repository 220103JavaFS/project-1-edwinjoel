package com.revature.repository;

import com.revature.model.Reimbursement;
import com.revature.model.Status;

import java.util.ArrayList;

public class ReimbursementDAOImpl implements ReimbursementDAO{

    @Override
    public ArrayList<Reimbursement> getAllReimbursements() {
        return null;
    }

    @Override
    public ArrayList<Reimbursement> getAllReimbursementsByAuthor(int authorUserId) {
        return null;
    }

    @Override
    public ArrayList<Reimbursement> getAllReimbursementsByResolver(int resolverUserId) {
        return null;
    }

    @Override
    public ArrayList<Reimbursement> getAllReimbursementsByStatus(Status.State status) {
        return null;
    }

    @Override
    public Reimbursement getReimbursementById(int reimbId) {
        return null;
    }

    @Override
    public boolean updateReimbursement(Reimbursement reimbursement) {
        return false;
    }

    @Override
    public boolean addReimbursement(Reimbursement reimbursement) {
        return false;
    }

    @Override
    public boolean deleteReimbursement(int reimbId) {
        return false;
    }
}
