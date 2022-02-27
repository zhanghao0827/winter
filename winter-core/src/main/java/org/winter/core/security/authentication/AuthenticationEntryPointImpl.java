package org.winter.core.security.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.winter.common.constant.HttpStatus;
import org.winter.common.rest.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        String msg = "请求访问：" + request.getRequestURI() + "，认证失败，无法访问系统资源!!!";
        String json = new ObjectMapper().writeValueAsString(Result.error(HttpStatus.UNAUTHORIZED, msg));
        out.write(json);
        out.flush();
        out.close();
    }
}
