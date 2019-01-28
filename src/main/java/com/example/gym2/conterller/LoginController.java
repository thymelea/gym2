package com.example.gym2.conterller;

import com.example.gym2.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
/**
 * @Author: zhangbo
 * @Date: 2019/1/24/024 16:23
 * @Version 1.0
 */
@RestController
public class LoginController {
    @Autowired
    private AdminService adminService;

    @PostMapping (value = {"/login"}, produces = "application/json;charset=utf-8")
    public String login(HttpServletResponse response, @RequestBody String json){
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
        return adminService.login(json);
    }
    @PostMapping(value = {"/register"}, produces = "application/json;charset=utf-8")
    public String registerFun(HttpServletResponse response, @RequestBody String json){
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
        return adminService.register(json);
    }

    @PostMapping(value = {"/err"},produces = "application/json;charset=utf-8")
    public String errFun(HttpServletResponse response){
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
        return "{\"msg\":\"操作失败\",\"code\":\"9\"}";
    }

    @PostMapping(value = {"/logOut"}, produces = "application/json;charset=utf-8")
    public String outFun(HttpServletResponse response, @RequestBody String json){
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
            return adminService.loginOut(json);
    }
}
