/**
 * Copyright (C), 2015-2018
 * FileName: StudentEntity
 * Author:   59458
 * Date:     2018/5/25 21:27
 * Description: 学生 实体类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.andy.demo.springboot01.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br>
 * 〈学生 实体类〉
 *
 * @author 59458
 * @create 2018/5/25
 * @since 1.0.0
 */
@Entity
@Data
public class StudentEntity implements Serializable {
    /**
     * ID 自增主键
     */
    @Id
    @GeneratedValue
    public Long id;
    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能不空！")
    public String name;
    /**
     * 性别
     */
    public String sex;

    /**
     * 年龄
     */
    @Min(value = 18, message = "年龄不能小于18岁！")
    public Integer age;
    /**
     * 兴趣爱好
     */
    @Size(max = 10, message = "兴趣爱好不能超过10个字符")
    public String hobby;
}