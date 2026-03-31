package org.example.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;

public class JwtUtil {

    // ⭐ 个性点 1：密钥可以改成你的名字拼音 + 生日
    private static final String SECRET = "zanyi-2004-secret-key";

    // ⭐ 个性点 2：过期时间可自定义（7 天、30 天都行）
    private static final long EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000;

    public static String createToken(Integer userId, String account) {
        return JWT.create()
                .withClaim("userId", userId)
                .withClaim("account", account)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .sign(Algorithm.HMAC256(SECRET));
    }

    public static DecodedJWT verify(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        return verifier.verify(token);
    }

    public static Integer getUserId(String token) {
        return verify(token).getClaim("userId").asInt();
    }
}