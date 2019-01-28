package com.lin.cms.admin.dto;

import com.lin.cms.admin.util.JsonUtil;
import org.springframework.validation.BindingResult;

/**
 * Created by zhouyajun on 2019/1/23
 */
public class CommonResult {
    public static final int SUCCESS = 200;
    public static final int FAILED = 500;
    public static final int VALIDATE_FAILED = 404;
    public static final int UNAUTHORIZED = 401;
    public static final int FORBIDDEN = 403;
    private int code;
    private String message;
    private Object data;

    public CommonResult success(Object data) {
        this.code = SUCCESS;
        this.message = "操作成功";
        this.data = data;
        return this;
    }

    public CommonResult forbidden(String data) {
        this.code = FORBIDDEN;
        this.message = "没有相关权限";
        this.data = data;
        return this;
    }

    public CommonResult unauthorized(String data) {
        this.code = UNAUTHORIZED;
        this.message = "认证未通过";
        this.data = data;
        return this;
    }

    public CommonResult validateFailed(String data) {
        this.code = VALIDATE_FAILED;
        this.message = "暂未登录或token已经过期";
        this.data = data;
        return this;
    }

    public CommonResult validateFailed(BindingResult result) {
        validateFailed(result.getFieldError().getDefaultMessage());
        return this;
    }

    public CommonResult failed() {
        this.code = FAILED;
        this.message = "操作失败";
        return this;
    }

    @Override
    public String toString() {
        return JsonUtil.objectToJson(this);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
