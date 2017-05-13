package com.dls.arc.utils;

public class ConstantFields {
    /* 管理员登陆的KEY */
    public static final String SESSION_ADMIN_KEY = "adminLogin";

    /* 成绩管理老师登录的KEY*/
    public static final String SESSION_TEACHER_SCORE_LOGIN_KEY = "teacherScore";

    /* 分页中每页数据数 */
    public static final int DEFAULT_PAGE_SIZE = 20;
    /* ajax分页中数据的KEY */
    public static final String PAGE_KEY = "page";
    /* 日志查询时存储LogContent条件的Session Key */
    public static final String SESSION_LOG_SEARCH_KEY = "logSearch";
    /* 学员查询时存储StudentUser条件的Session Key */
    public static final String SESSION_STU_SEARCH_KEY = "stuSearch";

    /**
     * 默认邮件配置项
     */
    public static final String MAIL_DEFAULT_HOST_KEY = "mail.smtp.host";
    public static final String MAIL_DEFAULT_AUTH_ENABLE_KEY = "mail.smtp.auth";
    public static final String MAIL_DEFAULT_PORT_KEY = "mail.smtp.port";
    public static final String MAIL_DEFAULT_USER_KEY = "mail.smtp.user";
    public static final String MAIL_DEFAULT_USER_PASS_KEY = "mail.smtp.pass";
    public static final String MAIL_DEFAULT_STARTTLS_ENABLE_KEY = "mail.smtp.starttls.enable";
    /* 添加成功消息Key */
    public static final String ADD_SUCCESS_KEY = "addMessage";
    /* 添加成功消息 */
    public static final String ADD_SUCCESS_MESSAGE = "添加成功";
    /* 添加失败消息Key */
    public static final String ADD_FAILURE_KEY = "addFailureMessage";
    /* 添加失败消息 */
    public static final String ADD_FAILURE_MESSAGE = "添加失败";
    /* 编辑Key */
    public static final String EDIT_OBJECT_KEY = "edit";
    /* 删除Key */
    public static final String DELETE_OBJECT_KEY = "delete";
    /* 编辑成功消息Key */
    public static final String EDIT_SUCCESS_KEY = "editMessage";
    /* 编辑成功消息 */
    public static final String EDIT_SUCCESS_MESSAGE = "编辑成功";
    /* 编辑失败消息Key */
    public static final String EDIT_FAILURE_KEY = "editFailureMessage";
    /* 编辑失败消息 */
    public static final String EDIT_FAILURE_MESSAGE = "编辑失败";
    /* 删除成功消息Key */
    public static final String DELETE_SUCCESS_KEY = "deleteMessage";
    /* 删除成功消息 */
    public static final String DELETE_SUCCESS_MESSAGE = "删除成功";
    /* 删除失败消息Key */
    public static final String DELETE_FAILURE_KEY = "deleteFailureMessage";
    /* 删除失败消息 */
    public static final String DELETE_FAILURE_MESSAGE = "删除失败";
    /* 教师查询Key */
    public static final String SESSION_TEACHER_SEARCH_KEY = "teacherSearch";
    /* 换卡成功消息key */
    public static final String TURN_CARD_SUCCESS_KEY = "turnCardMessage";
    /* 换卡成功消息 */
    public static final String TURN_CARD_SUCCESS_MESSAGE = "换卡成功";
    /* 换卡失败消息key */
    public static final String TURN_CARD_FAILURE_KEY = "turnCardFailureMessage";
    /* 换卡失败消息 */
    public static final String TURN_CARD_FAILURE_MESSAGE = "换卡失败";
    /* 课程查询Key */
    public static final String SESSION_COURSE_SEARCH_KEY = "courseSearch";
    /* 成绩查询Key */
    public static final String SESSION_SCORE_SEARCH_KEY = "scoreSearch";
    /* 收费查询Key */
    public static final String SESSION_FINANCE_SEARCH_KEY = "financeSearch";
    /* 教室查询的Key */
    public static final String SESSION_ROOM_SEARCH_KEY = "roomSearch";
    /* session学员key */
    public static final String SESSION_STU_ID_KEY = "stuId";
    /* 学员登陆的KEY */
    public static final String SESSION_STU_KEY = "stuLogin";
    /* 学员登陆的卡号 */
    public static final String STU_CARD_NUM = "stuCardNum";
    /* 课程信息key */
    public static final String COURSE_INFO_KEY = "course";
    /* 学员查询失败Key */
    public static final String DELETE_STU_FAILURE_KEY = "selectStuFailMessage";
    /* 学员查询失败消息 */
    public static final String DELETE_STU_FAILURE_MESSAGE = "该学员不存在";
    /* 违纪信息key */
    public static final String DISCIPLINARY_INFO_KEY = "disciplinary";

