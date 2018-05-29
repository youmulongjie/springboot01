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

import com.andy.demo.springboot01.bean.ResultBean;
import com.andy.demo.springboot01.entity.StudentEntity;
import com.andy.demo.springboot01.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
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
    public List<StudentEntity> list() {
        return studentService.list();
    }

    /**
     * 获取单个对象 Get请求
     *
     * @param id 查询ID
     * @return 根据ID查询到的对象
     */
    @GetMapping(value = "/student/{id}")
    public ResultBean findById(@PathVariable("id") Long id) {
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
    public ResultBean save(@Valid StudentEntity studentEntity, BindingResult bindingResult) {
        ResultBean messageBean;
        if (bindingResult.hasErrors()) {
            messageBean = ResultBean.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            messageBean = ResultBean.success(studentService.save(studentEntity));
        }
        return messageBean;
    }

    /**
     * 更新 Put请求
     *
     * @param id      更新的 Student对象ID
     * @param student 更新的 Student对象
     * @return 更新后 Student对象
     */
    @PutMapping(value = "/student/{id}")
    public StudentEntity updateById(@PathVariable("id") Long id, @Valid StudentEntity student) {
        return studentService.updateById(id, student);
    }

    /**
     * 删除 Delete请求
     *
     * @param id 要删除的ID
     */
    @DeleteMapping(value = "/student/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        studentService.deleteById(id);
    }

    /**
     * 按年龄查找（大于等于） Get请求
     *
     * @param age 年龄
     * @return 年龄大于等于参数的 Student对象集合
     */
    @GetMapping(value = "/student/age/gt/{age}")
    public List<StudentEntity> findByAgeGreaterThanEqual(@PathVariable("age") int age) {
        return studentService.findByAgeGreaterThanEqual(age);
    }

    /**
     * 按兴趣爱好（模糊）查找 Get请求
     *
     * @param hobby 兴趣爱好 关键字
     * @return 兴趣爱好包含参数的 Student对象集合
     */
    @GetMapping(value = "/student/hobby/{hobby}")
    public List<StudentEntity> findByHobbyLike(@PathVariable("hobby") String hobby) {
        return studentService.findByHobbyLike(hobby);
    }

    /**
     * 按性别查找 Get请求
     *
     * @param sex 性别
     * @return 性别等于参数的 Student对象集合
     */
    @GetMapping(value = "/student/sex/{sex}")
    public List<StudentEntity> findBySex(@PathVariable("sex") String sex) {
        return studentService.findBySex(sex);
    }
}