/**
 * Copyright (C), 2015-2018
 * FileName: ExceptionControllerAdvice
 * Author:   59458
 * Date:     2018/5/27 21:29
 * Description: ControllerAdvice
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.andy.demo.springboot01.controller.advice;

import com.andy.demo.springboot01.bean.MessageBean;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * 〈一句话功能简述〉<br> 
 * 〈ControllerAdvice〉
 *
 * @author 59458
 * @create 2018/5/27
 * @since 1.0.0
 */
@ControllerAdvice
public class ExceptionControllerAdvice {
    public MessageBean bindExceptionHandler(Exception  ex){
        System.out.println(ex.getMessage());
        return null;
    }
}