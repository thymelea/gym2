package com.example.gym2.service;

import com.example.gym2.dao.CollegeMapper;
import com.example.gym2.dao.CourseMapper;
import com.example.gym2.dao.UserMapper;
import com.example.gym2.dto.CourseDTO;
import com.example.gym2.model.College;
import com.example.gym2.model.Course;
import com.example.gym2.model.User;
import com.example.gym2.utils.Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;
/**
 * @Author: zhangbo
 * @Date: 2019/1/24/024 16:23
 * @Version 1.0
 */
@Service("courseService")
public class CourseService {

    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CollegeMapper collegeMapper;
    @Autowired
    private UserMapper userMapper;

    private static final org.slf4j.Logger logger= LoggerFactory.getLogger(CourseService.class);
    public String courseList(String json){
        String jsonlist="{\"msg\":\"111\",\"code\":\"9\"}";
    try {
        ObjectMapper mapper = new ObjectMapper();
        String userId="";
        String state="";
        String token="";
        if(!StringUtils.isEmpty(json)){
            logger.info(json);
            Map<String,Object> data = Util.paramToMap(json);
            userId=(String)data.get("userId");
            state=(String)data.get("state");
            token=(String)data.get("token");
        }
        List<CourseDTO> courses=courseMapper.selectCourse(state,userId,token);
        if(!StringUtils.isEmpty(state)){
            List<String> uId=new ArrayList<>();
            List<String> coll=new ArrayList<>();
            for(CourseDTO course:courses){
                uId.add(course.getUuId());
                coll.add(course.getCollid());
            }
            List<College> collegeList=collegeMapper.selectByFidIn(coll);
            List<User> userList=userMapper.selectUIdIn(uId);
            for(CourseDTO course:courses){
                if(collegeList!=null&&collegeList.size()>0) {
                    for (College college : collegeList) {
                        if (course.getCollid().equals(college.getFid())) {
                            course.setDateCollege(college);
                        }
                    }
                }
                if(userList!=null&&userList.size()>0) {
                    for (User user : userList) {
                        if (course.getUuId().equals(user.getFid())) {
                            course.setDateUser(user);
                        }
                    }
                }
            }
        }
        jsonlist = mapper.writeValueAsString(courses);
    } catch (
    JsonProcessingException e) {
        e.printStackTrace();
        logger.error("对象转json异常");
    }
        return jsonlist;
    }
//    public List<Course>getCourseList(Map<String,Object> dataMap){
//        return courseReposityInter.findCoursrByUserId(dataMap);
//    }

    /**
     *
     * @param json
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public String add(String json){
        if(StringUtils.isEmpty(json)){
            return "";
        }else{
            logger.info("填课程"+json);
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Map<String,Object> data = Util.paramToMap(json);
            String adminid=(String)data.get("token");
            String collegeid=(String)data.get("collegeid");
            String cname=(String)data.get("name");
            Course course=new Course();
            course.setFid(UUID.randomUUID().toString());
            course.setAdminid(adminid);
            course.setCollegeId(collegeid);
            course.setName(cname);
            course.setState("0");
            courseMapper.insert(course);
            return "{\"msg\":\"不容易啊！有成功了\",\"code\":\"0\"}";
        }
    }
//

}
