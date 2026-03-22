package org.example.common;

public class Constants {
    // === 角色定义 ===
    public static final int ROLE_STUDENT = 1;
    public static final int ROLE_ADMIN = 2;

    // === 报修单状态 ===
    public static final int STATUS_PENDING = 1;    // 待处理
    public static final int STATUS_PROCESSING = 2; // 处理中
    public static final int STATUS_DONE = 3;       // 已完成

    // 正则表达式
    // 学生：3125 或 3225 开头 + 6位数字
    public static final String REGEX_STUDENT = "^(3125|3225)\\d{6}$";
    // 管理员：0025 开头 + 6位数字
    public static final String REGEX_ADMIN = "^0025\\d{6}$";
}