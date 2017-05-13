package com.dls.function.support.shiro.manage.respository;

import com.dls.function.support.shiro.Permission;
import com.dls.function.support.shiro.Role;
import com.dls.function.support.shiro.User;

import java.util.List;

public interface RoleManageRepositoryI {
    /**
     * 查询这个系统中所有角色
     *
     * @return 返回系统所有角色
     */
    List<Role> selectAllRoles();

    /**
     * 查询所有的权限
     *
     * @return 返回所有的权限
     */
    List<Permission> selectAllPermissions();

    /**
     * 添加新添加角色的权限
     *
     * @param permissions 新角色的所有权限
     * @param roleKey 新添加角色的主键
     * @return 如果都添加成功返回true, else false
     */
    boolean insertPermissions(String[] permissions, String roleKey);

    /**
     * 添加新的角色
     *
     * @param role 新添加的角色的信息
     * @return 添加成功返回true, else false
     */
    boolean insertRole(Role role);

    /**
     * 查询某个角色下的所有权限
     *
     * @param roleId 角色Id
     * @return 这个角色的所有权限
     */
    List<Permission> selectRolePermissions(String roleId);

    /**
     * 查询管理员的角色
     *
     * @param adminUserId 管理员Id
     * @return 这个管理员的所有角色
     */
    List<Role> select4UserRoles(String adminUserId);

    /**
     * 查询管理员的名字
     *
     * @param adminId 管理员Id
     * @return 管理员登录名的管理员对象
     */
    User selectUserInfo(String adminId);

    /**
     * 删除原来的用户角色
     *
     * @param userId 用户Id
     * @return 删除成功返回true, else false
     */
    boolean deleteOldRole(int userId);

    /**
     * 添加这个用户的新的角色
     *
     * @param userId 用户Id
     * @param roles 新的角色
     * @return 添加成功返回true, else false
     */
    boolean insertNewRoles(int userId, String[] roles);

    /**
     * 查询判断这个角色名的唯一
     *
     * @param roleName 角色名
     * @return 如果不重复返回true, else false
     */
    boolean select4UniqueRoleName(String roleName);

    /**
     * 查询这个角色的现在所包含的权限
     *
     * @param roleId 角色的Id
     * @return 这个角色所包含的权限
     */
    List<Permission> selectRoleCheckPermissions(String roleId);

    /**
     * 查询这个角色的现在所没有包含的权限
     *
     * @param roleId 角色的Id
     * @return 这个角色未选择的权限
     */
    List<Permission> selectRoleUncheckIdPermissions(String roleId);

    /**
     * 查询这个角色的详细信息用于修改
     *
     * @param roleId 这个角色Id
     * @return 返回这个角色的详细信息
     */
    Role selectRoleInfo(String roleId);

    /**
     * 删除这个角色原来的权限
     *
     * @param roleId 角色Id
     * @return 删除成功返回true, else false
     */
    boolean deleteOldRolePermissions(String roleId);

    /**
     * 添加这个角色新的权限
     *
     * @param roleId 角色Id
     * @param editPermissions 新的权限
     * @return 添加成功返回true, else false
     */
    boolean insertNewRolePermissions(String roleId, String[] editPermissions);
}
