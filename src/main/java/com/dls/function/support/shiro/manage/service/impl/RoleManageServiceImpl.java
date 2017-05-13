package com.dls.function.support.shiro.manage.service.impl;

import com.dls.arc.utils.PrimaryKeyUtil;
import com.dls.function.support.log.LogContent;
import com.dls.function.support.log.repository.LogRepositoryI;
import com.dls.function.support.shiro.Permission;
import com.dls.function.support.shiro.Role;
import com.dls.function.support.shiro.User;
import com.dls.function.support.shiro.manage.respository.RoleManageRepositoryI;
import com.dls.function.support.shiro.manage.service.RoleManageServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleManageServiceImpl implements RoleManageServiceI {
    @Autowired
    private RoleManageRepositoryI roleManageRepository;
    @Autowired
    private LogRepositoryI logRepository;

    @Override
    public List<Role> queryAllRoles() {
        List<Role> roles = roleManageRepository.selectAllRoles();

        for (Role role : roles) {
            role.setPermissions(roleManageRepository.selectRolePermissions(role.getRoleId()));
        }

        return roles;
    }

    @Override
    public List<Permission> queryPermissions() {
        return roleManageRepository.selectAllPermissions();
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public boolean addNewRole(String[] permissions, Role role, String logUser) {
        String roleKey = PrimaryKeyUtil.uuidPrimaryKey();
        role.setRoleId(roleKey);

        boolean insertPermissions = roleManageRepository.insertPermissions(permissions, roleKey);
        boolean insertRole = roleManageRepository.insertRole(role);

        if (insertPermissions && insertRole) {
            LogContent logContent = new LogContent(logUser, "添加角色" + role.getRoleName(), 1, 3);
            logRepository.insertLog(logContent);
        }

        return insertPermissions && insertRole;
    }

    @Override
    public List<Role> queryUserRoles(String adminUserId) {
        return roleManageRepository.select4UserRoles(adminUserId);
    }

    @Override
    public List<Role> queryRoles() {
        return roleManageRepository.selectAllRoles();
    }

    @Override
    public User queryUserInfo(String adminId) {
        return roleManageRepository.selectUserInfo(adminId);
    }

    @Transactional
    @Override
    public boolean updateUserRole(String[] roles, User user, String logUser) {
        boolean deleteOldRoles = roleManageRepository.deleteOldRole(user.getUserId());
        boolean insertNewRoles = false;

        if (deleteOldRoles) {
            insertNewRoles = roleManageRepository.insertNewRoles(user.getUserId(), roles);

            if (insertNewRoles) {
                LogContent logContent = new LogContent(logUser, "更新用户角色" + user.getUserId(), 1, 4);
                logRepository.insertLog(logContent);
            }

        }

        return insertNewRoles && insertNewRoles;
    }

    @Override
    public boolean query4RoleNameUnique(String roleName) {
        return roleManageRepository.select4UniqueRoleName(roleName);
    }

    @Override
    public List<Permission> queryRoleCheckPermissions(String roleId) {
        return roleManageRepository.selectRoleCheckPermissions(roleId);
    }

    @Override
    public List<Permission> queryRoleUnckeckedPermissions(String roleId) {
        return roleManageRepository.selectRoleUncheckIdPermissions(roleId);
    }

    @Override
    public Role queryRoleInfo(String roleId) {
        return roleManageRepository.selectRoleInfo(roleId);
    }

    @Transactional
    @Override
    public boolean updateRolePermissions(String[] editPermissions, Role role, String logUser) {
        boolean deleteOldPermissions = roleManageRepository.deleteOldRolePermissions(role.getRoleId());
        boolean insertNewPermissions = false;

        if (deleteOldPermissions) {
            insertNewPermissions = roleManageRepository.insertNewRolePermissions(role.getRoleId(), editPermissions);

            if (insertNewPermissions) {
                LogContent logContent = new LogContent(logUser, "更新角色权限" + role.getRoleId(), 1, 4);
                logRepository.insertLog(logContent);
            }

        }

        return deleteOldPermissions && insertNewPermissions;
    }
}
