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

import com.andy.demo.springboot01.bean.MessageBean;
import com.andy.demo.springboot01.entity.StudentEntity;
import com.andy.demo.springboot01.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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

    // 获取所有对象 Get请求
    @GetMapping(value = "/student/list")
    public List<StudentEntity> list() {
        return studentService.list();
    }

    // 获取单个对象 Get请求
    @GetMapping(value = "/student/{id}")
    public StudentEntity findById(@PathVariable("id") Long id) {
        return studentService.getOneById(id);
    }

    // 新增 Post请求
    @PostMapping(value = "/student")
    public MessageBean save(@Validated StudentEntity student, BindingResult bindingResult) {
        MessageBean messageBean = null;
        System.out.println(student.toString());
        if (bindingResult.hasErrors()) {
            messageBean = MessageBean.failure(bindingResult.getFieldError().getDefaultMessage());
        }
        messageBean = MessageBean.success(studentService.save(student));
        return messageBean;
    }

    // 更新 Put请求
    @PutMapping(value = "/student/{id}")
    public StudentEntity updateById(@PathVariable("id") Long id, @Valid StudentEntity student) {
        return studentService.updateById(id, student);
    }

    // 删除 Delete请求
    @DeleteMapping(value = "/student/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        studentService.deleteById(id);
    }

    // 按年龄查找 Get请求
    @GetMapping(value = "/student/age/gt/{age}")
    public List<StudentEntity> findByAgeGreaterThanEqual(@PathVariable("age") int age) {
        return studentService.findByAgeGreaterThanEqual(age);
    }

    // 按兴趣爱好查找 Get请求
    @GetMapping(value = "/student/hobby/{hobby}")
    public List<StudentEntity> findByHobbyLike(@PathVariable("hobby") String hobby) {
        return studentService.findByHobbyLike(hobby);
    }

    // 按性别查找 Get请求
    @GetMapping(value = "/student/sex/{sex}")
    public List<StudentEntity> findBySex(@PathVariable("sex") String sex) {
        return studentService.findBySex(sex);
    }
}