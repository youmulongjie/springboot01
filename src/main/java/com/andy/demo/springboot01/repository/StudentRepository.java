/**
 * Copyright (C), 2015-2018
 * FileName: StudentRepository
 * Author:   59458
 * Date:     2018/5/25 22:46
 * Description: Student Dao层接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.andy.demo.springboot01.repository;

import com.andy.demo.springboot01.bean.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 〈一句话功能简述〉<br>
 * 〈Student Dao层接口〉
 *
 * @author 59458
 * @create 2018/5/25
 * @since 1.0.0
 */
public interface StudentRepository extends JpaRepository<Student, Long> {

}
