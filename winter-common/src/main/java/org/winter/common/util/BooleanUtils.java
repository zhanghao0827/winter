package org.winter.common.util;

public class BooleanUtils {

    public static Boolean isStringToBoolean(String s) {
        return "true".equals(s) || "false".equals(s);
    }
}
