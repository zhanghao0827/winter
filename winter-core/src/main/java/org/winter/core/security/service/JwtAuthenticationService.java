package org.winter.core.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.winter.common.exception.LoginFailureException;
import org.winter.common.exception.PasswordNotMatchException;
import org.winter.common.exception.UserDisabledException;
import org.winter.core.security.authentication.JwtTokenProvider;
import org.winter.core.security.config.bean.LoginBody;
import org.winter.core.security.model.SysUserDetails;

@Component
public class JwtAuthenticationService {

    // 此处注入的bean在SpringConfig中产生, 如果不在其中声明则注入AuthenticationManager报错
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    /**
     * 登录认证验证JWT令牌
     */
    public String login(LoginBody loginBody) {
        //用户验证
        Authentication authentication;
        try {
            // 进行身份验证(校验密码)
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginBody.getUsername(), loginBody.getPassword()));
        } catch (Exception e) {
            // 不能直接抛 BadCredentialsException 会报403
            if (e instanceof BadCredentialsException) {
                throw new PasswordNotMatchException();
            } else if (e instanceof DisabledException) {
                throw new UserDisabledException();
            } else {
                throw new LoginFailureException();
            }
        }
        SysUserDetails userDetails = (SysUserDetails) authentication.getPrincipal();
        // 生成token，并返回
        return jwtTokenProvider.generateToken(userDetails);
    }



}
