/**
 * Copyright (C), 2015-2018
 * FileName: StudentRepository
 * Author:   59458
 * Date:     2018/5/25 22:46
 * Description: StudentEntity Dao层接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.andy.demo.springboot01.repository;

import com.andy.demo.springboot01.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈StudentEntity Dao层接口〉
 *
 * @author 59458
 * @create 2018/5/25
 * @since 1.0.0
 */
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    /**
     * 查找年龄大于等于 参数的 Student集合
     *
     * @param age 年龄
     * @return
     */
    public List<StudentEntity> findByAgeGreaterThanEqual(int age);

    /**
     * 根据兴趣爱好 查找Student集合
     *
     * @param hobby 兴趣爱好
     * @return
     */
    public List<StudentEntity> findByHobbyLike(String hobby);

    /**
     * 根据性别查找 Student集合
     * @param sex 性别
     * @return
     */
    @Query(value = "select * from student where sex = ?", nativeQuery = true)
    public List<StudentEntity> findBySex(String sex);
}
