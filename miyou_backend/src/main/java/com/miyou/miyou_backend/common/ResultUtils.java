package com.miyou.miyou_backend.common;

/**
 * 返回工具类
 *
 * @author LINGLAN
 */
public class ResultUtils {

    /**
     * 成功
     *
     * @param data 数据
     * @param <T> 同用数据类型
     * @return 状态码,数据,信息
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(0, data, "ok");
    }

    /**
     * 失败
     *
     * @param errorCode 状态码
     * @return 状态码
     */
    public static BaseResponse error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);
    }

    /**
     * 失败
     *
     * @param code 状态码
     * @param message 信息
     * @return 状态码,数据,信息
     */
    public static BaseResponse error(int code, String message) {
        return new BaseResponse(code, null, message);
    }

    /**
     * 失败
     *
     * @param errorCode 状态码
     * @return 状态码,数据,信息
     */
    public static BaseResponse error(ErrorCode errorCode, String message) {
        return new BaseResponse(errorCode.getCode(), null, message);
    }
}
