/**
 * Copyright (C), 2015-2018
 * FileName: JsonResultBean
 * Author:   59458
 * Date:     2018/5/27 16:28
 * Description: 接口返回封装Bean
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.andy.demo.springboot01.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * 〈一句话功能简述〉<br>
 * 〈接口返回封装Bean的最外层对象〉
 *
 * @author 59458
 * @create 2018/5/27
 * @since 1.0.0
 */
@Data
@Builder // 依赖lombok插件，的构造器注解
@ApiModel(description = "返回响应数据")
public class JsonResultBean {
    /**
     * 返回状态成功，或者失败（失败结果保存在code， msg）
     */
    @ApiModelProperty(value = "是否成功")
    private boolean status;

    /**
     * 返回码
     */
    @ApiModelProperty("错误编码（status = false 时）")
    private Integer code;

    /**
     * 返回提示信息
     */
    @ApiModelProperty("错误信息（status = false 时）")
    private String msg;

    /**
     * 对象信息
     */
    @ApiModelProperty(value = "返回对象")
    private Object data;

    /**
     * 返回正确，带数据
     *
     * @param data 数据
     * @return
     */
    public static JsonResultBean success(Object data) {
        return JsonResultBean.builder().status(true).code(CodeEnum.SUCCESS.getCode()).data(data).build();
    }

    /**
     * 返回正确，无数据
     *
     * @return
     */
    public static JsonResultBean success() {
        return success(null);
    }

    /**
     * 返回错误，带错误信息
     *
     * @param msg 错误信息
     * @return
     */
    public static JsonResultBean failure(int code, String msg) {
        return JsonResultBean.builder().status(false).code(code).msg(msg).build();
    }
}