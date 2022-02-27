package org.winter.common.exception;

public class CaptchaNotMatchException extends RuntimeException {

    public CaptchaNotMatchException() {
        super("验证码错误！");
    }
}
