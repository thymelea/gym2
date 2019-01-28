package com.example.gym2.dao;

import com.example.gym2.model.College;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: zhangbo
 * @Date: 2019/1/24/024 14:10
 * @Version 1.0
 */
@Mapper
public interface CollegeMapper extends BaseMapper<College> {
    List<College> selectByFidAndCNameLikeAndAddressLikeAndAId(@Param("id") String id, @Param("name") String name, @Param("address") String address, @Param("adID") String adID);

    List<College> selectByFidIn(@Param("ids") List<String> ids);

}
