package org.winter.core.security.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.winter.common.constant.Constants;
import org.winter.common.rest.Result;
import org.winter.common.util.RedisUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        String token = jwtTokenProvider.resolveToken(request);
        String id = jwtTokenProvider.getId(token);
        redisUtils.delete(Constants.ONLINE_ID_PREFIX + id);
        out.write(new ObjectMapper().writeValueAsString(Result.success()));
        out.flush();
        out.close();
    }
}
