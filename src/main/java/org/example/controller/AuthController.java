package org.example.controller;

import org.example.dto.LoginRequest;
import org.example.entity.User;
import org.example.service.UserService;
import org.example.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.example.dto.LoginRequest;
import org.example.dto.RegisterRequest;
import org.example.util.JwtUtil;
import org.example.dto.LoginResponse;  // ← ⚠️ 必须有这行！


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result<LoginResponse> login(@RequestBody LoginRequest request) {
        Result<User> result = userService.login(request.getAccount(), request.getPassword());

        if (result.isSuccess()) {
            User user = result.getData();
            String token = JwtUtil.createToken(user.getId(), user.getAccount());

            // 创建返回对象（不包含密码等敏感信息）
            LoginResponse response = new LoginResponse();
            response.setId(user.getId());
            response.setAccount(user.getAccount());
            response.setRole(user.getRole());
            response.setToken(token);

            return Result.success(response, "登录成功！");
        }

        return Result.fail(result.getMessage());
    }

    @PostMapping("/register")
    public Result<Boolean> register(@RequestBody RegisterRequest request) {
        return userService.register(
                request.getAccount(),
                request.getPassword(),
                request.getRole()
        );
    }
}