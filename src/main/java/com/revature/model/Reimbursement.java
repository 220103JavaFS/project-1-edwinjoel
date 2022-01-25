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

    public Reimbursement(){

    }

    public Reimbursement(int id, int amount, Timestamp timeSubmitted, Timestamp timeResolved, String description, byte[] receipt, int authorUserId, int resolverUserId, int statusId, int typeId) {
        this.id = id;
        this.amount = amount;
        this.timeSubmitted = timeSubmitted;
        this.timeResolved = timeResolved;
        this.description = description;
        this.receipt = receipt;
        this.authorUserId = authorUserId;
        this.resolverUserId = resolverUserId;
        this.statusId = statusId;
        this.typeId = typeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Timestamp getTimeSubmitted() {
        return timeSubmitted;
    }

    public void setTimeSubmitted(Timestamp timeSubmitted) {
        this.timeSubmitted = timeSubmitted;
    }

    public Timestamp getTimeResolved() {
        return timeResolved;
    }

    public void setTimeResolved(Timestamp timeResolved) {
        this.timeResolved = timeResolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getReceipt() {
        return receipt;
    }

    public void setReceipt(byte[] receipt) {
        this.receipt = receipt;
    }

    public int getAuthorUserId() {
        return authorUserId;
    }

    public void setAuthorUserId(int authorUserId) {
        this.authorUserId = authorUserId;
    }

    public int getResolverUserId() {
        return resolverUserId;
    }

    public void setResolverUserId(int resolverUserId) {
        this.resolverUserId = resolverUserId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }


}
