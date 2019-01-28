package com.example.gym2.service;

import com.example.gym2.dao.AdminMapper;
import com.example.gym2.dao.RedisDao;
import com.example.gym2.model.Admin;
import com.example.gym2.utils.Util;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.UUID;
/**
 * @Author: zhangbo
 * @Date: 2019/1/24/024 16:23
 * @Version 1.0
 */
@Service("adminService")
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private RedisDao redisDao;
    private static final org.slf4j.Logger logger=LoggerFactory.getLogger(AdminService.class);
    public String login(String json){
        if(!StringUtils.isEmpty(json)){
            logger.info("登陆"+json);
            Map<String,Object> data = Util.paramToMap(json);
            String username=(String)data.get("account");
            String password=(String)data.get("password");
            if(!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)){
                Admin admin=adminMapper.selectAdminByNameAndPassword(username, password);
                if(admin!=null&&admin.getPassword().equals(password)){
                    redisDao.setExpire(admin.getFid(),username,true,60L);
                    return "{\"msg\":\"登陆成功\",\"code\":\"0\",\"token\":\""+admin.getFid()+"\"}";
                }else {
                    return "{\"msg\":\"密码或账号不对\",\"code\":\"9\"}";
                }
            }
            else {
                return "{\"msg\":\"密码或账号为空\",\"code\":\"9\"}";
            }
        }else {
            return "{\"msg\":\"请求参数为空\",\"code\":\"9\"}";
        }
    }
    public String loginOut(String json){
        Map<String,Object> data = Util.paramToMap(json);
        String token=(String)data.get("token");
        if(!StringUtils.isEmpty(token) ){
            if(redisDao.exists(token)){
                redisDao.remove(token);
            }
            return "{\"msg\":\"操作成功\",\"code\":\"0\"}";
        }else {
            return "{\"msg\":\"请求参数为空\",\"code\":\"9\"}";
        }
    }
    @Transactional(rollbackFor = Exception.class)
    public String register(String date){
        if(StringUtils.isEmpty(date)){
            return "{\"msg\":\"注册失败\",\"code\":\"9\"}";
        }
        logger.info("注册"+date);
        Map<String,Object> data = Util.paramToMap(date);
        Admin admin = new Admin();
        String userName = (String)data.get("account");
        if(adminMapper.selectAdminByNameAndPassword(userName, null) != null){
        logger.error("注册失败，用户已存在");
            return "{\"msg\":\"注册失败，用户已存在\",\"code\":\"6\"}";
        }
        String passWord = (String)data.get("password");
        admin.setFid(UUID.randomUUID().toString());
        admin.setUsername(userName);
        admin.setPassword(passWord);
        adminMapper.insert(admin);
        return "{\"msg\":\"注册成功\",\"code\":\"0\"}";
    }

}
