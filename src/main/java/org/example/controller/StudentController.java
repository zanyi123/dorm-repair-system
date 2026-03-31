package org.example.controller;

import org.example.entity.RepairOrder;
import org.example.service.RepairService;
import org.example.mapper.RepairOrderMapper;
import org.example.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/student")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private RepairService repairService;

    @Autowired
    private RepairOrderMapper repairOrderMapper;

    // 查询报修单列表
    @GetMapping("/orders")
    public Result<List<RepairOrder>> getMyOrders(@RequestParam Integer studentId) {
        return repairService.getMyOrders(studentId);
    }

    // 创建报修单
    @PostMapping("/orders")
    public Result<Boolean> createOrder(@RequestBody RepairOrder order) {
        try {
            order.setStatus(1);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            order.setCreateTime(sdf.format(new Date()));

            return repairService.createOrder(
                    order.getStudentId(),
                    order.getBuilding(),
                    order.getRoom(),
                    order.getDeviceType(),
                    order.getDescription()
            );
        } catch (Exception e) {
            return Result.fail("创建失败：" + e.getMessage());
        }
    }

    // 取消报修单
    @PutMapping("/orders/{id}/cancel")
    public Result<Boolean> cancelOrder(@PathVariable Integer id,
                                       @RequestParam Integer studentId) {
        try {
            // 用 Mapper 查询
            RepairOrder order = repairOrderMapper.selectById(id);
            if (order == null) {
                return Result.fail("报修单不存在");
            }

            // 验证是否属于自己的
            if (!order.getStudentId().equals(studentId)) {
                return Result.fail("无权操作此报修单");
            }

            // 只能取消待处理的
            if (order.getStatus() != 1) {
                return Result.fail("只能取消待处理的报修单");
            }

            // 修改状态为已取消（3=已取消）
            order.setStatus(3);
            repairOrderMapper.updateById(order);

            return Result.success(true, "取消成功");
        } catch (Exception e) {
            return Result.fail("取消失败：" + e.getMessage());
        }
    }

    // 绑定宿舍
    @PutMapping("/dorm")
    public Result<Boolean> bindDorm(@RequestParam Integer userId,
                                    @RequestParam String building,
                                    @RequestParam String room) {
        return repairService.bindDorm(userId, building, room);
    }
}