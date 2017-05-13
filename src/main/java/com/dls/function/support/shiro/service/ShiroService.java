package com.dls.function.support.shiro.service;

import com.dls.function.support.shiro.Permission;
import com.dls.function.support.shiro.Role;
import com.dls.function.support.shiro.User;

import java.util.List;

public interface ShiroService {
    User queryByName(String username);
    List<Role> queryRoles(int userId);
    List<Permission> queryRolePermissions(String roleId);
}
