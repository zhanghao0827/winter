package org.winter.common.exception;

public class UserDisabledException extends RuntimeException {

    public UserDisabledException() {
        super("账户被锁定！");
    }
}
