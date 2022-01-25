package com.revature.model;

public class UserRole {
    private int userRoleId;

    public enum userRole {
        EMPLOYEE,
        MANAGER;
    }

    //TODO: Make into Enum class
    public UserRole() {
    }

    public UserRole(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }



}


