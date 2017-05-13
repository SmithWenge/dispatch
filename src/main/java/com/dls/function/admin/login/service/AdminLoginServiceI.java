package com.dls.function.admin.login.service;

import com.dls.function.admin.login.AdminUser;

public interface AdminLoginServiceI {
    AdminUser login(AdminUser adminUser);
    AdminUser isExistAdminUser(AdminUser adminUser);
    AdminUser resetPassword(AdminUser adminUser);
    String configColor();
    AdminUser queryAdminUser(String adminUserId);
}
