package com.example.gym2.dao;

import com.example.gym2.dto.CourseDTO;
import com.example.gym2.model.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: zhangbo
 * @Date: 2019/1/24/024 14:10
 * @Version 1.0
 */
@Mapper
public interface CourseMapper extends BaseMapper<Course>  {
    List<CourseDTO> selectCourse(String state, String userId, String adminid);
}
