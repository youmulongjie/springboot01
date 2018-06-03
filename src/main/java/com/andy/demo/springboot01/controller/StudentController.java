/**
 * Copyright (C), 2015-2018
 * FileName: StudentController
 * Author:   59458
 * Date:     2018/5/25 23:02
 * Description: StudentEntity Controller
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.andy.demo.springboot01.controller;

import com.andy.demo.springboot01.bean.CodeEnum;
import com.andy.demo.springboot01.bean.ResultBean;
import com.andy.demo.springboot01.entity.StudentEntity;
import com.andy.demo.springboot01.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈StudentEntity Controller〉
 *
 * @author 59458
 * @create 2018/5/25
 * @since 1.0.0
 */
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * 获取所有对象 Get请求
     *
     * @return 所有的对象
     */
    @GetMapping(value = "/student/list")
    public ResultBean list() throws Exception {
        return ResultBean.success(studentService.list());
    }

    /**
     * 获取单个对象 Get请求
     *
     * @param id 查询ID
     * @return 根据ID查询到的对象
     */
    @GetMapping(value = "/student/{id}")
    public ResultBean findById(@PathVariable("id") Long id) throws Exception {
        return ResultBean.success(studentService.getOneById(id));
    }

    /**
     * 新增 Post请求
     *
     * @param studentEntity 新增的StudentEntity实体对象，开启验证
     * @param bindingResult BindingResult对象，记录验证的结果
     * @return 保存后的StudentEntity实体对象
     */
    @PostMapping(value = "/student")
    public ResultBean save(@Valid StudentEntity studentEntity, BindingResult bindingResult) throws Exception {
        ResultBean messageBean;
        if (bindingResult.hasErrors()) {
            StringBuffer sb = new StringBuffer(10);
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error : errors) {
                sb.append(error.getDefaultMessage() + " ");
            }
            messageBean = ResultBean.failure(CodeEnum.VALID_NOT_PASS.getCode(), sb.toString());
        } else {
            messageBean = ResultBean.success(studentService.save(studentEntity));
        }
        return messageBean;
    }

    /**
     * 更新 Put请求
     *
     * @param id      更新的 Student对象ID
     * @param student 更新的 Student对象，开启验证
     * @return 更新后 Student对象
     */
    @PutMapping(value = "/student/{id}")
    public ResultBean updateById(@PathVariable("id") Long id, @Valid StudentEntity student, BindingResult bindingResult) throws Exception {
        ResultBean messageBean;
        if (bindingResult.hasErrors()) {
            StringBuffer sb = new StringBuffer(10);
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error : errors) {
                sb.append(error.getDefaultMessage() + " ");
            }
            messageBean = ResultBean.failure(CodeEnum.VALID_NOT_PASS.getCode(), sb.toString());
        } else {
            messageBean = ResultBean.success(studentService.updateById(id, student));
        }
        return messageBean;
    }

    /**
     * 删除 Delete请求
     *
     * @param id 要删除的ID
     */
    @DeleteMapping(value = "/student/{id}")
    public ResultBean deleteById(@PathVariable("id") Long id) throws Exception {
        try {
            studentService.deleteById(id);
            return ResultBean.success();
        } catch (Exception e) {
            return ResultBean.failure(CodeEnum.UNKNOWN.getCode(), "删除失败：" + e.getMessage());
        }
    }

    /**
     * 按年龄查找（大于等于） Get请求
     *
     * @param age 年龄
     * @return 年龄大于等于参数的 Student对象集合
     */
    @GetMapping(value = "/student/age/gt/{age}")
    public ResultBean findByAgeGreaterThanEqual(@PathVariable("age") int age) throws Exception {
        return ResultBean.success(studentService.findByAgeGreaterThanEqual(age));
    }

    /**
     * 按兴趣爱好（模糊）查找 Get请求
     *
     * @param hobby 兴趣爱好 关键字
     * @return 兴趣爱好包含参数的 Student对象集合
     */
    @GetMapping(value = "/student/hobby/{hobby}")
    public ResultBean findByHobbyLike(@PathVariable("hobby") String hobby) throws Exception {
        // jpa like 传参时需自己加 %
        return ResultBean.success(studentService.findByHobbyLike("%" + hobby + "%"));
    }

    /**
     * 按性别查找 Get请求
     *
     * @param sex 性别
     * @return 性别等于参数的 Student对象集合
     */
    @GetMapping(value = "/student/sex/{sex}")
    public ResultBean findBySex(@PathVariable("sex") String sex) throws Exception {
        return ResultBean.success(studentService.findBySex(sex));
    }
}