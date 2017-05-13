package com.dls.function.admin.login.controller;

import com.google.common.base.Optional;
import com.dls.arc.utils.ConstantFields;
import com.dls.arc.utils.PasswordUtils;
import com.dls.function.admin.login.AdminUser;
import com.dls.function.admin.login.service.AdminLoginServiceI;
import com.dls.function.support.shiro.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminLoginController {
    private static final Logger LOG = LoggerFactory.getLogger(AdminLoginController.class);

    @Autowired
    private AdminLoginServiceI adminLoginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user, HttpSession session , RedirectAttributes redirectAttributes
            ,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/admin/routeLogin.action";
        }

        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.login(new UsernamePasswordToken(user.getUsername(), PasswordUtils.encrypt(user.getPassword())));

            User loginUser = (User) currentUser.getSession().getAttribute(ConstantFields.LOGIN_KEY);
            Optional<User> optional = Optional.fromNullable(loginUser);

            if (!optional.isPresent()) {
                return "redirect:/admin/routeLogin.action";
            } else {
                AdminUser adminUser = adminLoginService.queryAdminUser(loginUser.getAdminUserId());
                session.setAttribute(ConstantFields.SESSION_ADMIN_KEY, adminUser);

                String backgroundColor = adminLoginService.configColor();
                session.setAttribute(ConstantFields.SESSION_BG_COLOR, backgroundColor);
            }

            if (LOG.isInfoEnabled())
                LOG.info("[DLS MANAGE] {} login system at {} .", loginUser.getUsername(), DateTime.now());

            return "redirect:/admin/home/index.action";
        } catch (AuthenticationException e) {
            redirectAttributes.addFlashAttribute("message", "用户名或者密码错误");

            return "redirect:/admin/routeLogin.action";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet(HttpSession session) {
        AdminUser loginUser = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);

        Optional<AdminUser> optional = Optional.fromNullable(loginUser);

        if (optional.isPresent()) {
            return "redirect:/admin/home/index.action";
        }

        return "redirect:/admin/routeLogin.action";
    }

    @RequestMapping(value = "/routeLogin", method = RequestMethod.GET)
    public String routeLogin(HttpSession session) {
        AdminUser loginUser = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);

        Optional<AdminUser> optional = Optional.fromNullable(loginUser);

        if (optional.isPresent()) {
            return "redirect:/admin/home/index.action";
        }

        return "admin/login/adminLogin";
    }

    @RequestMapping(value = "/routePass", method = RequestMethod.GET)
    public String routePassword() {
        return "admin/login/adminPassword";
    }

    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public ModelAndView password(AdminUser adminUser, HttpSession session) {
        ModelAndView mav = new ModelAndView("redirect:/admin/routeLogin.action");

        AdminUser newUser = adminLoginService.resetPassword(adminUser);
        Optional<AdminUser> optional = Optional.fromNullable(newUser);

        if (optional.isPresent()) {
            session.removeAttribute(ConstantFields.LOGIN_KEY);
            if (LOG.isInfoEnabled())
                LOG.info("[DLS MANAGE] {} reset password.", newUser.getAdminName());

            return mav;
        }

        return new ModelAndView("redirect:/admin/routePass.action");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();

        return "redirect:/admin/routeLogin.action";
    }
}
