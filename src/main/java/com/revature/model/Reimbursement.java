package com.revature.model;

import java.sql.Timestamp;

public class Reimbursement {
    private int id;
    private int amount;
    private Timestamp timeSubmitted;
    private Timestamp timeResolved;
    private String description;
    private byte[] receipt;
    private int authorUserId;
    private int resolverUserId;
    private int statusId;
    private int typeId;
}
