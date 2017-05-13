package com.dls.function.support.shiro.manage.respository.impl;

import com.dls.function.support.shiro.Permission;
import com.dls.function.support.shiro.Role;
import com.dls.function.support.shiro.User;
import com.dls.function.support.shiro.manage.respository.RoleManageRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleManageRepositoryImpl implements RoleManageRepositoryI {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询这个系统中所有角色
     *
     * @return 返回系统所有角色
     */
    @Override
    public List<Role> selectAllRoles() {
        String sql = "SELECT roleId, roleName, roleZHCNName FROM shiro_role WHERE deleteFlag = 0 AND roleId != '0'";
        Object[] args = {};

        try {
            return jdbcTemplate.query(sql, args, new SelectAllRolesRowMapper());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private class SelectAllRolesRowMapper implements RowMapper<Role> {

        @Override
        public Role mapRow(ResultSet rs, int rowNum) throws SQLException {

            Role role = new Role();

            role.setRoleId(rs.getString("roleId"));
            role.setRoleName(rs.getString("roleName"));
            role.setRoleZHCNName(rs.getString("roleZHCNName"));

            return role;
        }
    }

    /**
     * 查询所有的权限
     *
     * @return 返回所有的权限
     */
    @Override
    public List<Permission> selectAllPermissions() {
        String sql = "SELECT permissionId, permissionName, permissionZHCNName FROM shiro_permission";
        Object[] args = {};

        try {
            return jdbcTemplate.query(sql, args, new SelectAllPermissionsRowMapper());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private class SelectAllPermissionsRowMapper implements RowMapper<Permission> {

        @Override
        public Permission mapRow(ResultSet rs, int rowNum) throws SQLException {

            Permission permission = new Permission();

            permission.setPermissionId(rs.getInt("permissionId"));
            permission.setPermissionName(rs.getString("permissionName"));
            permission.setPermissionZHCNName(rs.getString("permissionZHCNName"));

            return permission;
        }
    }

    /**
     * 添加新添加角色的权限
     *
     * @param permissions 新角色的所有权限
     * @param roleKey 新添加角色的主键
     * @return 如果都添加成功返回true, else false
     */
    @Override
    public boolean insertPermissions(final String[] permissions, final String roleKey) {
        int[] result = jdbcTemplate.batchUpdate("INSERT INTO shiro_rolepermission (roleId, permissionId) VALUES (?, ?)",
                new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setString(1, roleKey);
                preparedStatement.setString(2, permissions[i]);
            }

            @Override
            public int getBatchSize() {
                return permissions.length;
            }
        });

        return result.length == permissions.length;
    }

    /**
     * 添加新的角色
     *
     * @param role 新添加的角色的信息
     * @return 添加成功返回true, else false
     */
    @Override
    public boolean insertRole(Role role) {
        String sql = "INSERT INTO shiro_role (roleId, roleName, roleZHCNName) VALUES (?, ?, ?)";
        Object[] args = {
                role.getRoleId(),
                role.getRoleName(),
                role.getRoleZHCNName()
        };

        try {
            return jdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 查询某个角色下的所有权限
     *
     * @param roleId 角色Id
     * @return 这个角色的所有权限
     */
    @Override
    public List<Permission> selectRolePermissions(String roleId) {
        String sql = "SELECT P.permissionZHCNName FROM shiro_rolepermission RP LEFT JOIN shiro_permission P ON RP.permissionId = P.permissionId WHERE RP.roleId = ? AND roleId != '0'";
        Object[] args = {
                roleId
        };

        try {
            return jdbcTemplate.query(sql, args, new SelectRolePermissionsRowMapper());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private class SelectRolePermissionsRowMapper implements RowMapper<Permission> {

        @Override
        public Permission mapRow(ResultSet rs, int rowNum) throws SQLException {

            Permission permission = new Permission();

            permission.setPermissionZHCNName(rs.getString("permissionZHCNName"));

            return permission;
        }
    }

    /**
     * 查询管理员的角色
     *
     * @param adminUserId 管理员Id
     * @return 这个管理员的所有角色
     */
    @Override
    public List<Role> select4UserRoles(String adminUserId) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT roleId, roleZHCNName FROM shiro_role WHERE roleId IN (SELECT roleId FROM shiro_userrole");
        builder.append(" WHERE userId = (SELECT userId FROM shiro_user WHERE adminUserId = ?))");
        Object[] args = {
                adminUserId
        };

        try {
            return jdbcTemplate.query(builder.toString(), args, new Select4UserRolesRowMapper());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private class Select4UserRolesRowMapper implements RowMapper<Role> {

        @Override
        public Role mapRow(ResultSet rs, int rowNum) throws SQLException {

            Role role = new Role();

            role.setRoleId(rs.getString("roleId"));
            role.setRoleZHCNName(rs.getString("roleZHCNName"));

            return role;
        }
    }

    /**
     * 查询管理员的名字
     *
     * @param adminId 管理员Id
     * @return 管理员登录名的管理员对象
     */
    @Override
    public User selectUserInfo(String adminId) {
        String sql = "SELECT userId, username, adminUserId FROM shiro_user WHERE adminUserId = ? AND deleteFlag = 0";
        Object[] args = {
                adminId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new SelectUserInfoRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    private class SelectUserInfoRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {

            User user = new User();

            user.setUsername(rs.getString("username"));
            user.setUserId(rs.getInt("userId"));
            user.setAdminUserId(rs.getString("adminUserId"));

            return user;
        }
    }

    /**
     * 删除原来的用户角色
     *
     * @param userId 用户Id
     * @return 删除成功返回true, else false
     */
    @Override
    public boolean deleteOldRole(int userId) {
        String sql = "DELETE FROM shiro_userrole WHERE userId = ?";
        Object[] args = {
                userId
        };

        try {
            return jdbcTemplate.update(sql, args) >= 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 添加这个用户的新的角色
     *
     * @param userId 用户Id
     * @param roles 新的角色
     * @return 添加成功返回true, else false
     */
    @Override
    public boolean insertNewRoles(final int userId, final String[] roles) {
        int[] result = jdbcTemplate.batchUpdate("INSERT INTO shiro_userrole (userId, roleId) VALUES (?, ?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setInt(1, userId);
                preparedStatement.setString(2, roles[i]);
            }

            @Override
            public int getBatchSize() {
                return roles.length;
            }
        });

        return result.length == roles.length;
    }

    /**
     * 查询判断这个角色名的唯一
     *
     * @param roleName 角色名
     * @return 如果不重复返回true, else false
     */
    @Override
    public boolean select4UniqueRoleName(String roleName) {
        String sql = "SELECT COUNT(1) AS NUM FROM shiro_role WHERE roleName = ?";
        Object[] args = {
                roleName
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, Integer.class) == 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 查询这个角色的现在所包含的权限
     *
     * @param roleId 角色的Id
     * @return 这个角色所包含的权限
     */
    @Override
    public List<Permission> selectRoleCheckPermissions(String roleId) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT P.permissionId, P.permissionName, P.permissionZHCNName FROM shiro_rolepermission RP LEFT JOIN");
        builder.append(" shiro_permission P ON RP.permissionId = P.permissionId WHERE roleId = ?");
        Object[] args = {
                roleId
        };

        try {
            return jdbcTemplate.query(builder.toString(), args, new SelectRoleCheckPermissionsRowMapper());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private class SelectRoleCheckPermissionsRowMapper implements RowMapper<Permission> {

        @Override
        public Permission mapRow(ResultSet rs, int rowNum) throws SQLException {

            Permission permission = new Permission();

            permission.setPermissionId(rs.getInt("permissionId"));
            permission.setPermissionName(rs.getString("permissionName"));
            permission.setPermissionZHCNName(rs.getString("permissionZHCNName"));

            return permission;
        }
    }

    /**
     * 查询这个角色的现在所没有包含的权限
     *
     * @param roleId 角色的Id
     * @return 这个角色未选择的权限
     */
    @Override
    public List<Permission> selectRoleUncheckIdPermissions(String roleId) {
        StringBuilder builder = new StringBuilder();
        builder.append("SElECT permissionId, permissionName, permissionZHCNName FROM shiro_permission WHERE permissionId NOT IN (SELECT");
        builder.append(" P.permissionId FROM shiro_rolepermission RP LEFT JOIN shiro_permission P ON RP.permissionId =");
        builder.append(" P.permissionId WHERE roleId = ?)");
        Object[] args = {
                roleId
        };

        try {
            return jdbcTemplate.query(builder.toString(), args, new SelectRoleCheckPermissionsRowMapper());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * 查询这个角色的详细信息用于修改
     *
     * @param roleId 这个角色Id
     * @return 返回这个角色的详细信息
     */
    @Override
    public Role selectRoleInfo(String roleId) {
        String sql = "SELECT roleId, roleName, roleZHCNName FROM shiro_role WHERE roleId = ? AND deleteFlag = 0";
        Object[] args = {
                roleId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new SelectRoleInfoRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    private class SelectRoleInfoRowMapper implements RowMapper<Role> {

        @Override
        public Role mapRow(ResultSet rs, int rowNum) throws SQLException {

            Role role = new Role();

            role.setRoleId(rs.getString("roleId"));
            role.setRoleName(rs.getString("roleName"));
            role.setRoleZHCNName(rs.getString("roleZHCNName"));

            return role;
        }
    }

    /**
     * 删除这个角色原来的权限
     *
     * @param roleId 角色Id
     * @return 删除成功返回true, else false
     */
    @Override
    public boolean deleteOldRolePermissions(String roleId) {
        String sql = "DELETE FROM shiro_rolepermission WHERE roleId = ?";
        Object[] args = {
                roleId
        };

        try {
            return jdbcTemplate.update(sql, args) >= 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 添加这个角色新的权限
     *
     * @param roleId 角色Id
     * @param editPermissions 新的权限
     * @return 添加成功返回true, else false
     */
    @Override
    public boolean insertNewRolePermissions(final String roleId, final String[] editPermissions) {
        int[] result = jdbcTemplate.batchUpdate("INSERT INTO shiro_rolepermission (roleId, permissionId) VALUES (?, ?)",
                new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setString(1, roleId);
                preparedStatement.setString(2, editPermissions[i]);
            }

            @Override
            public int getBatchSize() {
                return editPermissions.length;
            }
        });

        return result.length == editPermissions.length;
    }
}
