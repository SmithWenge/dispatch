package com.dls.function.support.shiro.manage.controller;

import com.dls.arc.utils.ConstantFields;
import com.dls.function.admin.login.AdminUser;
import com.dls.function.support.shiro.Permission;
import com.dls.function.support.shiro.Role;
import com.dls.function.support.shiro.User;
import com.dls.function.support.shiro.manage.service.RoleManageServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/role")
public class RoleManageController {
    private final static Logger LOG = LoggerFactory.getLogger(RoleManageController.class);

    @Autowired
    private RoleManageServiceI roleManageService;

    /**
     * 查询系统里面所有角色
     *
     * @return 返回到角色Listy页面,包含数据
     */
    @RequestMapping("/list")
    public ModelAndView listAllRoles() {
        List<Role> roles = roleManageService.queryAllRoles();

        ModelAndView mav = new ModelAndView("admin/role/index");

        mav.addObject("roles", roles);

        return mav;
    }

    /**
     * 路由到角色添加页面
     *
     * @return 包含权限信息的角色添加页面
     */
    @RequestMapping("/routeAdd")
    public ModelAndView routeAddRole() {
        List<Permission> permissions = roleManageService.queryPermissions();
        List<Permission> departmentList = new ArrayList<>();
        List<Permission> courseList = new ArrayList<>();
        List<Permission> majorList = new ArrayList<>();
        List<Permission> teacherList = new ArrayList<>();
        List<Permission> studentList = new ArrayList<>();
        List<Permission> disciplinaryList = new ArrayList<>();
        List<Permission> scoreList = new ArrayList<>();
        List<Permission> monitorList = new ArrayList<>();
        List<Permission> changeCourseList = new ArrayList<>();
        List<Permission> memberCountList = new ArrayList<>();
        List<Permission> downSignList = new ArrayList<>();
        List<Permission> backcolorList = new ArrayList<>();
        List<Permission> messageSetList = new ArrayList<>();
        List<Permission> logList = new ArrayList<>();
        List<Permission> userList = new ArrayList<>();
        List<Permission> registerList = new ArrayList<>();
        List<Permission> chargeList = new ArrayList<>();
        List<Permission> financeCountList = new ArrayList<>();
        List<Permission> financeAnalysisList = new ArrayList<>();
        List<Permission> billList = new ArrayList<>();
        List<Permission> unpaymentList = new ArrayList<>();
        List<Permission> paymentList = new ArrayList<>();
        List<Permission> refundList = new ArrayList<>();
        List<Permission> financeChangeCourseList = new ArrayList<>();
        List<Permission> roomList = new ArrayList<>();

        for (Permission premission : permissions) {
            String premissionName = premission.getPermissionName();
            if (premissionName.substring(0, 3).equals("log")) {
                logList.add(premission);
            } else if (premissionName.substring(0, 4).equals("bill")) {
                billList.add(premission);
            } else if (premissionName.substring(0, 4).equals("user")) {
                userList.add(premission);
            } else if (premissionName.substring(0, 4).equals("room")) {
                roomList.add(premission);
            } else if (premissionName.substring(0, 5).equals("major")) {
                majorList.add(premission);
            } else if (premissionName.substring(0, 5).equals("score")) {
                scoreList.add(premission);
            }  else if (premissionName.substring(0, 6).equals("course")) {
                courseList.add(premission);
            } else if (premissionName.substring(0, 6).equals("charge")) {
                chargeList.add(premission);
            } else if (premissionName.substring(0, 6).equals("refund")) {
                refundList.add(premission);
            } else if (premissionName.substring(0, 7).equals("monitor")) {
                monitorList.add(premission);
            } else if (premissionName.substring(0, 7).equals("teacher")) {
                teacherList.add(premission);
            } else if (premissionName.substring(0, 7).equals("student")) {
                studentList.add(premission);
            } else if (premissionName.substring(0, 7).equals("payment")) {
                paymentList.add(premission);
            } else if (premissionName.substring(0, 8).equals("downSign")) {
                downSignList.add(premission);
            } else if (premissionName.substring(0, 8).equals("register")) {
                registerList.add(premission);
            } else if (premissionName.substring(0, 9).equals("unpayment")) {
                unpaymentList.add(premission);
            } else if (premissionName.substring(0, 10).equals("department")) {
                departmentList.add(premission);
            } else if (premissionName.substring(0, 10).equals("messageSet")) {
                messageSetList.add(premission);
            } else if (premissionName.substring(0, 11).equals("memberCount")) {
                memberCountList.add(premission);
            } else if (premissionName.substring(0, 12).equals("disciplinary")) {
                disciplinaryList.add(premission);
            } else if (premissionName.substring(0, 12).equals("changeCourse")) {
                changeCourseList.add(premission);
            } else if (premissionName.substring(0, 12).equals("backcolorSet")) {
                backcolorList.add(premission);
            } else if (premissionName.substring(0, 12).equals("financeCount")) {
                financeCountList.add(premission);
            } else if (premissionName.substring(0, 15).equals("financeAnalysis")) {
                financeAnalysisList.add(premission);
            } else if (premissionName.substring(0, 19).equals("financeChangeCourse")) {
                financeChangeCourseList.add(premission);
            }
        }

        ModelAndView mav = new ModelAndView("admin/role/add");

        mav.addObject("department", departmentList);
        mav.addObject("course", courseList);
        mav.addObject("major", majorList);
        mav.addObject("teacher", teacherList);
        mav.addObject("student", studentList);
        mav.addObject("disciplinary", disciplinaryList);
        mav.addObject("score", scoreList);
        mav.addObject("monitor", monitorList);
        mav.addObject("changeCourse", changeCourseList);
        mav.addObject("memberCount", memberCountList);
        mav.addObject("downSign", downSignList);
        mav.addObject("backcolorSet", backcolorList);
        mav.addObject("messageSet", messageSetList);
        mav.addObject("log", logList);
        mav.addObject("user", userList);
        mav.addObject("register", registerList);
        mav.addObject("charge", chargeList);
        mav.addObject("financeCount", financeCountList);
        mav.addObject("financeAnalysis", financeAnalysisList);
        mav.addObject("bill", billList);
        mav.addObject("unpayment", unpaymentList);
        mav.addObject("payment", paymentList);
        mav.addObject("refund", refundList);
        mav.addObject("financeChangeCourse", financeChangeCourseList);
        mav.addObject("room", roomList);

        return mav;
    }

