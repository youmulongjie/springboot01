/**
 * Copyright (C), 2015-2018
 * FileName: ExceptionHandle
 * Author:   59458
 * Date:     2018/5/29 22:49
 * Description: 控制层增强--异常处理类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.andy.demo.springboot01.controlleradvice;

import com.andy.demo.springboot01.bean.CodeEnum;
import com.andy.demo.springboot01.bean.JsonResultBean;
import com.andy.demo.springboot01.exception.MyException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 〈一句话功能简述〉<br>
 * 〈控制层增强--异常处理类〉
 *
 * @author 59458
 * @create 2018/5/29
 * @since 1.0.0
 */
@Slf4j
@ControllerAdvice
public class ExceptionHandle {

    /**
     * 捕获异常信息
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public JsonResultBean handleException(Exception e) {
        JsonResultBean resultBean;
        if (e instanceof MyException) {
            MyException myException = (MyException) e;
            resultBean = JsonResultBean.failure(myException.getCode(), myException.getMessage());
        } else {
            // 记录 未知异常日志
            log.error(CodeEnum.UNKNOWN.getDesc(), e);
            resultBean = JsonResultBean.failure(CodeEnum.UNKNOWN.getCode(), e.getMessage());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String res = objectMapper.writeValueAsString(resultBean);
            log.info("Response ：{}", res);
        } catch (JsonProcessingException e1) {
            log.error("jackson beanToJson 异常！", e1);
        }
        log.info("***********捕获异常，结束*********");
        return resultBean;
    }
}