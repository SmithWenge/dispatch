package com.dls.function.support.shiro;

public class Permission {
    private int permissionId;
    private String permissionName;
    private Role role;
    private String permissionZHCNName;

    public void setPermissionZHCNName(String permissionZHCNName) {
        this.permissionZHCNName = permissionZHCNName;
    }

    public String getPermissionZHCNName() {

        return permissionZHCNName;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public int getPermissionId() {

        return permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {

        return role;
    }
}
