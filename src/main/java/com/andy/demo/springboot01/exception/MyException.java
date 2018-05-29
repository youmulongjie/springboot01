/**
 * Copyright (C), 2015-2018
 * FileName: MyException
 * Author:   59458
 * Date:     2018/5/29 22:41
 * Description: 自定义异常
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.andy.demo.springboot01.exception;

/**
 * 〈一句话功能简述〉<br>
 * 〈自定义异常〉
 *
 * @author 59458
 * @create 2018/5/29
 * @since 1.0.0
 */
public class MyException extends RuntimeException {
    /**
     * 异常Code
     */
    public Integer code;

    public MyException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}