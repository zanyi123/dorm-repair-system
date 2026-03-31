/*package org.example.controller;

import org.example.common.Constants;
import org.example.entity.User;
import org.example.entity.RepairOrder;
import org.example.service.RepairService;
import org.example.common.Result;


import java.util.List;
import java.util.Scanner;


public class AdminController {

    private final User currentUser;
    private final Scanner scanner = new Scanner(System.in);
    private final RepairService repairService ;

    public AdminController(User user, RepairService repairService) {
        this.currentUser = user;
        this.repairService = repairService;
    }*/

/*
    public void showMenu() {

        System.out.println("\n===== 欢迎管理员：" + currentUser.getName() + " =====");

        //  知识点：while 无限循环、boolean 字面量
        while (true){
            System.out.println("\n【管理员菜单】");
            System.out.println("1. 查看所有报修单");
            System.out.println("2. 更新报修单状态");
            System.out.println("3. 删除报修单");
            System.out.println("4. 退出登录");
            System.out.print("请选择操作（输入 1-4）：");

            //  知识点：Scanner 类、next() 方法接收字符串
            String choice = scanner.next();

            //  知识点：字符串 equals() 比较、if 条件判断
            if (choice.equals("1")) {
                viewAllOrders();
            }
            // 知识点：else if 多分支判断、字符串 equals() 比较
            else if (choice.equals("2")) {
                updateOrderStatus();
            }
            //  知识点：else if 多分支判断、字符串 equals() 比较
            else if (choice.equals("3")) {
                deleteOrder();
            }
            //  知识点：else if 多分支判断、字符串 equals() 比较
            else if (choice.equals("4")) {
                System.out.println("已退出登录，再见！");
                // 📚 知识点：break 跳出循环
                break;
            } else {
                System.out.println("输入无效，请重新输入！");
            }
        }
    }


     //【功能 2】查看所有报修单

    private void viewAllOrders() {
        System.out.println("\n【所有报修单列表】");

        // 📚 知识点：调用 Service 层方法、泛型、方法返回值
        Result<List<RepairOrder>> result = repairService.getAllOrders();

        // 📚 知识点：对象方法调用、逻辑非 !、if 条件判断
        if (!result.isSuccess()) {
            // 📚 知识点：字符串拼接、对象方法调用（Getter）
            System.out.println("❌ " + result.getMessage());
            return;
        }

        // 📚 知识点：对象方法调用、泛型、获取 Result 中的数据
        List<RepairOrder> list = result.getData();

        // 📚 知识点：List 集合方法、isEmpty() 判断、if 条件
        if (list.isEmpty()) {
            System.out.println("暂无报修单。");
            return;
        }

        System.out.println("ID\t学生 ID\t楼栋\t房间\t设备\t状态\t时间");
        System.out.println("--------------------------------------------------------");

        // 📚 知识点：增强 for 循环、集合遍历、对象声明
        for (RepairOrder order:list) {
            // 📚 知识点：调用本类方法、对象方法调用（Getter）、传参
            String statusText = getStatusText(order.getStatus());
            // 📚 知识点：对象方法调用（Getter）、字符串拼接、\t 制表符
            System.out.println(order.getId() + "\t" +
                     order.getStudentId()+ "\t" +
                     order.getBuilding()+ "\t" +
                     order.getRoom()+ "\t" +
                     order.getDeviceType()+ "\t" +
                     statusText+ "\t" +
                     order.getCreateTime()

                    );
        }
    }

    //【功能 3】更新报修单状态

    private void updateOrderStatus() {
        System.out.println("\n【更新报修单状态】");

        System.out.print("请输入报修单 ID:");
        // 📚 知识点：Scanner 类、nextInt() 方法接收整数、变量声明
        int orderId = scanner.nextInt();

        System.out.println("状态选项：1-待处理  2-处理中  3-已完成");
        System.out.print("请输入新状态（1-3）：");
        // 📚 知识点：Scanner 类、nextInt() 方法接收整数、变量声明
        int newStatus = scanner.nextInt();

        // 📚 知识点：范围判断、关系运算符 < 和 >、逻辑或 ||、if 条件
        if (newStatus < 1 || newStatus > 3) {
            System.out.println("❌ 状态输入错误！请输入 1-3。");
            return;
        }

        // 📚 知识点：调用 Service 层方法、传参、方法返回值
        Result<Boolean> result = repairService.updateStatus(orderId, newStatus);

        // 📚 知识点：对象方法调用、if 条件判断
        if (result.isSuccess()) {
            // 📚 知识点：字符串拼接、对象方法调用（Getter）
            System.out.println("✅ " +result.getMessage() );
        } else {
            // 📚 知识点：字符串拼接、对象方法调用（Getter）
            System.out.println("❌ " +result.getMessage());
        }
    }


     //【功能 4】删除报修单

    private void deleteOrder() {
        System.out.println("\n【删除报修单】");

        System.out.print("请输入要删除的报修单 ID:");
        // 📚 知识点：Scanner 类、nextInt() 方法接收整数、变量声明
        int orderId = scanner.nextInt();

        System.out.print("确认删除？(y/n):");
        // 📚 知识点：Scanner 类、next() 方法接收字符串、变量声明
        String confirm = scanner.next();

        // 📚 知识点：字符串 equals() 比较、逻辑非 !、逻辑与 &&、if 条件
        if (!confirm.equals("y")&&(!confirm.equals("Y"))) {
            System.out.println("输入有误，请按照提示输入小写字母！");
            return ;
        }

        // 📚 知识点：调用 Service 层方法、传参、方法返回值
        Result<Boolean> result = repairService.deleteOrder(orderId);

        // 📚 知识点：对象方法调用、if 条件判断
        if (result.isSuccess()) {
            // 📚 知识点：字符串拼接、对象方法调用（Getter）
            System.out.println("✅ " + result.getMessage());
        } else {
            // 📚 知识点：字符串拼接、对象方法调用（Getter）
            System.out.println("❌ " + result.getMessage());

        }
    }


     // 【功能 5】状态码转文字

    private String getStatusText(int status) {
            if (status == Constants.STATUS_PENDING) {
                return "待处理";
            } else if (status == Constants.STATUS_PROCESSING) {
                return "处理中";
            } else if (status == Constants.STATUS_DONE) {
                return "已完成";
            } else {
                return "未知";
            }
        }
    }

*/

package org.example.controller;

import org.example.entity.RepairOrder;
import org.example.service.RepairService;
import org.example.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private RepairService repairService;

    // 查询全部报修单（可带状态筛选）
    @GetMapping("/orders")
    public Result<List<RepairOrder>> getAllOrders(@RequestParam(required = false) Integer status) {
        if (status != null) {
            return repairService.getOrdersByStatus(status);
        }
        return repairService.getAllOrders();
    }

    // 修改报修单状态
    @PutMapping("/orders/{id}/status")
    public Result<Boolean> updateStatus(@PathVariable Integer id,
                                        @RequestParam Integer status) {
        return repairService.updateStatus(id, status);
    }

    // 删除报修单
    @DeleteMapping("/orders/{id}")
    public Result<Boolean> deleteOrder(@PathVariable Integer id) {
        return repairService.deleteOrder(id);
    }
}