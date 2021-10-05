package com.ProjectHub.enums;

/**
 * Created by Avinash Vijayvargiya on 30-09-2021.
 */
public enum UserRole {

    ROLE_USER("ROLE_USER"), ROLE_ADMIN("ROLE_ADMIN");

    private String roleAssigned;
    private UserRole(String roleAssigned) {
        this.roleAssigned = roleAssigned;
    }

    @Override
    public String toString(){
        return roleAssigned;
    }
}
