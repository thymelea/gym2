package com.example.gym2.service;

import com.example.gym2.dao.CollegeMapper;
import com.example.gym2.model.College;
import com.example.gym2.utils.Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.UUID;
/**
 * @Author: zhangbo
 * @Date: 2019/1/24/024 16:23
 * @Version 1.0
 */
@Service("collegeService")
public class CollegeService {
@Autowired
private CollegeMapper collegeMapper;

    private static final org.slf4j.Logger logger= LoggerFactory.getLogger(CollegeService.class);
    public String findCollege(String id,String name,String address,String adID){
        String jsonlist="{\"msg\":\"111\",\"code\":\"9\"}";
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<College> college=collegeMapper.selectByFidAndCNameLikeAndAddressLikeAndAId(id,name,address,adID);
            jsonlist = mapper.writeValueAsString(college);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.error("对象转json异常");
        }
        return jsonlist;
    }
    @Transactional(rollbackFor = Exception.class)
    public String save(String json){
        if(StringUtils.isEmpty(json)){
            return "";
        }else{
            logger.info(json);
            Map<String,Object> data = Util.paramToMap(json);
            String adminid=(String)data.get("token");
            String name=(String)data.get("name");
            String address=(String)data.get("address");
            College college=new College();
            college.setFid(UUID.randomUUID().toString());
            college.setAdminid(adminid);
            college.setCollegename(name);
            college.setAddress(address);
            collegeMapper.insert(college);
            return "{\"msg\":\"success\",\"code\":\"0\"}";
        }
    }

    public List<College> findCollIdin(List<String> ids){
            return collegeMapper.selectByFidIn(ids);
    }

}
