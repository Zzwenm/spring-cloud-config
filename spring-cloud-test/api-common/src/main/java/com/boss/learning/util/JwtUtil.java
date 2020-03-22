package com.boss.learning.util;

import com.boss.learning.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt工具类
 *
 * @author Zzwen
 * @date 2020/3/18 19:36
 */
public class JwtUtil {
    /**
     * 密钥
     */
    private static final String KEY = "spring cloud";

    /**
     * token有效期，两小时
     */
    private static final Long EFFECTIVE_TIME = 2 * 60 * 60 * 1000L;

    /**
     * 创建jwt
     * @param user
     * @return
     * @throws Exception
     */
    public static String createJwt(User user){

        //获取当前时间，用设置失效时间
        long nowMillis = System.currentTimeMillis();
        long exp = nowMillis + EFFECTIVE_TIME;

        //创建payload的私有声明（根据特定的业务需要添加，如果要拿这个做验证，一般是需要和jwt的接收方提前沟通好验证方式的）
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("userId", user.getUserId());
        claims.put("username", user.getUsername());

        //下面就是在为payload添加各种标准声明和私有声明了
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setId(user.getUserId().toString())
                //签名时间
                .setIssuedAt(new Date())
                .setSubject(user.getUsername())
                //签名的算法
                .signWith(SignatureAlgorithm.HS256, KEY)
                //失效时间
                .setExpiration(new Date(exp));
        //生成并返回token
        return builder.compact();
    }

    /**
     * 解密token
     *
     * @param token
     * @return 存放在claim中的数据
     * @throws Exception
     */
    public static Claims parseJwt(String token) {
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
    }
}
