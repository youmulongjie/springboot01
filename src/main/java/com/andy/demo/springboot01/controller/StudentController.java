/**
 * Copyright (C), 2015-2018
 * FileName: StudentController
 * Author:   59458
 * Date:     2018/5/25 23:02
 * Description: Student Controller
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.andy.demo.springboot01.controller;

import com.andy.demo.springboot01.bean.Student;
import com.andy.demo.springboot01.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈Student Controller〉
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
    public List<Student> list() {
        return studentService.list();
    }

    // 获取单个对象 Get请求
    @GetMapping(value = "/student/{id}")
    public Student findById(@PathVariable("id") Long id) {
        return studentService.getOneById(id);
    }

    // 新增 Post请求
    @PostMapping(value = "/student")
    public Student save(Student student) {
        System.out.println(student.toString());
        return studentService.save(student);
    }

    // 更新 Put请求
    @PutMapping(value = "/student/{id}")
    public Student updateById(@PathVariable("id") Long id, Student student) {
        return studentService.updateById(id, student);
    }

    // 删除 Delete请求
    @DeleteMapping(value = "/student/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        studentService.deleteById(id);
    }

}