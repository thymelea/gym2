package com.example.gym2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
/**
 * @Author: zhangbo
 * @Date: 2019/1/24/024 16:23
 * @Version 1.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Course {
    private String fid;
    private String adminid;
    private String collegeId;
    private String name;
    private String startDate;
    private String endDate;
    private String state;

}
