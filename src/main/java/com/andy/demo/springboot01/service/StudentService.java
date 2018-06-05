/**
 * Copyright (C), 2015-2018
 * FileName: StudentService
 * Author:   59458
 * Date:     2018/5/25 22:48
 * Description: StudentEntity Service
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.andy.demo.springboot01.service;

import com.andy.demo.springboot01.bean.CodeEnum;
import com.andy.demo.springboot01.entity.StudentEntity;
import com.andy.demo.springboot01.exception.MyException;
import com.andy.demo.springboot01.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈StudentEntity Service〉
 *
 * @author 59458
 * @create 2018/5/25
 * @since 1.0.0
 */
@Service
@Transactional  // 开启事务控制
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    /**
     * 保存
     *
     * @param student
     * @return 返回新增的实体
     */
    public StudentEntity save(StudentEntity student) throws Exception {
        Long id = student.getId();
        if (null != id) {
            if (studentRepository.findById(id).isPresent()) {
                throw new MyException(CodeEnum.ALREADY_EXISTS.getCode()
                        , "student with id = " + id + " already exists!");
            }
        }
        return studentRepository.save(student);
    }

    /**
     * 查询列表
     *
     * @return 实体集合
     */
    public List<StudentEntity> list() throws Exception {
        return studentRepository.findAll();
    }

    /**
     * 根据 ID查询
     *
     * @param id 查询ID
     * @return 实体对象
     */
    public StudentEntity getOneById(Long id) throws Exception {
        // 如果存在返回查询的对象，不存在则抛出异常
        if (studentRepository.findById(id).isPresent()) {
            return studentRepository.findById(id).get();
        }
        throw new MyException(CodeEnum.NOT_FOUND.getCode()
                , "not find student with id = " + id);
    }

    /**
     * 更新
     *
     * @param id      更新ID
     * @param student 更新的对象
     * @return 更新后的对象
     */
    public StudentEntity updateById(Long id, StudentEntity student) throws Exception {
        // 如果存在返回查询的对象，不存在则抛出异常
        if (studentRepository.findById(id).isPresent()) {
            student.setId(id);
            return studentRepository.save(student);
        }
        throw new MyException(CodeEnum.NOT_FOUND.getCode()
                , "not find student with id = " + id);
    }

    /**
     * 删除
     *
     * @param id 删除ID
     */
    public void deleteById(Long id) throws Exception {
        studentRepository.deleteById(id);
    }

    /**
     * 按年龄查找
     *
     * @param age 年龄
     * @return
     */
    public List<StudentEntity> findByAgeGreaterThanEqual(int age) throws Exception {
        return studentRepository.findByAgeGreaterThanEqual(age);
    }

    /**
     * 按 兴趣爱好 查找
     *
     * @param hobby 兴趣爱好
     * @return
     */
    public List<StudentEntity> findByHobbyLike(String hobby) throws Exception {
        return studentRepository.findByHobbyLike("%" + hobby + "%");
    }

    /**
     * 按 性别 查找
     *
     * @param sex 性别
     * @return
     */
    public List<StudentEntity> findBySex(String sex) throws Exception {
        return studentRepository.findBySex(sex);
    }
}