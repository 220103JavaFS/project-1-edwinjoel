package com.revature.repository;

import com.revature.model.Reimbursement;
import com.revature.model.Status;

import java.util.ArrayList;

public interface ReimbursementDAO {
    //get all tickets reimbursements
    ArrayList<Reimbursement> getAllReimbursements();

    //get all tickets reimbursements by author
    ArrayList<Reimbursement> getAllReimbursementsByAuthor(int authorUserId);

    //get all tickets reimbursements by resolver (manager)
    ArrayList<Reimbursement> getAllReimbursementsByResolver(int resolverUserId);

    //filter the reimbursement by status (manager)
    ArrayList<Reimbursement> getAllReimbursementsByStatus(Status.State status);

    Reimbursement getReimbursementById(int reimbId);

    boolean updateReimbursement(Reimbursement reimbursement);

    boolean addReimbursement(Reimbursement reimbursement);

    boolean deleteReimbursement(int reimbId);
}
