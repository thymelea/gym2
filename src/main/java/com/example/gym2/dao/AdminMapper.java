package com.example.gym2.dao;

import com.example.gym2.model.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: zhangbo
 * @Date: 2019/1/24/024 14:10
 * @Version 1.0
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin>{
    /**
     *
     * @param name
     * @param password
     * @return
     */
    Admin selectAdminByNameAndPassword(@Param("name") String name, @Param("password") String password);

}
