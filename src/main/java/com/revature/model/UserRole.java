package com.revature.model;

public class UserRole {
    private int userRoleId;
    private UserRole.Role role;

    public enum Role {
        EMPLOYEE,
        MANAGER;
    }

    //TODO: Make into Enum class
    public UserRole() {
    }

    public UserRole(int userRoleId, Role role) {
        this.userRoleId = userRoleId;
        this.role = role;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}


