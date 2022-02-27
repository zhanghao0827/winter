package org.winter.common.exception;

public class LoginFailureException extends RuntimeException {

    public LoginFailureException() {
        super("登录失败！");
    }

}
