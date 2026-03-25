/*package org.example.service;

import org.example.entity.User;
import org.example.mapper.UserMapper;
import org.example.common.Constants;
import org.example.common.MD5Util;
import org.example.common.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserService {

    // ✅ 注入 Mapper（Spring 自动管理）
    @Autowired
    private UserMapper userMapper;


      //【功能 1】用户注册

    public Result<Boolean> register(String account, String password, int role) {

        // ==================== 正则校验 ====================
        if (role == Constants.ROLE_STUDENT) {
            if (!account.matches(Constants.REGEX_STUDENT)) {
                return Result.fail("账号格式错误：应输入以3125开头或3225的账号");
            }
        } else if (role == Constants.ROLE_ADMIN) {
            if (!account.matches(Constants.REGEX_ADMIN)) {
                return Result.fail("账号格式输入错误：需以0025开头的账号");
            }
        }

        try {
            // 检查账号是否已存在
            LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(User::getAccount, account);
            User existUser = userMapper.selectOne(wrapper);

            if (existUser != null) {
                return Result.fail("注册失败：该账号已被注册！");
            }

            // 创建 User 对象
            User newUser = new User();
            newUser.setAccount(account);
            newUser.setPassword(MD5Util.encrypt(password));
            newUser.setRole(role);

            int rows = userMapper.insert(newUser);

            if (rows > 0) {
                return Result.success(true, "注册成功！请返回登录。");
            } else {
                return Result.fail("注册失败：数据库写入错误。");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("注册失败：" + e.getMessage());
        }
    }


     // 【功能 2】用户登录

    public Result<User> login(String account, String password) {
        try {
            LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(User::getAccount, account);
            User user = userMapper.selectOne(wrapper);

            if (user == null) {
                return Result.fail("登录失败：账号不存在！");
            }

            // 密码比对
            String inputPwdEncoded = MD5Util.encrypt(password);
            if (!inputPwdEncoded.equals(user.getPassword())) {
                return Result.fail("登录失败，输入密码错误，请重新输入！");
            }

            return Result.success(user, "登录成功！欢迎 " + user.getAccount());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("登录失败：" + e.getMessage());
        }
    }


      //【功能 3】更新用户信息

    public  Result<Boolean> updateUser(User user) {
        try {
            int rows = userMapper.updateById(user);

            if (rows > 0) {
                return Result.success(true, "更新成功！");
            } else {
                return Result.fail("更新失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("更新失败：" + e.getMessage());
        }
    }


     //【功能 4】修改密码

    public Result<Boolean> updatePassword(String account, String oldPassword, String newPassword) {
        try {
            // 步骤 1：根据账号查询用户
            LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(User::getAccount, account);
            User user = userMapper.selectOne(wrapper);

            // 用户不存在
            if (user == null) {
                return Result.fail("用户不存在！");
            }

            // 步骤 2：验证旧密码
            String oldPwdEncoded = MD5Util.encrypt(oldPassword);
            if (!oldPwdEncoded.equals(user.getPassword())) {
                return Result.fail("旧密码错误！");
            }

            // 步骤 3：加密新密码并更新
            user.setPassword(MD5Util.encrypt(newPassword));
            int rows = userMapper.updateById(user);

            if (rows > 0) {
                return Result.success(true, "密码修改成功！");
            } else {
                return Result.fail("密码修改失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("密码修改失败：" + e.getMessage());
        }
    }
}
*/


package org.example.service;

import org.example.entity.User;
import org.example.mapper.UserMapper;
import org.example.common.Constants;
import org.example.common.MD5Util;
import org.example.common.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public Result<User> login(String account, String password) {
        try {
            LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(User::getAccount, account);
            User user = userMapper.selectOne(wrapper);

            if (user == null) {
                return Result.fail("登录失败：账号不存在！");
            }

            String inputPwdEncoded = MD5Util.encrypt(password);
            if (!inputPwdEncoded.equals(user.getPassword())) {
                return Result.fail("登录失败，输入密码错误，请重新输入！");
            }

            return Result.success(user, "登录成功！欢迎 " + user.getAccount());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("登录失败：" + e.getMessage());
        }
    }

    public Result<Boolean> register(String account, String password, int role) {
        try {
            if (role == Constants.ROLE_STUDENT) {
                if (!account.matches(Constants.REGEX_STUDENT)) {
                    return Result.fail("账号格式错误");
                }
            } else if (role == Constants.ROLE_ADMIN) {
                if (!account.matches(Constants.REGEX_ADMIN)) {
                    return Result.fail("账号格式错误");
                }
            }

            LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(User::getAccount, account);
            User existUser = userMapper.selectOne(wrapper);

            if (existUser != null) {
                return Result.fail("注册失败：该账号已被注册！");
            }

            User newUser = new User();
            newUser.setAccount(account);
            newUser.setPassword(MD5Util.encrypt(password));
            newUser.setRole(role);

            int rows = userMapper.insert(newUser);

            if (rows > 0) {
                return Result.success(true, "注册成功！");
            } else {
                return Result.fail("注册失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("注册失败：" + e.getMessage());
        }
    }

    public Result<Boolean> updateUser(User user) {
        try {
            int rows = userMapper.updateById(user);
            if (rows > 0) {
                return Result.success(true, "更新成功！");
            } else {
                return Result.fail("更新失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("更新失败：" + e.getMessage());
        }
    }
}