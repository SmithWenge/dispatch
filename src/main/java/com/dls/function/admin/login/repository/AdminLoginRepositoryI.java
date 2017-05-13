package com.dls.function.admin.login.repository;

import com.dls.function.admin.login.AdminUser;

public interface AdminLoginRepositoryI {
    AdminUser selectUnique(AdminUser adminUser);
    AdminUser selectUniqueEmail(AdminUser adminUser);
    boolean resetPassword(AdminUser adminUser);
    String select4ConfigColor();

    /**
     * 查询通过shiro管理的登录用户的管理员信息
     *
     * @param adminUserId 管理员Id
     * @return 返回管理员详细信息
     */
    AdminUser selectAdminUser(String adminUserId);
}
