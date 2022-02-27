package org.winter.core.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.winter.common.constant.HttpStatus;
import org.winter.common.rest.Result;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 没有权限异常
     */
    @ExceptionHandler(AccessDeniedException.class)
    public Result handleAuthorizationException(AccessDeniedException e) {
        return Result.error(HttpStatus.FORBIDDEN, "没有权限，请联系管理员授权!");
    }
}
