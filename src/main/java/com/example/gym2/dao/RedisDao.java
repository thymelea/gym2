package com.example.gym2.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zhangbo
 * @Date: 2019/1/24/024 14:20
 * @Version 1.0
 */
@Repository
public class RedisDao {

    @Autowired
    private RedisTemplate<String, ?> redisTemplate;
    /**
     * @param key
     * @param values
     * @param expire 是否设置超时
     * @param second 超时时间（秒）
     * @return
     * 保存有序list
     */
    public Long setSortList(String key, Set<ZSetOperations.TypedTuple<Object>> values, Boolean expire, Long second) {
        JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
        redisTemplate.setValueSerializer(jdkSerializationRedisSerializer);
        ZSetOperations<String, Object> stringZSetOperations = (ZSetOperations<String, Object>) redisTemplate.opsForZSet();
        if (redisTemplate.hasKey(key)) {
            return 0L;
        }
        Long add = null;
        try{
            add = stringZSetOperations.add(key, values);
        }catch (Exception e){
            e.printStackTrace();
        }

        if (expire) {
            redisTemplate.expire(key, second, TimeUnit.SECONDS);
        }
        return add;
    }
    public boolean exists(final String key) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                return connection.exists(serializer.serialize(key));
            }
        });
        return result;
    }
    /**
     * 获取有序set1
     *
     * @param key
     * @return
     */
    public Set<Object> getSortList(String key) {
        JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
        redisTemplate.setValueSerializer(jdkSerializationRedisSerializer);
        Long size = redisTemplate.opsForZSet().size(key);
        return (Set<Object>) redisTemplate.opsForZSet().range(key, 0, size);
    }

    public void remove(String key) {
        redisTemplate.delete(key);
    }

    public boolean setExpire(final String key, final String value, Boolean expire, Long second) {
        boolean result = redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            connection.set(serializer.serialize(key), serializer.serialize(value));
            if (expire) {
                redisTemplate.expire(key, second, TimeUnit.SECONDS);
            }
            return true;
        });
        return result;
    }
    public boolean set(final String key, final String value) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                connection.set(serializer.serialize(key), serializer.serialize(value));
                return true;
            }
        });
        return result;
    }
    public String get(final String key) {
        String result = redisTemplate.execute(new RedisCallback<String>() {

            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] value = connection.get(serializer.serialize(key));
                return serializer.deserialize(value);
            }
        });
        return result;
    }
}
