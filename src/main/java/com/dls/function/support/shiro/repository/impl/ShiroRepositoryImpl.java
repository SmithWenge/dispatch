package com.dls.function.support.shiro.repository.impl;

import com.dls.function.support.shiro.Permission;
import com.dls.function.support.shiro.Role;
import com.dls.function.support.shiro.User;
import com.dls.function.support.shiro.repository.ShiroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ShiroRepositoryImpl implements ShiroRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     *  更具用户的登录名查询用户信息
     *
     * @param username 用户登录名
     * @return 如果用户存在返回用户对象, else null
     */
    @Override
    public User selectByUsername(String username) {
        String sql = "SELECT userId, username, password, adminUserId FROM shiro_user WHERE username = ? AND deleteFlag = 0";
        Object[] args = {
                username
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new SelectByUsernameRowMapper());
        } catch (Exception e) {
            LOGGER.warn("[SHIRO LOGIN] [W], the user name is {} and information is {}.", username, e);

            return null;
        }
    }

    private class SelectByUsernameRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();

            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setUserId(rs.getInt("userId"));
            user.setAdminUserId(rs.getString("adminUserId"));

            return user;
        }
    }

    /**
     * 查询这个用户的所有角色
     *
     * @param userId 登陆用户的Id
     * @return 返回这个用户的所拥有的角色
     */
    @Override
    public List<Role> selectRoles(int userId) {
        String sql = "SELECT R.roleId, R.roleName FROM shiro_userrole UR LEFT JOIN shiro_role R ON UR.roleId = R.roleId WHERE UR.userId = ?";
        Object[] args = {
                userId
        };

        try {
            return jdbcTemplate.query(sql, args, new SelectRolesRowMapper());
        } catch (Exception e) {
            LOGGER.warn("[SHIRO] [W] query user roles, the userId is {} and info is {},", userId, e);

            return new ArrayList<>();
        }
    }

    private class SelectRolesRowMapper implements RowMapper<Role> {

        @Override
        public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
            Role role = new Role();

            role.setRoleName(rs.getString("roleName"));
            role.setRoleId(rs.getString("roleId"));

            return role;
        }
    }

    /**
     * 查询这个角色的所有权限
     *
     * @param roleId 用户拥有的角色
     * @return 这个角色的所有权限
     */
    @Override
    public List<Permission> selectPermissions(String roleId) {
        String sql = "SELECT P.permissionName FROM shiro_rolepermission RP LEFT JOIN shiro_permission P ON RP.permissionId = P.permissionId WHERE RP.roleId = ?";
        Object[] args = {
                roleId
        };

        try {
            return jdbcTemplate.query(sql, args, new SelectPermissionsRowMapper());
        } catch (Exception e) {
            LOGGER.warn("[SHIRO] [W], query permissionName the roleId is {} and information is {}.", roleId, e);

            return new ArrayList<>();
        }
    }

    private class SelectPermissionsRowMapper implements RowMapper<Permission> {

        @Override
        public Permission mapRow(ResultSet rs, int rowNum) throws SQLException {
            Permission permission = new Permission();

            permission.setPermissionName(rs.getString("permissionName"));

            return permission;
        }
    }
}
