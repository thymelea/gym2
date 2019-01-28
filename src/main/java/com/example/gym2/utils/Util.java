package com.example.gym2.utils;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhangbo
 * @Date: 2019/1/24/024 14:18
 * @Version 1.0
 */
public class Util {
    public static final Map<String,Boolean> tokenMap=new HashMap();

    public static Map<String,Object> paramToMap(String param){
        if(StringUtils.isEmpty(param)){
            return null;
        }else {
            Map<String,Object> map=new HashMap<>(16);
            String keyAndValues[]= param.split("&");
            for(String keyAndValue:keyAndValues){
                String paramValue[]=keyAndValue.split("=");
                String key=paramValue[0]+"";
                String value="";
                if(paramValue.length>1) {
                    value = paramValue[1] + "";
                }
                map.put(key,value);
            }
            return map;
        }
    }
}
