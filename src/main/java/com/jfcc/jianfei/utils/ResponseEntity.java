package com.jfcc.jianfei.utils;


import java.io.Serializable;

/**
 * 通用 API 返回对象
 *
 * @param <T> 返回数据类型
 */
public class ResponseEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 状态码（如 200、400、500） */
    private int code;

    /** 返回消息 */
    private String message;

    /** 返回数据 */
    private T data;

    /** 是否成功 */
    private boolean success;

    // --- 构造函数 ---
    public ResponseEntity() {}

    public ResponseEntity(int code, String message, T data, boolean success) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.success = success;
    }

    // --- 静态工厂方法 ---

    public static <T> ResponseEntity<T> success() {
        return new ResponseEntity<>(200, "操作成功", null, true);
    }

    public static <T> ResponseEntity<T> success(T data) {
        return new ResponseEntity<>(200, "操作成功", data, true);
    }

    public static <T> ResponseEntity<T> success(String message, T data) {
        return new ResponseEntity<>(200, message, data, true);
    }

    public static <T> ResponseEntity<T> failure(String message) {
        return new ResponseEntity<>(500, message, null, false);
    }

    public static <T> ResponseEntity<T> failure(int code, String message) {
        return new ResponseEntity<>(code, message, null, false);
    }

    // --- Getter & Setter ---
    public int getCode() {
        return code;
    }

    public ResponseEntity<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseEntity<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseEntity<T> setData(T data) {
        this.data = data;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public ResponseEntity<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }
}
