package org.winter.core.security.config.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginBody {

    /* 用户名 */
    private String username;

    /* 密码 */
    private String password;

    /* 验证码 */
    private String captcha;

}
