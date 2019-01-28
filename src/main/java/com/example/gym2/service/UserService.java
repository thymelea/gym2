package com.example.gym2.service;

import com.example.gym2.dao.UserMapper;
import com.example.gym2.model.User;
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
@Service("userService")
public class UserService {
    @Autowired
    private UserMapper userMapper;

    private static final org.slf4j.Logger logger= LoggerFactory.getLogger(UserService.class);

    private static final ObjectMapper mapper=new ObjectMapper();
    public String findUser(String json){
        List<User> users=null;
        String jsonlist="{\"msg\":\"false\",\"code\":\"9\"}";
        if(StringUtils.isEmpty(json)) {
            logger.info("查所有用户");
            return jsonlist;
        }
        logger.info("查-->"+json);
        Map<String,Object> data = Util.paramToMap(json);
        String token=(String)data.get("token");
        String userId=(String)data.get("userId");
        String adminId=(String)data.get("adminId");
        if(!StringUtils.isEmpty(userId)&&StringUtils.isEmpty(adminId)){
            User user= userMapper.selectUserIdAndToken(userId,token);
            if(user==null){
                return jsonlist;
            }else {
                users.add(user);
            }
        }else if(StringUtils.isEmpty(userId)&&!StringUtils.isEmpty(adminId)){
            users=userMapper.selectUserByAdmin(adminId,null);
        }else {
            users=userMapper.selectUserByAdmin(null, token);
        }
        try {
            if(users!=null){
                jsonlist = mapper.writeValueAsString(users);
            }
        } catch (JsonProcessingException e) {
            logger.error("对象转json异常");
            e.printStackTrace();
        }
        return jsonlist;
    }

    /**
     *
     * @param json
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public String addUser(String json){
        if(StringUtils.isEmpty(json)){
            return "";
        }else{
            logger.info("填用户"+json);
            Map<String,Object> data = Util.paramToMap(json);
            String adminid=(String)data.get("token");
            String name=(String)data.get("name");
            if(StringUtils.isEmpty(name)){
                return "{\"msg\":\"请输入用户名\",\"code\":\"8\"}";
            }
            if(userMapper.selectUserByNameAndAdminId(name,adminid)!=null){
                logger.error("添加失败，用户已存在");
                return "{\"msg\":\"用户已存在,更换用户名\",\"code\":\"6\"}";
            }
            String sex=(String)data.get("sex");
            String yearold=(String)data.get("yearold");
            String weight=(String)data.get("weight");
            String height=(String)data.get("height");
            String phone=(String)data.get("phone");
            User user=new User();

            user.setFid(UUID.randomUUID().toString());
            user.setAdminId(adminid);
            user.setName(name);
            user.setSex(sex);
            user.setYearold(yearold);
            user.setWeight(weight);
            user.setHeight(height);
            user.setPhone(phone);
            user.setState("0");
            userMapper.insert(user);
            return "{\"msg\":\"恭喜你成功\",\"code\":\"0\"}";
        }
    }
//
//    public User findUserByNameAndAdminId(String name,String adminId){
//        return userReposityInter.findByNameAndAdminId(name,adminId);
//    }

    /**
     *
     * @param json
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public String delete(String  json){
        if(StringUtils.isEmpty(json)){
            return "";
        }else{
            logger.info("删除"+json);
            Map<String,Object> data = Util.paramToMap(json);
            String userId=(String)data.get("userId");
            userMapper.deleteByPrimaryKey(userId);
            return "{\"msg\":\"success\",\"code\":\"0\"}";

        }
    }
}
