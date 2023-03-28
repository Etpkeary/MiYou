package com.miyou.miyou_backend.utlis;

import com.miyou.miyou_backend.common.ErrorCode;
import com.miyou.miyou_backend.exception.BusinessException;

/**
 * 抛异常工具类
 *
 * @author LINGLAN
 */
public class ThrowUtils {

    /**
     * 条件成立则抛异常
     *
     * @param condition 条件
     * @param runtimeException 运行超时异常
     */
    public static void throwIf(boolean condition, RuntimeException runtimeException) {
        if (condition) {
            throw runtimeException;
        }
    }

    /**
     * 条件成立则抛异常
     *
     * @param condition 条件
     * @param errorCode 状态码
     */
    public static void throwIf(boolean condition, ErrorCode errorCode) {
        throwIf(condition, new BusinessException(errorCode));
    }

    /**
     * 条件成立则抛异常
     *
     * @param condition 条件
     * @param errorCode 状态码
     * @param message 信息
     */
    public static void throwIf(boolean condition, ErrorCode errorCode, String message) {
        throwIf(condition, new BusinessException(errorCode, message));
    }
}
