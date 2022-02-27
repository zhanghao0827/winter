package org.winter.common.exception;

public class PasswordNotMatchException extends RuntimeException {

    public PasswordNotMatchException() {
        super("用户名密码错误！");
    }

    public PasswordNotMatchException(String message) {
        super(message);
    }
}
