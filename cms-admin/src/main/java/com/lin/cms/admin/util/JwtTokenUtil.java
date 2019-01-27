package com.lin.cms.admin.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhouyajun on 2019/1/22
 */

@Component
public class JwtTokenUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
    private static final String CLAIMS_KEY_USERNAME = "sub";  // 声明数据
    private static final String CLAIMS_KEY_CREATED = "created";
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 根据负载生成token
     *
     * @param claims
     * @return
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.ES512, secret).compact();
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(secret)
                    .parseClaimsJws(token).getBody();
        } catch (Exception e) {
            LOGGER.info("WT格式验证失败:{}", token);
        }
        return claims;
    }

    public String getUserNameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            LOGGER.info("从token获取用户名称失败:{}", token);
            username = null;
        }
        return username;
    }

    /**
     * 判断token是否过期
     *
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpiratedDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 从token获取过期时间
     *
     * @param token
     * @return
     */
    private Date getExpiratedDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 根绝用户信息生成token
     *
     * @param userName
     * @return
     */
    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIMS_KEY_USERNAME, userName);
        claims.put(CLAIMS_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 判断token是否可以刷新
     *
     * @param token
     * @return
     */
    public boolean canRefresh(String token) {
        return !isTokenExpired(token); // isTokenExpired(token)判断token是否过期，过期的话返回true，是否刷新就为false
    }

    /**
     * 刷新token
     *
     * @param expiredToken 过期token
     * @return
     */
    public String refreshToken(String expiredToken) {
        Claims claims = getClaimsFromToken(expiredToken);
        claims.put(CLAIMS_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 验证token是否还有效
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        return userDetails.getUsername().equals(getUserNameFromToken(token)) && !isTokenExpired(token);
    }
}
