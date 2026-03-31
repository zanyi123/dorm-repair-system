package org.example.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.example.util.JwtUtil;  // ← 根据实际路径调整

public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 放行 OPTIONS 预检请求（跨域用）
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        String uri = request.getRequestURI();

        // 放行登录、注册接口
        if (uri.contains("/api/auth/login") || uri.contains("/api/auth/register")) {
            return true;
        }

        // ⚠️ 放行管理员接口
        if (uri.contains("/api/admin")) {
            return true;
        }

        // ⚠️ 放行学生接口
        if (uri.contains("/api/student")) {
            return true;
        }

        // 验证 Token
        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty()) {
            response.setStatus(401);
            response.getWriter().write("Token 缺失");
            return false;
        }

        // 去掉 "Bearer " 前缀
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        try {
            // 验证 Token 是否有效
            JwtUtil.verify(token);
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            response.getWriter().write("Token 无效或已过期");
            return false;
        }
    }
}