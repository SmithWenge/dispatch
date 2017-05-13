package com.dls.function.admin.login.repository;

import com.dls.arc.utils.ConstantFields;
import com.dls.function.admin.login.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AdminLoginRepository implements AdminLoginRepositoryI {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public AdminUser selectUnique(AdminUser adminUser) {
        String sql = "SELECT adminName, adminLoginName, adminRole, adminLoginPass FROM lgb_adminUser WHERE deleteFlag = 0 AND adminIsLock = 0 AND adminLoginName = ? AND adminLoginPass = ?";
        Object[] args = {
                adminUser.getAdminLoginName(),
                adminUser.getAdminLoginPass()
        };

        AdminUser returnUser = null;

        try {
            returnUser = jdbcTemplate.queryForObject(sql, args, new SelectUniqueRowMapper());
        } catch (Exception e) {
            return null;
        }

        return returnUser;
    }

    @Override
    public AdminUser selectUniqueEmail(AdminUser adminUser) {
        String sql = "SELECT adminLoginName, adminEmail, adminName FROM lgb_adminUser WHERE adminLoginName = ? AND adminEmail = ? AND deleteFlag = 0 AND adminIsLock = 0";
        Object[] args = {
                adminUser.getAdminLoginName(),
                adminUser.getAdminEmail()
        };

        AdminUser emailUser = null;

        try {
            emailUser = jdbcTemplate.queryForObject(sql, args, new SelectUniqueEmailRowMapper());
        } catch (Exception e) {
            return null;
        }

        return emailUser != null ? emailUser : null;
    }

    public boolean resetPassword(AdminUser adminUser) {
        String sql = "UPDATE lgb_adminUser SET adminLoginPass = ? WHERE adminLoginName = ? AND adminLoginPass = ? AND adminIsLock = 0 AND deleteFlag = 0";
        Object[] args = {
                adminUser.getAdminLoginPassNew(),
                adminUser.getAdminLoginName(),
                adminUser.getAdminLoginPass()
        };

        return jdbcTemplate.update(sql, args) == 1;
    }

    private class SelectUniqueRowMapper implements RowMapper<AdminUser> {

        @Override
        public AdminUser mapRow(ResultSet resultSet, int i) throws SQLException {
            AdminUser adminUser = new AdminUser();

            adminUser.setAdminLoginName(resultSet.getString("adminLoginName"));
            adminUser.setAdminName(resultSet.getString("adminName"));
            adminUser.setAdminRole(resultSet.getInt("adminRole"));
            adminUser.setAdminLoginPass(resultSet.getString("adminLoginPass"));

            return adminUser;
        }
    }

    private class SelectUniqueEmailRowMapper implements RowMapper<AdminUser> {

        @Override
        public AdminUser mapRow(ResultSet resultSet, int i) throws SQLException {
            AdminUser adminUser = new AdminUser();

            adminUser.setAdminLoginName(resultSet.getString("adminLoginName"));
            adminUser.setAdminName(resultSet.getString("adminName"));
            adminUser.setAdminEmail(resultSet.getString("adminEmail"));

            return adminUser;
        }
    }

    /**
     * 获取用户登录后的配置背景色
     *
     * @return 16进制背景色
     */
    @Override
    public String select4ConfigColor() {
        String sql = "SELECT background_color_setting FROM lgb_config";

        try {
            return jdbcTemplate.queryForObject(sql, String.class);
        } catch (Exception e) {
            return ConstantFields.DEFAULT_BACKGROUND_COLOR_SETTING;
        }
    }

    /**
     * 查询通过shiro管理的登录用户的管理员信息
     *
     * @param adminUserId 管理员Id
     * @return 返回管理员详细信息
     */
    @Override
    public AdminUser selectAdminUser(String adminUserId) {
        String sql = "SELECT adminName, adminLoginName FROM lgb_adminUser WHERE deleteFlag = 0 AND adminIsLock = 0 AND adminId = ?";
        Object[] args = {
                adminUserId
        };

        AdminUser returnUser = null;

        try {
            returnUser = jdbcTemplate.queryForObject(sql, args, new SelectAdminUserRowMapper());
        } catch (Exception e) {
            return null;
        }

        return returnUser;
    }

    private class SelectAdminUserRowMapper implements RowMapper<AdminUser> {

        @Override
        public AdminUser mapRow(ResultSet rs, int rowNum) throws SQLException {

            AdminUser user = new AdminUser();

            user.setAdminName(rs.getString("adminName"));
            user.setAdminLoginName(rs.getString("adminLoginName"));

            return user;
        }
    }
}
