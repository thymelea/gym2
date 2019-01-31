package com.example.gym2.interceptor;

import com.example.gym2.dao.RedisDao;
import com.example.gym2.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author: zhangbo
 * @Date: 2019/1/25/025 14:15
 * @Version 1.0
 */
@Slf4j
@Component
public class UrlInterceptor implements HandlerInterceptor {
    @Autowired
    RedisDao redisDao;

    Logger logger = LoggerFactory.getLogger(UrlInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //获取session
        String id = "";
        Map<String, String[]> tokenMap = request.getParameterMap();
        for (String key : tokenMap.keySet()) {
            if("token".equals(key)){
                String[] values = tokenMap.get(key);
                for (String value : values) {
                    id = value;
                }
            }
        }
        if(redisDao.exists(id)){
            String name = redisDao.get(id);
            redisDao.setExpire(id, name, true, 60L);
            return true;
        }else{
            response.sendRedirect(request.getContextPath() + "/err");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub

    }
}
