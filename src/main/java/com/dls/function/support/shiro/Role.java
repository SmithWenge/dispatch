package com.dls.function.support.shiro;

import java.util.List;

public class Role {
    private String roleId;
    private String roleName;
    private String hiddenRoleName;
    private List<User> users;
    private List<Permission> permissions;
    private String roleZHCNName;

    public String getHiddenRoleName() {
        return hiddenRoleName;
    }

    public void setHiddenRoleName(String hiddenRoleName) {
        this.hiddenRoleName = hiddenRoleName;
    }

    public void setRoleZHCNName(String roleZHCNName) {
        this.roleZHCNName = roleZHCNName;
    }

    public String getRoleZHCNName() {

        return roleZHCNName;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleId() {

        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<User> getUsers() {

        return users;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }
}