    /**
     * 添加新的角色
     *
     * @param addPermissions 这个角色的所有权限
     * @return 重定向到list请求
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addNewRole(@RequestParam String[] addPermissions, Role role, HttpSession session,
                             RedirectAttributes redirectAttributes) {
        if (addPermissions.length <= 0) {
            return "redirect:/admin/role/routeAdd.action";
        }

        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = user.getAdminLoginName();

        if (roleManageService.addNewRole(addPermissions, role, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} add new role {} and permissions are {}.", logUser, role.getRoleName(), addPermissions);

            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.ADD_SUCCESS_MESSAGE);
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.ADD_FAILURE_MESSAGE);
        }

        return "redirect:/admin/role/list.action";
    }

    /**
     * 编辑用户角色
     *
     * @param adminId 管理员用户Id
     * @return 管理员用户角色更改页面
     */
    @RequestMapping("/user/edit/route/{adminId}")
    public ModelAndView editUserRole(@PathVariable String adminId) {
        List<Role> userRoles = roleManageService.queryUserRoles(adminId);

        List<Role> allRoles = roleManageService.queryRoles();
        User user = roleManageService.queryUserInfo(adminId);

        ModelAndView mav = new ModelAndView("admin/role/userRoleEdit");

        mav.addObject("userRoles", userRoles);
        mav.addObject("allRoles", allRoles);
        mav.addObject("user", user);

        return mav;
    }

