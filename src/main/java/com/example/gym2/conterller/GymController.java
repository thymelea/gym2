package com.example.gym2.conterller;

import com.example.gym2.dao.UserMapper;
import com.example.gym2.service.*;
import com.example.gym2.utils.Util;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author: zhangbo
 * @Date: 2019/1/24/024 16:23
 * @Version 1.0
 */
@RestController
public class GymController {
    @Autowired
    private CollegeService collegeService;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private RelationService relationService;
    /**
     * 查询所有用户或根据用户id查用户或根据教练id查教练的用户
     * @param response
     * @param json
     * @return
     */
    @PostMapping(value = {"/findUser"},   produces = "application/json;charset=utf-8")
    public String getUser(HttpServletResponse response, @RequestBody String json) {
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
        return userService.findUser(json);
    }

    @PostMapping(value = {"/findCourse"},   produces = "application/json;charset=utf-8")
    public String getCourse(HttpServletResponse response, @RequestBody String json) {
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
        return courseService.courseList(json);
    }

    @PostMapping(value = {"/findStore"},   produces = "application/json;charset=utf-8")
    public String getCollege(HttpServletResponse response, @RequestBody String json) {
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
        String storeId="";
        String name="";
        String address="";
        String token="";
        if(!StringUtils.isEmpty(json)){
            Map<String,Object> data = Util.paramToMap(json);
            token=(String)data.get("token");
            //根据门店ID查，可不传
            storeId=(String)data.get("storeId");
            //根据门店名字查，可不传
            name=(String)data.get("name");
            //根据门店地址查，可不传
            address=(String)data.get("address");
        }
        return collegeService.findCollege(storeId,name,address,token);
    }

    /**
     * 添加学员用户
     * @param response

     * @param json
     * @return
     */
    @PostMapping(value = {"/addUser"},   produces = "application/json;charset=utf-8")
    public String addUser(HttpServletResponse response, HttpServletRequest request,@RequestBody String json) {
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
        return userService.addUser(json);
    }

    /**
     * 添加课程
     * @param response
     * @param json
     * @return
     */
    @PostMapping(value = {"/addCourse"},   produces = "application/json;charset=utf-8")
    public String addCourse(HttpServletResponse response, @RequestBody String json) {
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
        return courseService.add(json);
    }

    @PostMapping(value = {"/addStore"},   produces = "application/json;charset=utf-8")
    public String addStore(HttpServletResponse response, @RequestBody String json) {
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
        return collegeService.save(json);
    }

    /**
     * 为用户约课程
     * @param response

     * @param json
     * @return
     */
    @PostMapping(value = {"/delCourseForUser"},   produces = "application/json;charset=utf-8")
    public String delCourseForUser(HttpServletResponse response, @RequestBody String json) {
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
        return relationService.deleteCourseIdIn(json);
    }
    /**
     * 为用户约课程
     * @param response

     * @param json
     * @return
     */
    @PostMapping(value = {"/addCourseForUser"},   produces = "application/json;charset=utf-8")
    public String addCourseForUser(HttpServletResponse response, HttpServletRequest request,@RequestBody String json) {
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
        return relationService.save(json);
    }
    /**
     * 删除用户
     * @param response

     * @param json
     * @return
     */
    @PostMapping(value = {"/deleteUser"},   produces = "application/json;charset=utf-8")
    public String deleteUser(HttpServletResponse response, HttpServletRequest request,@RequestBody String json){
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
        return userService.delete(json);
    }
}
