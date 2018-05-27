/**
 * Copyright (C), 2015-2018
 * FileName: MessageBean
 * Author:   59458
 * Date:     2018/5/27 16:28
 * Description: 接口返回封装Bean
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.andy.demo.springboot01.bean;

/**
 * 〈一句话功能简述〉<br>
 * 〈接口返回封装Bean的最外层对象〉
 *
 * @author 59458
 * @create 2018/5/27
 * @since 1.0.0
 */
public class MessageBean {
    /**
     * 返回状态成功，或者失败（失败结果保存在errorMsg）
     */
    public boolean status;

    /**
     * 错误提示信息
     */
    public String errorMsg;

    /**
     * 对象信息
     */
    public Object data;

    public boolean isStatus() {
        return status;
    }

    public MessageBean setStatus(boolean status) {
        this.status = status;
        return this;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public MessageBean setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public MessageBean setData(Object data) {
        this.data = data;
        return this;
    }

    /**
     * 返回争取，带数据
     *
     * @param data 数据
     * @return
     */
    public static MessageBean success(Object data) {
        MessageBean messageBean = new MessageBean();
        messageBean.setStatus(true).setData(data);
        return messageBean;
    }

    /**
     * 返回正确，无数据
     *
     * @return
     */
    public static MessageBean success() {
        return success(null);
    }

    /**
     * 返回错误
     *
     * @param errorMsg 错误信息
     * @return
     */
    public static MessageBean failure(String errorMsg) {
        MessageBean messageBean = new MessageBean();
        return messageBean.setStatus(false).setErrorMsg(errorMsg);
    }
}