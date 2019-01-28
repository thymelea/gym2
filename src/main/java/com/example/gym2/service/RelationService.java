package com.example.gym2.service;

import com.example.gym2.dao.RelationMapper;
import com.example.gym2.model.Relation;
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
@Service("relationService")
public class RelationService {
    @Autowired
    private RelationMapper relationMapper;
    private static final org.slf4j.Logger logger= LoggerFactory.getLogger(RelationService.class);
//
    @Transactional
    public String save(String json){
        if(StringUtils.isEmpty(json)){
        return "";
    }else{
        logger.info(json);
        Map<String,Object> data = Util.paramToMap(json);
        String userId=(String)data.get("userId");
        String courseId=(String)data.get("courseId");
        String startdate=(String)data.get("startdate");
        String enddate=(String)data.get("enddate");
        String collegeid=(String)data.get("collegeid");
        String relid=(String)data.get("relid");
        Relation relation;
        if(StringUtils.isEmpty(relid)) {
            relation=new Relation();
            relid= UUID.randomUUID().toString();
        }else {
            relation=relationMapper.selectByPrimaryKey(relid);
        }
        relation.setFid(relid);
        if(!StringUtils.isEmpty(courseId)){
            relation.setCourseId(courseId);
        }
        if(!StringUtils.isEmpty(userId)){
            relation.setUserId(userId);
        }
        if(!StringUtils.isEmpty(startdate)){
            relation.setStartDate(startdate);
        }
        if(!StringUtils.isEmpty(enddate)){
            relation.setEndDate(enddate);
        }
        if(!StringUtils.isEmpty(collegeid)){
            relation.setCollid(collegeid);
        }
        relationMapper.insert(relation);
        return "{\"msg\":\"success\",\"code\":\"0\"}";

    }
    }

    /**
     *
     * @param json
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public String deleteCourseIdIn(String json){
        if(StringUtils.isEmpty(json)){
            return "{\"msg\":\"false\",\"code\":\"9\"}";
        }else{
            logger.info("删除约课"+json);
            Map<String,Object> data = Util.paramToMap(json);
            String relid=(String)data.get("relid");
            relationMapper.deleteByPrimaryKey(relid);
            return "{\"msg\":\"success\",\"code\":\"0\"}";

        }
    }
}
