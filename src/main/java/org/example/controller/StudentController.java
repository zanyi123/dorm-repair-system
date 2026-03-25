/*package org.example.controller;

import org.example.common.Constants;
import org.example.entity.User;
import org.example.entity.RepairOrder;
import org.example.service.RepairService;
import org.example.common.Result;
import org.example.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;




public class StudentController {
    private final User currentUser;
    private final Scanner scanner = new Scanner(System.in);
    private final RepairService repairService ;
    private final UserService userService ;

    public StudentController(User user,RepairService repairService,UserService userService) {
        this.currentUser = user;
        this.repairService = repairService;
        this.userService = userService;
    }

    //功能1：主菜单


    public void showmenu() {

        System.out.println("\n======欢迎来到学生界面，用户："+ currentUser.getName()+"======");

        while (true){
            System.out.println("\n【学生菜单】");
            System.out.println("1. 绑定/修改宿舍信息");
            System.out.println("2. 创建宿舍报修单");
            System.out.println("3. 查看我的报修单记录");
            System.out.println("4. 修改我的登录密码");
            System.out.println("5. 退出登录");
            System.out.print("请选择操作（输入 1-5）：");


           String choice = scanner.next();
            if (choice.equals("1")) {
                bindDorm();
            }
            // 📚 知识点：else if 多分支判断、字符串 equals() 比较
            else if (choice.equals("2")) {
                createOrder();
            }
            // 📚 知识点：else if 多分支判断、字符串 equals() 比较
            else if (choice.equals("3")) {
                viewMyOrders();
            }
            // 📚 知识点：else if 多分支判断、字符串 equals() 比较
            else if (choice.equals("4")) {
                alterPassword();
            }
            else if (choice.equals("5")) {
                System.out.println("成功退出登录");

                break;
            } else {
                System.out.println("输入无效，请重新输入！");
            }
        }
    }

    //功能2：绑定/修改学生宿舍信息
    private void bindDorm() {
        System.out.println("\n【绑定/修改学生宿舍信息】");

        System.out.print("请输入您新的宿舍楼栋：");

        String dormBuilding = scanner.next();

        System.out.print("请输入您新的宿舍房间：");

        String dormRoom = scanner.next();


        System.out.print("确认更新？(y/n):");

        String confirm = scanner.next();
        if (!confirm.equals("y")) {
            System.out.println("已取消更新。");
            return ;
        }

        Result<Boolean> result = userService.updateUser(currentUser);
        if (result.isSuccess()){
            System.out.println( result.getMessage());
        }else {
            System.out.println( result.getMessage());
        }





    }


    //功能3：创建宿舍报修单
    private void createOrder() {
        System.out.println("\n【创建宿舍报修单】");

        if (currentUser.getDormBuilding() == null){
            System.out.println("您还未绑定宿舍信息，请绑定！");
            return;
        }

        System.out.println("请输入报修设备类型：");
        String deviceType = scanner.next();

        System.out.println("对报修需设备的内容描述：");
        String desc = scanner.next();

        Result<Boolean> result = repairService.createOrder(currentUser.getId(),currentUser.getDormBuilding(),currentUser.getDormRoom(),deviceType,desc);
        if (result.isSuccess()){
            System.out.println("✅ " + result.getMessage());

        }else {
            System.out.println("❌ " + result.getMessage());
        }



    }


    //功能4：查看我的报修单记录
    private void viewMyOrders() {
        System.out.println("\n【查看我的报修单记录】");

        Result<List<RepairOrder>> result = repairService.getMyOrders(currentUser.getId());

        if (!result.isSuccess()){
            System.out.println(result.getMessage());
            return;
        }

        List<RepairOrder> list =  result.getData();

        if (list.isEmpty()){
            System.out.println("没有报修单哟。。。");
            return;
        }

        System.out.println("报修单编号\t宿舍楼号\t  宿舍门牌\t   设备类型\t     报修内容\t      报修状态\t     报修时间");

        for(RepairOrder order : list){
            System.out.println(
                    order.getId() + "          \t" +
                    order.getBuilding() + "    \t" +
                    order.getRoom() + "\t        " +
                    order.getDeviceType() + "      \t" +
                    order.getDescription() + "    \t" +
                    order.getStatus() + "    \t" +
                    order.getCreateTime()
            );
        }


    }


    //功能5：修改学生的登录密码
    private  void  alterPassword() {
        System.out.println("\n【修改学生的登录密码】");

        System.out.println("请输入原来的密码以确认身份：");
        String oldPassword = scanner.next();



        System.out.println("请输入新的密码：");
        String newPassword = scanner.next();

        System.out.println("请再次确认新的密码：");
        String newPassword2 = scanner.next();

        if(!newPassword2.equals(newPassword)){
            System.out.println("前后输入的密码不一致！请重新输入");
            return;
        }

        //  旧密码验证交给 Service 层
        Result<Boolean> result = userService.updatePassword(
                currentUser.getAccount(),
                oldPassword,
                newPassword2
        );

        if (result.isSuccess()){
            System.out.println("✅ " + result.getMessage());
        }else {
            System.out.println("❌ " + result.getMessage());
        }




    }

    //不对外的状态功能
    private String getStatusText(int status){
        System.out.println("\n【状态码转文字】");

        switch (status){
            case  Constants.STATUS_PENDING:
                return "待处理";


            case Constants.STATUS_PROCESSING:
                return "处理中";


            case Constants.STATUS_DONE:
                return "已完成";

            default:
                return "未知状态";

        }
    }

}*/


package org.example.controller;

import org.example.entity.RepairOrder;
import org.example.service.RepairService;
import org.example.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private RepairService repairService;

    @GetMapping("/orders")
    public Result<List<RepairOrder>> getMyOrders(@RequestParam Integer studentId) {
        return repairService.getMyOrders(studentId);
    }

    @PostMapping("/orders")
    public Result<Boolean> createOrder(@RequestBody RepairOrder order) {
        return repairService.createOrder(
                order.getStudentId(),
                order.getBuilding(),
                order.getRoom(),
                order.getDeviceType(),
                order.getDescription()
        );
    }

    @PutMapping("/dorm")
    public Result<Boolean> bindDorm(@RequestParam Integer userId,
                                    @RequestParam String building,
                                    @RequestParam String room) {
        return repairService.bindDorm(userId, building, room);
    }
}
