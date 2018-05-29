package com.andy.demo.springboot01.bean;

/**
 * Code 枚举类
 */
public enum CodeEnum {
    SUCCESS(1, "成功"), UNKNOWN(-1, "未知错误"), NOT_FOUND(11, "未发现错误"), VALID_NOT_PASS(21, "验证未通过");
    /**
     * 状态码
     */
    public Integer code;
    /**
     * 状态描述
     */
    public String desc;

    CodeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
