package org.winter.common.exception;

public class CaptchaExpireException extends RuntimeException {

    public CaptchaExpireException() {
        super("验证码已过期！");
    }
}