    /**
     * 保存新的用户的角色
     *
     * @param roles 新的角色
     * @param user 用户
     * @return 路由到用户角色编辑页面
     */
    @RequestMapping(value = "/user/edit", method = RequestMethod.POST)
    public String saveEditUserRole(@RequestParam String[] roles, User user, HttpSession session,
                                   RedirectAttributes redirectAttributes) {
        if (roles.length <= 0) {
            return "redirect:/admin/role/user/edit/route/" + user.getAdminUserId() + ".action";
        }

        AdminUser adminUser = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = adminUser.getAdminLoginName();

        if (roleManageService.updateUserRole(roles, user, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} update user role with userId is {} and roles are {}.", logUser, user.getUserId(), roles);

            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.ADD_SUCCESS_MESSAGE);
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.ADD_FAILURE_MESSAGE);
        }

        return "redirect:/admin/role/user/edit/route/" + user.getAdminUserId() + ".action";
    }

    /**
     * 确定角色名的唯一
     *
     * @param role 包含角色名的角色对象
     * @return 如果不重复返回true, else false
     */
    @ResponseBody
    @RequestMapping(value = "unique/roleName", method = RequestMethod.POST)
    public boolean uniqueRoleNameModify(Role role) {
        String roleName = role.getRoleName();
        String hiddenRoleName = role.getHiddenRoleName();

        return roleName.equalsIgnoreCase(hiddenRoleName) || roleManageService.query4RoleNameUnique(roleName);
    }

    /**
     * 编辑角色
     *
     * @param roleId 角色Id
     * @return 返回到角色编辑页面
     */
    @RequestMapping("/edit/route/{roleId}")
    public ModelAndView route2RoleEdit(@PathVariable String roleId) {
        ModelAndView mav = new ModelAndView("admin/role/edit");

        Role role = roleManageService.queryRoleInfo(roleId);
        List<Permission> checkedPermissions = roleManageService.queryRoleCheckPermissions(roleId);
        List<Permission> uncheckedPermissions = roleManageService.queryRoleUnckeckedPermissions(roleId);

        List<Permission> hadDepartmentList = new ArrayList<>();
        List<Permission> hadCourseList = new ArrayList<>();
        List<Permission> hadMajorList = new ArrayList<>();
        List<Permission> hadTeacherList = new ArrayList<>();
        List<Permission> hadStudentList = new ArrayList<>();
        List<Permission> hadDisciplinaryList = new ArrayList<>();
        List<Permission> hadScoreList = new ArrayList<>();
        List<Permission> hadMonitorList = new ArrayList<>();
        List<Permission> hadChangeCourseList = new ArrayList<>();
        List<Permission> hadMemberCountList = new ArrayList<>();
        List<Permission> hadDownSignList = new ArrayList<>();
        List<Permission> hadDackcolorList = new ArrayList<>();
        List<Permission> hadMessageSetList = new ArrayList<>();
        List<Permission> hadLogList = new ArrayList<>();
        List<Permission> hadUserList = new ArrayList<>();
        List<Permission> hadRegisterList = new ArrayList<>();
        List<Permission> hadChargeList = new ArrayList<>();
        List<Permission> hadFinanceCountList = new ArrayList<>();
        List<Permission> hadFinanceAnalysisList = new ArrayList<>();
        List<Permission> hadBillList = new ArrayList<>();
        List<Permission> hadUnpaymentList = new ArrayList<>();
        List<Permission> hadPaymentList = new ArrayList<>();
        List<Permission> hadRfundList = new ArrayList<>();
        List<Permission> hadFinanceChangeCourseList = new ArrayList<>();
        List<Permission> hadRoomList = new ArrayList<>();

        for (Permission premission : checkedPermissions) {
            String premissionName = premission.getPermissionName();
            if (premissionName.substring(0, 3).equals("log")) {
                hadLogList.add(premission);
            } else if (premissionName.substring(0, 4).equals("bill")) {
                hadBillList.add(premission);
            } else if (premissionName.substring(0, 4).equals("user")) {
                hadUserList.add(premission);
            } else if (premissionName.substring(0, 4).equals("room")) {
                hadRoomList.add(premission);
            } else if (premissionName.substring(0, 5).equals("major")) {
                hadMajorList.add(premission);
            } else if (premissionName.substring(0, 5).equals("score")) {
                hadScoreList.add(premission);
            }  else if (premissionName.substring(0, 6).equals("course")) {
                hadCourseList.add(premission);
            } else if (premissionName.substring(0, 6).equals("charge")) {
                hadChargeList.add(premission);
            } else if (premissionName.substring(0, 6).equals("refund")) {
                hadRfundList.add(premission);
            } else if (premissionName.substring(0, 7).equals("monitor")) {
                hadMonitorList.add(premission);
            } else if (premissionName.substring(0, 7).equals("teacher")) {
                hadTeacherList.add(premission);
            } else if (premissionName.substring(0, 7).equals("student")) {
                hadStudentList.add(premission);
            } else if (premissionName.substring(0, 7).equals("payment")) {
                hadPaymentList.add(premission);
            } else if (premissionName.substring(0, 8).equals("downSign")) {
                hadDownSignList.add(premission);
            } else if (premissionName.substring(0, 8).equals("register")) {
                hadRegisterList.add(premission);
            } else if (premissionName.substring(0, 9).equals("unpayment")) {
                hadUnpaymentList.add(premission);
            } else if (premissionName.substring(0, 10).equals("department")) {
                hadDepartmentList.add(premission);
            } else if (premissionName.substring(0, 10).equals("messageSet")) {
                hadMessageSetList.add(premission);
            } else if (premissionName.substring(0, 11).equals("memberCount")) {
                hadMemberCountList.add(premission);
            } else if (premissionName.substring(0, 12).equals("disciplinary")) {
                hadDisciplinaryList.add(premission);
            } else if (premissionName.substring(0, 12).equals("changeCourse")) {
                hadChangeCourseList.add(premission);
            } else if (premissionName.substring(0, 12).equals("backcolorSet")) {
                hadDackcolorList.add(premission);
            } else if (premissionName.substring(0, 12).equals("financeCount")) {
                hadFinanceCountList.add(premission);
            } else if (premissionName.substring(0, 15).equals("financeAnalysis")) {
                hadFinanceAnalysisList.add(premission);
            } else if (premissionName.substring(0, 19).equals("financeChangeCourse")) {
                hadFinanceChangeCourseList.add(premission);
            }
        }

        List<Permission> departmentList = new ArrayList<>();
        List<Permission> courseList = new ArrayList<>();
        List<Permission> majorList = new ArrayList<>();
        List<Permission> teacherList = new ArrayList<>();
        List<Permission> studentList = new ArrayList<>();
        List<Permission> disciplinaryList = new ArrayList<>();
        List<Permission> scoreList = new ArrayList<>();
        List<Permission> monitorList = new ArrayList<>();
        List<Permission> changeCourseList = new ArrayList<>();
        List<Permission> memberCountList = new ArrayList<>();
        List<Permission> downSignList = new ArrayList<>();
        List<Permission> backcolorList = new ArrayList<>();
        List<Permission> messageSetList = new ArrayList<>();
        List<Permission> logList = new ArrayList<>();
        List<Permission> userList = new ArrayList<>();
        List<Permission> registerList = new ArrayList<>();
        List<Permission> chargeList = new ArrayList<>();
        List<Permission> financeCountList = new ArrayList<>();
        List<Permission> financeAnalysisList = new ArrayList<>();
        List<Permission> billList = new ArrayList<>();
        List<Permission> unpaymentList = new ArrayList<>();
        List<Permission> paymentList = new ArrayList<>();
        List<Permission> refundList = new ArrayList<>();
        List<Permission> financeChangeCourseList = new ArrayList<>();
        List<Permission> roomList = new ArrayList<>();

        for (Permission premission : uncheckedPermissions) {
            String premissionName = premission.getPermissionName();
            if (premissionName.substring(0, 3).equals("log")) {
                logList.add(premission);
            } else if (premissionName.substring(0, 4).equals("bill")) {
                billList.add(premission);
            } else if (premissionName.substring(0, 4).equals("user")) {
                userList.add(premission);
            } else if (premissionName.substring(0, 4).equals("room")) {
                roomList.add(premission);
            } else if (premissionName.substring(0, 5).equals("major")) {
                majorList.add(premission);
            } else if (premissionName.substring(0, 5).equals("score")) {
                scoreList.add(premission);
            }  else if (premissionName.substring(0, 6).equals("course")) {
                courseList.add(premission);
            } else if (premissionName.substring(0, 6).equals("charge")) {
                chargeList.add(premission);
            } else if (premissionName.substring(0, 6).equals("refund")) {
                refundList.add(premission);
            } else if (premissionName.substring(0, 7).equals("monitor")) {
                monitorList.add(premission);
            } else if (premissionName.substring(0, 7).equals("teacher")) {
                teacherList.add(premission);
            } else if (premissionName.substring(0, 7).equals("student")) {
                studentList.add(premission);
            } else if (premissionName.substring(0, 7).equals("payment")) {
                paymentList.add(premission);
            } else if (premissionName.substring(0, 8).equals("downSign")) {
                downSignList.add(premission);
            } else if (premissionName.substring(0, 8).equals("register")) {
                registerList.add(premission);
            } else if (premissionName.substring(0, 9).equals("unpayment")) {
                unpaymentList.add(premission);
            } else if (premissionName.substring(0, 10).equals("department")) {
                departmentList.add(premission);
            } else if (premissionName.substring(0, 10).equals("messageSet")) {
                messageSetList.add(premission);
            } else if (premissionName.substring(0, 11).equals("memberCount")) {
                memberCountList.add(premission);
            } else if (premissionName.substring(0, 12).equals("disciplinary")) {
                disciplinaryList.add(premission);
            } else if (premissionName.substring(0, 12).equals("changeCourse")) {
                changeCourseList.add(premission);
            } else if (premissionName.substring(0, 12).equals("backcolorSet")) {
                backcolorList.add(premission);
            } else if (premissionName.substring(0, 12).equals("financeCount")) {
                financeCountList.add(premission);
            } else if (premissionName.substring(0, 15).equals("financeAnalysis")) {
                financeAnalysisList.add(premission);
            } else if (premissionName.substring(0, 19).equals("financeChangeCourse")) {
                financeChangeCourseList.add(premission);
            }
        }

        mav.addObject("role", role);

//        mav.addObject("checkedPermissions", checkedPermissions);
        mav.addObject("checkedDepartment", hadDepartmentList);
        mav.addObject("checkedcourse", hadCourseList);
        mav.addObject("checkedmajor", hadMajorList);
        mav.addObject("checkedteacher", hadTeacherList);
        mav.addObject("checkedstudent", hadTeacherList);
        mav.addObject("checkeddisciplinary", hadDisciplinaryList);
        mav.addObject("checkedscore", hadScoreList);
        mav.addObject("checkedmonitor", hadMonitorList);
        mav.addObject("checkedchangeCourse", hadChangeCourseList);
        mav.addObject("checkedmemberCount", hadMemberCountList);
        mav.addObject("checkeddownSign", hadDownSignList);
        mav.addObject("checkedbackcolorSet", hadDackcolorList);
        mav.addObject("checkedmessageSet", hadMessageSetList);
        mav.addObject("checkedlog", hadLogList);
        mav.addObject("checkeduser", hadUserList);
        mav.addObject("checkedregister", hadRegisterList);
        mav.addObject("checkedcharge", hadChargeList);
        mav.addObject("checkedfinanceCount", hadFinanceCountList);
        mav.addObject("checkedfinanceAnalysis", hadFinanceAnalysisList);
        mav.addObject("checkedbill", hadBillList);
        mav.addObject("checkedunpayment", hadUnpaymentList);
        mav.addObject("checkedpayment", hadPaymentList);
        mav.addObject("checkedrefund", hadRfundList);
        mav.addObject("checkedfinanceChangeCourse", hadFinanceChangeCourseList);
        mav.addObject("checkedroom", hadRoomList);

        mav.addObject("uncheckedPermissions", uncheckedPermissions);
        mav.addObject("department", departmentList);
        mav.addObject("course", courseList);
        mav.addObject("major", majorList);
        mav.addObject("teacher", teacherList);
        mav.addObject("student", studentList);
        mav.addObject("disciplinary", disciplinaryList);
        mav.addObject("score", scoreList);
        mav.addObject("monitor", monitorList);
        mav.addObject("changeCourse", changeCourseList);
        mav.addObject("memberCount", memberCountList);
        mav.addObject("downSign", downSignList);
        mav.addObject("backcolorSet", backcolorList);
        mav.addObject("messageSet", messageSetList);
        mav.addObject("log", logList);
        mav.addObject("user", userList);
        mav.addObject("register", registerList);
        mav.addObject("charge", chargeList);
        mav.addObject("financeCount", financeCountList);
        mav.addObject("financeAnalysis", financeAnalysisList);
        mav.addObject("bill", billList);
        mav.addObject("unpayment", unpaymentList);
        mav.addObject("payment", paymentList);
        mav.addObject("refund", refundList);
        mav.addObject("financeChangeCourse", financeChangeCourseList);
        mav.addObject("room", roomList);

        return mav;
    }

    /**
     * 编辑角色
     *
     * @param editPermissions 角色的心权限
     * @param role 角色的其他信息
     * @return 编辑成功返回定向到list请求,编辑失败返回编辑页面
     */
    @RequestMapping("/edit/save")
    public String saveRoleEdit(@RequestParam String[] editPermissions, Role role, HttpSession session,
                               RedirectAttributes redirectAttributes) {
        if (editPermissions.length <= 0) {
            return "redirect:/admin/role/edit/route/" + role.getRoleId() + ".action";
        }

        AdminUser adminUser = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        String logUser = adminUser.getAdminLoginName();

        if (roleManageService.updateRolePermissions(editPermissions, role, logUser)) {
            if (LOG.isInfoEnabled())
                LOG.info("[LGB MANAGE] [OK] {} update role permission with permissions is {} and roleId is {}.", logUser, editPermissions, role.getRoleId());

            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.EDIT_SUCCESS_MESSAGE);

            return "redirect:/admin/role/list.action";
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, ConstantFields.EDIT_FAILURE_MESSAGE);

            return "redirect:/admin/role/edit/route/" + role.getRoleId() + ".action";
        }
    }
}
