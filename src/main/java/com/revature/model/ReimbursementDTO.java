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

    public ReimbursementDTO() {
    }

    public ReimbursementDTO(int id, int amount, Timestamp timeSubmitted, Timestamp timeResolved, String description, byte[] receipt, int authorUserId, User authorUser, int resolverUserId, User resolverUser, int statusId, Status status, int typeId, Type type) {
        this.id = id;
        this.amount = amount;
        this.timeSubmitted = timeSubmitted;
        this.timeResolved = timeResolved;
        this.description = description;
        this.receipt = receipt;
        this.authorUserId = authorUserId;
        this.authorUser = authorUser;
        this.resolverUserId = resolverUserId;
        this.resolverUser = resolverUser;
        this.statusId = statusId;
        this.status = status;
        this.typeId = typeId;
        this.type = type;
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

    public User getAuthorUser() {
        return authorUser;
    }

    public void setAuthorUser(User authorUser) {
        this.authorUser = authorUser;
    }

    public int getResolverUserId() {
        return resolverUserId;
    }

    public void setResolverUserId(int resolverUserId) {
        this.resolverUserId = resolverUserId;
    }

    public User getResolverUser() {
        return resolverUser;
    }

    public void setResolverUser(User resolverUser) {
        this.resolverUser = resolverUser;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
