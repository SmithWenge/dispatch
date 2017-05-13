package com.dls.function.support.shiro.service.impl;

import com.dls.function.support.shiro.Permission;
import com.dls.function.support.shiro.Role;
import com.dls.function.support.shiro.User;
import com.dls.function.support.shiro.repository.ShiroRepository;
import com.dls.function.support.shiro.service.ShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiroServiceImpl implements ShiroService {
    @Autowired
    private ShiroRepository shiroRepository;

    @Override
    public User queryByName(String username) {
        return shiroRepository.selectByUsername(username);
    }

    @Override
    public List<Role> queryRoles(int userId) {
        return shiroRepository.selectRoles(userId);
    }

    @Override
    public List<Permission> queryRolePermissions(String roleId) {
        return shiroRepository.selectPermissions(roleId);
    }
}
