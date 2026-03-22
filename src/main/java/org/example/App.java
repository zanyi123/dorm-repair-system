package org.example;

import org.example.controller.LoginController;
import org.example.service.UserService;
import org.example.service.RepairService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.example.common.MybatisPlusConfig;  // ← 确保导入这个

public class App {
    public static void main(String[] args) {
        System.out.println("*****GDUT 宿舍管理系统 1.0*****");
        System.out.println("欢迎您的使用~");

        // 创建 Spring 容器
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MybatisPlusConfig.class);

        // 从容器获取 Service
        UserService userService = context.getBean(UserService.class);
        RepairService repairService = context.getBean(RepairService.class);

        // 手动创建 LoginController（传入 Service）
        LoginController loginController = new LoginController(userService, repairService);
        loginController.showMainMenu();

        context.close();
    }
}