    /* 消息操作key*/
    public static final String OPERATION_MESSAGE = "message";
    /* 操作成功消息 */
    public static final String OPERATION_SUCCESS_MESSAGE = "操作成功";
    /* 操作失败消息 */
    public static final String OPERATION_FAILURE_MESSAGE = "操作失败";

    /* 默认配置的背景色 */
    public static final String DEFAULT_BACKGROUND_COLOR_SETTING = " FFFECE";
    public static final String CONFIG_SUCCESS_MESSAGE = "配置成功";
    /* 配置表的默认主键 */
    public static final int DEFAULT_CONFIG_ID = 1;
    /* 背景色配置Key */
    public static final String SESSION_BG_COLOR = "backgroundColor";

    /* 财务角色的常量值 */
    public static final int ADMIN_LOGIN_CAIWU_ROLE = 4;
    public static final int ADMIN_LOGIN_ADMIN_ROLE = 1;

    /* 学习管角色理员的常量值*/
    public static final int ADMIN_LOGIN_STUDY_ROLE = 2;

    /* 系主任角色的常量值*/
    public static final int ADMIN_LOGIN_DEPARTMENT_ROLE = 6;

    /* 退课路由信息 */
    public static final String ROUTE_REFUND_SUCCESS_MESSAGE = "不能到达退课页面";
    /* 退课信息 */
    public static final String REFUND_SUCCESS_MESSAGE = "退课成功";
    public static final String REFUND_FAILURE_MESSAGE = "退课失败";

    /* 学院选课表中的课程来源换课标识 */
    public static final int SIGN_UP_COME_FROM_TURN_COURSE = 3;
    public static final String CHANGE_COURSE_TUITION_SUCCESS_MESSAGE = "换课缴费成功";
    public static final String CHANGE_COURSE_TUITION_FAILURE_MESSAGE = "换课缴费失败";

    /* 学员考勤是否有课打卡标示 0表示有课 1表示没有课 */
    public static final int STUDENT_CHECK_HAS_COURSE_FLAG = 0;
    public static final int STUDENT_CHECK_NOT_COURSE_FLAG = 1;
    public static final String SESSION_REGISTER_SEARCH_KEY = "pageRegister";

    /* 学员考勤打卡查询的默认查询多少天没有打卡的默认天 */
    public static final int DEFAULT_OUT_OF_DATE_RANGE_FOR_CARD_CHECK = 15;
    public static final String STUDENT_CRAD_CHECK_QUERY_CONDITION = "studentCardCheckCondition";
    public static final String STUDENT_CRAD_CHECK_HISTORY_QUERY_CONDITION = "studentCardCheckHistoryCondition";
    /* 默认图片名 */
    public static final String DEFAULT_STUDENT_IMAGE_NAME = "default_student";
    /* shiro登录状态Key */
    public static final String LOGIN_KEY = "shiroLogin";
    /* 超级管理员用户名 */
    public static final String SUPER_ADMIN_NAME = "admin";
}
