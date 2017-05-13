package com.dls.function.admin.login.service;

import com.google.common.base.Optional;
import com.dls.arc.utils.PasswordUtils;
import com.dls.function.admin.login.AdminUser;
import com.dls.function.admin.login.repository.AdminLoginRepositoryI;
import com.dls.function.support.log.LogContent;
import com.dls.function.support.log.repository.LogRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminLoginService implements AdminLoginServiceI {
    @Autowired
    private AdminLoginRepositoryI adminLoginRepository;
    @Autowired
    private LogRepositoryI logRepository;

    public AdminUser login(AdminUser adminUser) {
        adminUser.setAdminLoginPass(PasswordUtils.encrypt(adminUser.getAdminLoginPass()));
        AdminUser loginUser = adminLoginRepository.selectUnique(adminUser);
        Optional<AdminUser> optional = Optional.fromNullable(loginUser);

        if (optional.isPresent()) {
            LogContent logContent = new LogContent(adminUser.getAdminLoginName(), "登陆系统", 1, 5);
            logRepository.insertLog(logContent);
        }

        return loginUser;
    }

    @Override
    public AdminUser isExistAdminUser(AdminUser adminUser) {
        AdminUser mailUser = adminLoginRepository.selectUniqueEmail(adminUser);
        Optional<AdminUser> optional = Optional.fromNullable(mailUser);

        if (optional.isPresent()) {

        }

        return mailUser;
    }

    @Override
    public AdminUser resetPassword(AdminUser adminUser) {
        adminUser.setAdminLoginPass(PasswordUtils.encrypt(adminUser.getAdminLoginPass()));
        AdminUser canLogin = adminLoginRepository.selectUnique(adminUser);

        Optional<AdminUser> optional = Optional.fromNullable(canLogin);

        if (optional.isPresent()) {
            adminUser.setAdminLoginPassNew(PasswordUtils.encrypt(adminUser.getAdminLoginPassNew()));

            if (adminLoginRepository.resetPassword(adminUser)) {
                LogContent logContent = new LogContent(adminUser.getAdminLoginName(), "更改密码", 1, 4);
                logRepository.insertLog(logContent);
                adminUser.setAdminLoginPass(adminUser.getAdminLoginPassNew());

                return adminLoginRepository.selectUnique(adminUser);
            }
        }

        return null;
    }

    @Override
    public String configColor() {
        return adminLoginRepository.select4ConfigColor();
    }

    @Override
    public AdminUser queryAdminUser(String adminUserId) {
        return adminLoginRepository.selectAdminUser(adminUserId);
    }
}
