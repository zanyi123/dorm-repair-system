/*package org.example.controller;

import org.example.entity.User;
import org.example.service.UserService;
import org.example.service.RepairService;
import org.example.common.Constants;
import org.example.common.Result;


import java.util.Scanner;


public class LoginController {

    // === 成员变量 ===
    private final Scanner scanner = new Scanner(System.in);
    private final UserService userService;
    private final RepairService repairService;


    // ✅ 修改构造方法
    public LoginController(UserService userService, RepairService repairService) {
        this.userService = userService;
        this.repairService = repairService;
    }

     //【功能 1】显示系统主菜单

    public void showMainMenu() {
        System.out.println("\n=================================");
        System.out.println("   🏠 宿舍报修管理系统");
        System.out.println("=================================");

        // 📚 知识点：while 无限循环、boolean 字面量
        while (true) {
            System.out.println("\n【主菜单】");
            System.out.println("1. 登录");
            System.out.println("2. 注册");
            System.out.println("3. 退出系统");
            System.out.print("请选择操作（输入 1-3）：");

            // 📚 知识点：Scanner 类、next() 方法接收字符串
            String choice = scanner.next();

            // 📚 知识点：字符串 equals() 比较、if 条件判断
            if (choice.equals("1")) {
                doLogin();
            } else if (choice.equals("2")) {
                doRegister();
            } else if (choice.equals("3")) {
                System.out.println("感谢使用，再见！");
                break;
            } else {
                System.out.println("输入无效，请重新输入！");
            }
        }
    }


     //【功能 2】处理用户注册

    private void doRegister() {
        System.out.println("\n===== 用户注册 =====");

        // 📚 知识点：Scanner 类、nextInt() 方法接收整数
        System.out.print("请选择角色（1-学生，2-管理员）：");
        int role = scanner.nextInt();

        // 📚 知识点：范围判断、逻辑或 ||、逻辑与 &&、if 条件
        if (role != Constants.ROLE_STUDENT && role != Constants.ROLE_ADMIN) {
            System.out.println("❌ 角色选择错误！请输入 1 或 2。");
            return;
        }

        // 📚 知识点：Scanner 类、next() 方法接收字符串
        System.out.print("请输入账号（学号/工号）：");
        String account = scanner.next();

        System.out.print("请输入密码：");
        String password = scanner.next();

        System.out.print("请确认密码：");
        String confirmPassword = scanner.next();

        // 📚 知识点：字符串 equals() 比较、逻辑非 !、if 条件
        if (!confirmPassword.equals(password)) {
            System.out.println(" 前后输入的密码不一致！");
            return;
        }

        // 📚 知识点：调用 Service 层方法、传参、方法返回值
        Result<Boolean> result = userService.register( account, password,role);

        // 📚 知识点：对象方法调用、if 条件判断
        if (result.isSuccess()) {
            System.out.println("✅ " + result.getMessage());
        } else {
            System.out.println("❌ " + result.getMessage());
        }
    }


      //【功能 3】处理用户登录

    private void doLogin() {
        System.out.println("\n===== 用户登录 =====");

        //  知识点：Scanner 类、next() 方法接收字符串
        System.out.print("请输入账号：");
        String account = scanner.next();

        System.out.print("请输入密码：");
        String password =scanner.next() ;

        //  知识点：调用 Service 层方法、传参、方法返回值
        Result<User> result = userService.login(account, password);

        //  知识点：对象方法调用、逻辑非 !、if 条件判断
        if (!result.isSuccess()) {
            System.out.println("❌ " + result.getMessage());
            return;
        }

        //  知识点：从 Result 中获取数据、对象赋值
        User user = result.getData();

        System.out.println("✅ " + result.getMessage());

        //  知识点：常量比较、if-else 多分支、对象创建
        if (user.getRole() == Constants.ROLE_STUDENT) {
            System.out.println("正在进入学生界面...");
            //  知识点：创建对象、调用方法
            StudentController studentController = new StudentController(
                    user,
                    repairService,  // 从成员变量获取
                    userService     // 从成员变量获取
            );
            studentController.showmenu();
        } else if (user.getRole() == Constants.ROLE_ADMIN) {
            System.out.println("正在进入管理员界面...");
            //  知识点：创建对象、调用方法
            AdminController adminController = new AdminController(
                    user,
                    repairService
            );
            adminController.showMenu();
        } else {
            System.out.println("❌ 未知角色，无法进入系统！");
        }
    }
}

*/