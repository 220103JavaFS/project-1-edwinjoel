package com.revature.model;

public class Status {
    private int statusId;
    private State status;
    public enum State {
        APPROVED,
        PENDING,
        DENIED;
    }


    //TODO: Make into Enum class
    public Status() {
    }

    public Status(int statusId, State status) {
        this.statusId = statusId;
        this.status = status;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public State getStatus() {
        return status;
    }

    public void setStatus(State status) {
        this.status = status;
    }
}
