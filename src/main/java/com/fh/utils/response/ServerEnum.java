package com.fh.utils.response;

public enum ServerEnum {
    SUCCESS(200, "成功"),
    ERROR(500, "失败"),
    ZHANGHAO_SUCCESS(220, "注册成功"),
    PRODUCT_CART_KEY(80, "cart_"),
    LOGIN_SUCCESS(200, "登录成功"),
    LOGIN_USER_NULL(1, "此号未注册"),
    ZHANGHAO_LOCK(3300, "账号冻结"),
    PASSWORD_ERROR(100, "密码错误"),
    YANZHENGMA_CHAOSHI(102, "验证码失效");

    private ServerEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;


    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
