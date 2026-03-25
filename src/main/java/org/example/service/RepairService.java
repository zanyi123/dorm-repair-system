/*package org.example.service;

import org.example.entity.RepairOrder;
import org.example.mapper.RepairOrderMapper;
import org.example.common.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RepairService {

    // ✅ 注入 Mapper（Spring 自动管理）
    @Autowired
    private RepairOrderMapper repairOrderMapper;


     //【功能 1】获取所有报修单

    public Result<List<RepairOrder>> getAllOrders() {
        try {
            List<RepairOrder> list = repairOrderMapper.selectList(null);
            return Result.success(list, "查询成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("查询失败：" + e.getMessage());
        }
    }


     // 【功能 2】获取我的报修单
    public Result<List<RepairOrder>> getMyOrders(Integer studentId) {
        try {
            LambdaQueryWrapper<RepairOrder> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(RepairOrder::getStudentId, studentId);
            List<RepairOrder> list = repairOrderMapper.selectList(wrapper);
            return Result.success(list, "查询成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("查询失败：" + e.getMessage());
        }
    }


      //【功能 3】创建报修单

    public Result<Boolean> createOrder(Integer studentId, String building, String room,
                                       String deviceType, String description) {
        try {
            RepairOrder order = new RepairOrder();
            order.setStudentId(studentId);
            order.setBuilding(building);
            order.setRoom(room);
            order.setDeviceType(deviceType);
            order.setDescription(description);
            order.setStatus(1);  // 待处理
            order.setCreateTime(java.time.LocalDateTime.now().toString().replace("T", " "));

            int rows = repairOrderMapper.insert(order);

            if (rows > 0) {
                return Result.success(true, "报修单提交成功！");
            } else {
                return Result.fail("提交失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("提交失败：" + e.getMessage());
        }
    }


      //【功能 4】更新报修单状态

    public Result<Boolean> updateStatus(Integer orderId, Integer newStatus) {
        try {
            RepairOrder order = repairOrderMapper.selectById(orderId);
            if (order == null) {
                return Result.fail("报修单不存在！");
            }

            order.setStatus(newStatus);
            order.setCreateTime(java.time.LocalDateTime.now().toString().replace("T", " "));

            int rows = repairOrderMapper.updateById(order);

            if (rows > 0) {
                return Result.success(true, "状态更新成功！");
            } else {
                return Result.fail("状态更新失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("状态更新失败：" + e.getMessage());
        }
    }


     //【功能 5】删除报修单

    public Result<Boolean> deleteOrder(Integer orderId) {
        try {
            int rows = repairOrderMapper.deleteById(orderId);

            if (rows > 0) {
                return Result.success(true, "删除成功！");
            } else {
                return Result.fail("删除失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("删除失败：" + e.getMessage());
        }
    }
}
*/



package org.example.service;

import org.example.entity.RepairOrder;
import org.example.entity.User;
import org.example.mapper.RepairOrderMapper;
import org.example.mapper.UserMapper;
import org.example.common.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RepairService {

    @Autowired
    private RepairOrderMapper repairOrderMapper;

    @Autowired
    private UserMapper userMapper;

    public Result<List<RepairOrder>> getAllOrders() {
        try {
            List<RepairOrder> list = repairOrderMapper.selectList(null);
            return Result.success(list, "查询成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("查询失败：" + e.getMessage());
        }
    }

    public Result<List<RepairOrder>> getMyOrders(Integer studentId) {
        try {
            LambdaQueryWrapper<RepairOrder> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(RepairOrder::getStudentId, studentId);
            List<RepairOrder> list = repairOrderMapper.selectList(wrapper);
            return Result.success(list, "查询成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("查询失败：" + e.getMessage());
        }
    }

    public Result<Boolean> createOrder(Integer studentId, String building, String room,
                                       String deviceType, String description) {
        try {
            RepairOrder order = new RepairOrder();
            order.setStudentId(studentId);
            order.setBuilding(building);
            order.setRoom(room);
            order.setDeviceType(deviceType);
            order.setDescription(description);
            order.setStatus(1);
            order.setCreateTime(LocalDateTime.now().toString().replace("T", " "));

            int rows = repairOrderMapper.insert(order);

            if (rows > 0) {
                return Result.success(true, "报修单提交成功！");
            } else {
                return Result.fail("提交失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("提交失败：" + e.getMessage());
        }
    }

    public Result<Boolean> updateStatus(Integer orderId, Integer newStatus) {
        try {
            RepairOrder order = repairOrderMapper.selectById(orderId);
            if (order == null) {
                return Result.fail("报修单不存在！");
            }

            order.setStatus(newStatus);
            order.setUpdateTime(LocalDateTime.now().toString().replace("T", " "));

            int rows = repairOrderMapper.updateById(order);

            if (rows > 0) {
                return Result.success(true, "状态更新成功！");
            } else {
                return Result.fail("状态更新失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("状态更新失败：" + e.getMessage());
        }
    }

    public Result<Boolean> deleteOrder(Integer orderId) {
        try {
            int rows = repairOrderMapper.deleteById(orderId);

            if (rows > 0) {
                return Result.success(true, "删除成功！");
            } else {
                return Result.fail("删除失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("删除失败：" + e.getMessage());
        }
    }

    public Result<Boolean> bindDorm(Integer userId, String building, String room) {
        try {
            User user = userMapper.selectById(userId);
            if (user == null) {
                return Result.fail("用户不存在！");
            }

            user.setDormBuilding(building);
            user.setDormRoom(room);

            int rows = userMapper.updateById(user);

            if (rows > 0) {
                return Result.success(true, "绑定成功！");
            } else {
                return Result.fail("绑定失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("绑定失败：" + e.getMessage());
        }
    }
}