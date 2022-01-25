package com.revature.model;

public class Status {
    private int statusId;
    private statusStatus status;
    public enum statusStatus {
        APPROVED,
        PENDING,
        DENIED;
    }


    //TODO: Make into Enum class
    public Status() {
    }

    public Status(int statusId, statusStatus status) {
        this.statusId = statusId;
        this.status = status;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public statusStatus getStatus() {
        return status;
    }

    public void setStatus(statusStatus status) {
        this.status = status;
    }
}
