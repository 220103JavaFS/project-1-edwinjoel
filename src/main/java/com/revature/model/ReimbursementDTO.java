package com.revature.model;

import java.sql.Timestamp;

public class ReimbursementDTO {
    private int id;
    private int amount;
    private Timestamp timeSubmitted;
    private Timestamp timeResolved;
    private String description;
    private byte[] receipt;
    private int authorUserId;
    private User authorUser;
    private int resolverUserId;
    private User resolverUser;
    private int statusId;
    private Status status;
    private int typeId;
    private Type type;
}
