package com.example.gym2.dto;

import com.example.gym2.model.Course;
import lombok.Data;

/**
 * @Author: zhangbo
 * @Date: 2019/1/28/028 10:11
 * @Version 1.0
 */
@Data
public class CourseDTO extends Course {
    private String uuId;
    private Object dateCollege;
    private Object dateUser;
    private String collid;
    private String relid;
}
