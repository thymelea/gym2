package com.example.gym2.dao;

import com.example.gym2.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @Author: zhangbo
 * @Date: 2019/1/24/024 14:11
 * @Version 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    User selectUserIdAndToken(@Param("userId")String userId, @Param("token")String token);
    List<User> selectUserByAdmin(@Param("userId")String userId,  @Param("token")String token);

    List<User> selectUIdIn(@Param("userId")List<String> userId);

    User selectUserByNameAndAdminId(@Param("userName")String userName,@Param("adminId")String adminId);
}
