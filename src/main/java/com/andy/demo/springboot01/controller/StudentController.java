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
import com.andy.demo.springboot01.bean.JsonResultBean;
import com.andy.demo.springboot01.entity.StudentEntity;
import com.andy.demo.springboot01.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
@RequestMapping(value = "/student") // controller 根访问目录
@Api("StudentController 相关RESTFul API")
@Slf4j
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * 获取所有对象 Get请求
     *
     * @return 所有的对象
     */
    @ApiOperation(value = "获取所有Student列表信息", notes = "获取所有Student列表信息")
    @GetMapping(value = "/list")
    public JsonResultBean list() throws Exception {
        return JsonResultBean.success(studentService.list());
    }

    /**
     * 获取单个对象 Get请求
     *
     * @param id 查询ID
     * @return 根据ID查询到的对象
     */
    @ApiOperation(value = "获取单个Student信息", notes = "根据学生ID获取单个Student信息")
    @ApiImplicitParam(name = "id", value = "Student ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping(value = "/{id}")
    public JsonResultBean findById(@PathVariable("id") Long id) throws Exception {
        return JsonResultBean.success(studentService.getOneById(id));
    }

    /**
     * 新增 Post请求
     *
     * @param studentEntity 新增的StudentEntity实体对象，开启验证
     * @param bindingResult BindingResult对象，记录验证的结果
     * @return 保存后的StudentEntity实体对象
     */
    @ApiOperation(value = "创建Student", notes = "根据传入的Student实体创建Student")
    @ApiImplicitParam(name = "studentEntity", value = "需保存的Student实体", required = true, dataType = "StudentEntity", paramType = "body")
    @PostMapping
    public JsonResultBean save(@Valid @RequestBody StudentEntity studentEntity, BindingResult bindingResult) throws Exception {
        JsonResultBean messageBean;
        if (bindingResult.hasErrors()) {
            StringBuffer sb = new StringBuffer(10);
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error : errors) {
                sb.append(error.getDefaultMessage() + " ");
            }
            messageBean = JsonResultBean.failure(CodeEnum.VALID_NOT_PASS.getCode(), sb.toString());
        } else {
            messageBean = JsonResultBean.success(studentService.save(studentEntity));
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
    @ApiOperation(value = "更新Student", notes = "根据Student ID更新 Student实体")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "要更新的Student ID", dataType = "Long", required = true, paramType = "path"),
            @ApiImplicitParam(name = "student", dataType = "StudentEntity", value = "更新的Student实体", required = true, paramType = "body")
    })
    @PutMapping(value = "/{id}")
    public JsonResultBean updateById(@PathVariable("id") Long id, @Valid @RequestBody StudentEntity student, BindingResult bindingResult) throws Exception {
        JsonResultBean messageBean;
        if (bindingResult.hasErrors()) {
            StringBuffer sb = new StringBuffer(10);
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error : errors) {
                sb.append(error.getDefaultMessage() + " ");
            }
            messageBean = JsonResultBean.failure(CodeEnum.VALID_NOT_PASS.getCode(), sb.toString());
        } else {
            messageBean = JsonResultBean.success(studentService.updateById(id, student));
        }
        return messageBean;
    }

    /**
     * 删除 Delete请求
     *
     * @param id 要删除的ID
     */
    @ApiOperation(value = "删除Student", notes = "根据Student ID删除")
    @ApiImplicitParam(name = "id", dataType = "Long", value = "要删除的 Student ID", required = true, paramType = "path")
    @DeleteMapping(value = "/{id}")
    public JsonResultBean deleteById(@PathVariable("id") Long id) throws Exception {
        try {
            studentService.deleteById(id);
            return JsonResultBean.success();
        } catch (Exception e) {
            return JsonResultBean.failure(CodeEnum.UNKNOWN.getCode(), "删除失败：" + e.getMessage());
        }
    }

    /**
     * 按年龄查找（大于等于） Get请求
     *
     * @param age 年龄
     * @return 年龄大于等于参数的 Student对象集合
     */
    @ApiOperation(value = "查找年龄大于等于参数的Student列表信息", notes = "根据年龄查找，大于等于参数的Student列表信息")
    @ApiImplicitParam(name = "age", dataType = "Integer", value = "查找的年龄参数", required = true, paramType = "path")
    @GetMapping(value = "/age/gt/{age}")
    public JsonResultBean findByAgeGreaterThanEqual(@PathVariable("age") int age) throws Exception {
        return JsonResultBean.success(studentService.findByAgeGreaterThanEqual(age));
    }

    /**
     * 按兴趣爱好（模糊）查找 Get请求
     *
     * @param hobby 兴趣爱好 关键字
     * @return 兴趣爱好包含参数的 Student对象集合
     */
    @ApiOperation(value = "查找兴趣爱好包含参数的Student列表信息", notes = "根据兴趣爱好模糊查找，包含参数的Student列表信息")
    @ApiImplicitParam(name = "hobby", dataType = "String", value = "查找的兴趣爱好参数（关键字）", required = true, paramType = "path")
    @GetMapping(value = "/hobby/{hobby}")
    public JsonResultBean findByHobbyLike(@PathVariable("hobby") String hobby) throws Exception {
        // jpa like 传参时需自己加 %
        return JsonResultBean.success(studentService.findByHobbyLike("%" + hobby + "%"));
    }

    /**
     * 按性别查找 Get请求
     *
     * @param sex 性别
     * @return 性别等于参数的 Student对象集合
     */
    @ApiOperation(value = "查找性别等于参数的Student列表信息", notes = "根据性别查找，等于参数的Student列表信息")
    @ApiImplicitParam(name = "sex", dataType = "String", value = "查找的性别参数", required = true, paramType = "path")
    @GetMapping(value = "/sex/{sex}")
    public JsonResultBean findBySex(@PathVariable("sex") String sex) throws Exception {
        return JsonResultBean.success(studentService.findBySex(sex));
    }

    /**
     * 分页查询 ，Get请求
     *
     * @param currentPage 当前页数，从0开始
     * @param pageShowNum 每页展示个数
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询Student列表信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", dataType = "Integer", value = "当前页数，从0开始", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageShowNum", dataType = "Integer", value = "每页展示个数", required = true, paramType = "path")
    })
    @GetMapping(value = "/page/{currentPage}/{pageShowNum}")
    public JsonResultBean page(@PathVariable("currentPage") int currentPage, @PathVariable("pageShowNum") int pageShowNum) {
        Page<StudentEntity> page = studentService.page(currentPage, pageShowNum);
        showPageInfo(page);
        return JsonResultBean.success(page);
    }

    private void showPageInfo(Page<StudentEntity> page) {
        log.info("查询总条数：{}", page.getTotalElements());
        log.info("查询总页数：{}", page.getTotalPages());
        log.info("当前页数：{}", page.getNumber() + 1);
        log.info("当前页数集合：{}", page.getContent());
        log.info("当前页数集合数量：", page.getNumberOfElements());
    }

    /**
     * 排序分页查询 ，Get请求
     *
     * @param currentPage 当前页数，从0开始
     * @param pageShowNum 每页展示个数
     * @return
     */
    @ApiOperation(value = "按年龄升序排序、分页查询", notes = "按年龄升序排序分页查询Student列表信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", dataType = "Integer", value = "当前页数，从0开始", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageShowNum", dataType = "Integer", value = "每页展示个数", required = true, paramType = "path")
    })
    @GetMapping(value = "/pageAndSortByAgeAsc/{currentPage}/{pageShowNum}")
    public JsonResultBean pageAndSortByAgeAsc(@PathVariable("currentPage") int currentPage, @PathVariable("pageShowNum") int pageShowNum) {
        Page<StudentEntity> page = studentService.pageAndSortByAgeAsc(currentPage, pageShowNum);
        showPageInfo(page);
        return JsonResultBean.success(page);
    }

    /**
     * 单条件查询后，分页排序（查询年龄大于 age的列表，分页排序），Get请求
     *
     * @param age         年龄
     * @param currentPage 当前页数，从0开始
     * @param pageShowNum 每页展示个数
     * @return
     */
    @ApiOperation(value = "单条件查询后，分页排序（查询年龄大于 age的列表，分页排序）", notes = "单条件查询后，分页排序（查询年龄大于 age的列表，分页排序）Student列表信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "age", dataType = "Integer", value = "查询年龄", required = true, paramType = "query"),
            @ApiImplicitParam(name = "currentPage", dataType = "Integer", value = "当前页数，从0开始", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageShowNum", dataType = "Integer", value = "每页展示个数", required = true, paramType = "path")
    })
    @GetMapping(value = "/pageAndSortByAgeAscWhereAgeGreaterThan/{currentPage}/{pageShowNum}")
    public JsonResultBean pageAndSortByAgeAscWhereAgeGreaterThan(@RequestParam("age") int age, @PathVariable("currentPage") int currentPage, @PathVariable("pageShowNum") int pageShowNum) {
        Page<StudentEntity> page = studentService.pageAndSortByAgeAscWhereAgeGreaterThan(age, currentPage, pageShowNum);
        showPageInfo(page);
        return JsonResultBean.success(page);
    }

    /**
     * 多条件查询后、分页排序（查询 年龄 > age，兴趣爱好 like hobby 的列表，分页排序),POST 请求
     *
     * @param age         年龄
     * @param hobby       兴趣爱好
     * @param currentPage 当前页数，从0开始
     * @param pageShowNum 每页展示个数
     * @return
     */
    @ApiOperation(value = "多条件查询后、分页排序（查询 年龄 > age，兴趣爱好 like hobby 的列表，分页排序）", notes = "多条件查询后、分页排序（查询 年龄 > age，兴趣爱好 like hobby 的列表，分页排序）Student列表信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "age", dataType = "Integer", value = "查询年龄", required = true, paramType = "query"),
            @ApiImplicitParam(name = "hobby", dataType = "String", value = "兴趣爱好", required = true, paramType = "query"),
            @ApiImplicitParam(name = "currentPage", dataType = "Integer", value = "当前页数，从0开始", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageShowNum", dataType = "Integer", value = "每页展示个数", required = true, paramType = "path")
    })
    @PostMapping(value = "/pageAndSortWithConditions/{currentPage}/{pageShowNum}")
    public JsonResultBean pageAndSortWithConditions(@RequestParam("age") int age, @RequestParam("hobby") String hobby, @PathVariable("currentPage") int currentPage, @PathVariable("pageShowNum") int pageShowNum) {
        Page<StudentEntity> page = studentService.pageAndSortWithConditions(age, hobby, currentPage, pageShowNum);
        showPageInfo(page);
        return JsonResultBean.success(page);
    }
}