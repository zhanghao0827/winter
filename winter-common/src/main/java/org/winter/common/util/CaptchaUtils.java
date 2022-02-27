package org.winter.common.util;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.captcha.generator.MathGenerator;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CaptchaUtils {

    public static void generateCaptcha() {
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 45, 4, 4);
        captcha.setGenerator(new MathGenerator(1));
        String code = captcha.getCode();
        ServletUtils.getRequest().getSession().setAttribute("captcha", code);
        HttpServletResponse response = ServletUtils.getResponse();
        //告诉浏览器输出内容为jpeg类型的图片
        response.setContentType("image/jpeg");
        try {
            captcha.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
