package com.revature.repository;

import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementDTO;
import com.revature.model.Status;

import java.util.ArrayList;

public interface ReimbursementDAO {
    //get all tickets reimbursements
    ArrayList<ReimbursementDTO> getAllReimbursements();

    //get all tickets reimbursements by author
    ArrayList<ReimbursementDTO> getAllReimbursementsByAuthor(int authorUserId);

    //get all tickets reimbursements by resolver (manager)
    ArrayList<ReimbursementDTO> getAllReimbursementsByResolver(int resolverUserId);

    //filter the reimbursement by status (manager)
    ArrayList<ReimbursementDTO> getAllReimbursementsByStatus(Status status);

    ReimbursementDTO getReimbursementById(int reimbId);

    boolean updateReimbursement(ReimbursementDTO reimbursement, int reimbId);

    boolean addReimbursement(ReimbursementDTO reimbursement);

    boolean deleteReimbursement(int reimbId);
}
