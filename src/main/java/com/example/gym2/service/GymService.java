package com.example.gym2.service;

import com.example.gym2.conterller.LoginController;
import com.example.gym2.dao.RedisDao;
import com.example.gym2.model.Admin;
import com.example.gym2.utils.Util;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @Author: zhangbo
 * @Date: 2019/1/24/024 16:58
 * @Version 1.0
 */
@Service
public class GymService {


}
