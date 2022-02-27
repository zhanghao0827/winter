package org.winter.core.security.authentication;

import cn.hutool.core.util.IdUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.winter.common.constant.Constants;
import org.winter.common.domain.NetInfo;
import org.winter.common.util.NetUtils;
import org.winter.common.util.RedisUtils;
import org.winter.core.security.config.bean.JwtProperties;
import org.winter.monitor.service.dto.Online;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class JwtTokenProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

    private static final String SUBJECT = "sub";

    public static final String ID = "jti";

    private final JwtProperties jwtProperties;

    @Autowired
    private RedisUtils redisUtils;

    public JwtTokenProvider(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    /**
     * 根据用户信息生成token
     */
    public String generateToken(UserDetails userDetails) {
        String id = IdUtil.fastUUID();
        Map<String, Object> claims = new HashMap<>();
        claims.put(SUBJECT, userDetails.getUsername());
        claims.put(ID, id);
        NetInfo netInfo = NetUtils.getNetInfo();
        Online onlineUserDto = new Online(id, userDetails.getUsername(), netInfo.getIp(), netInfo.getPro() + netInfo.getCity(), NetUtils.getBrowser(), new Date());
        redisUtils.set(Constants.ONLINE_ID_PREFIX + id, onlineUserDto, jwtProperties.getExpiration(), TimeUnit.SECONDS);
        return generateToken(claims);
    }

    /**
     * 根据负载生成JWT的token
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                // 设置负载
                .setClaims(claims)
                // 设置过期时间
                .setExpiration(generateExpirationDate())
                // 设置加密方式及盐
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .compact();
    }

    /**
     * 生成token的过期时间 --currentTimeMillis返回毫秒 expiration单位为秒，注意单位换算
     * 过期时间=当前时间+token配置有效时间
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + jwtProperties.getExpiration() * 1000);
    }

    /**
     * 从token中获取JWT中的负载
     */
    private Claims parseClaims(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(jwtProperties.getSecret())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            LOGGER.info("JWT格式验证失败:{}", token);
        }
        return claims;
    }

    /**
     * 从token中获取登录用户名
     */
    public String getUsername(String token) {
        String username;
        try {
            Claims claims = parseClaims(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 获取token id
     */
    public String getId(String token) {
        String id;
        try {
            Claims claims = parseClaims(token);
            id = claims.getId();
        } catch (Exception e) {
            id = null;
        }
        return id;
    }

    public String resolveToken(HttpServletRequest request) {
        String token = request.getHeader(jwtProperties.getHeader());
        if (token != null && token.startsWith(jwtProperties.getPrefix())) {
            token = token.replace(jwtProperties.getPrefix(), "");
        }
        return token;
    }

    /**
     * 验证token是否还有效
     *
     * @param token       客户端传入的token
     * @param userDetails 从数据库中查询出来的用户信息
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * 判断token是否已经失效
     * 过期时间与当前时间比较
     * 创建token的设置的过期时间 = 当前时间(创建token的时间) + 配置token过期时间
     * 过期时间 = 当前时间 + redis存储token的过期时间
     */
    private boolean isTokenExpired(String token) {
        Date expiration = getExpiration(token);
        return expiration.before(new Date());
    }

    /**
     * 从token中获取过期时间
     */
    private Date getExpiration(String token) {
        long time = redisUtils.getExpire(Constants.ONLINE_ID_PREFIX + getId(token));
        return new Date(System.currentTimeMillis() + time);
    }

}