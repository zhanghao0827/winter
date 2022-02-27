package org.winter.common.rest;

import lombok.Getter;
import lombok.Setter;
import org.winter.common.constant.Constants;
import org.winter.common.constant.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class Result {

    private int code;

    private String message;

    private Object data;

    public Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result success() {
        return new Result(HttpStatus.SUCCESS, Constants.SUCCESS, null);
    }

    public static Result success(String message) {
        return new Result(HttpStatus.SUCCESS, message, null);
    }

    public static Result success(Object object) {
        return new Result(HttpStatus.SUCCESS, Constants.SUCCESS, object);
    }

    public static Result success(String message, Object object) {
        return new Result(HttpStatus.SUCCESS, message, object);
    }

    public static Result error() {
        return new Result(HttpStatus.ERROR, Constants.ERROR, null);
    }

    public static Result error(Object object) {
        return new Result(HttpStatus.ERROR, Constants.ERROR, object);
    }

    public static Result error(String message) {
        return new Result(HttpStatus.ERROR, message, null);
    }

    public static Result error(int code, String message) {
        return new Result(code, message, null);
    }

    public static Map<String, Object> getMap() {
        return new HashMap<>();
    }

}
