/**
 * Copyright (C), 2015-2018
 * FileName: ResultBean
 * Author:   59458
 * Date:     2018/5/27 16:28
 * Description: 接口返回封装Bean
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.andy.demo.springboot01.bean;

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
public class ResultBean {
    /**
     * 返回状态成功，或者失败（失败结果保存在code， msg）
     */
    private boolean status;

    /**
     * 返回码
     */
    private Integer code;

    /**
     * 返回提示信息
     */
    private String msg;

    /**
     * 对象信息
     */
    private Object data;

    /**
     * 返回正确，带数据
     *
     * @param data 数据
     * @return
     */
    public static ResultBean success(Object data) {
        return ResultBean.builder().status(true).code(CodeEnum.SUCCESS.getCode()).data(data).build();
    }

    /**
     * 返回正确，无数据
     *
     * @return
     */
    public static ResultBean success() {
        return success(null);
    }

    /**
     * 返回错误，带错误信息
     *
     * @param msg 错误信息
     * @return
     */
    public static ResultBean failure(int code, String msg) {
        return ResultBean.builder().status(false).code(code).msg(msg).build();
    }
}