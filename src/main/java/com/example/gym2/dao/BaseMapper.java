package com.example.gym2.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * desc mapper基准接口，所有mapper都需要继承此接口
 */
@Mapper
public interface BaseMapper<T> {

    /**
     * 根据id删除记录
     *
     * @param fid id
     * @return 返回影响的行数
     */
    int deleteByPrimaryKey(String fid);

    /**
     * 新增记录
     *
     * @param record 记录数据
     * @return 返回新增id
     */
    int insert(T record);

    /**
     * 新增记录（过滤空值）
     *
     * @param record 记录数据
     * @return 返回id
     */
    int insertSelective(T record);

    /**
     * 根据id查询数据
     *
     * @param fid 记录id
     * @return 返回详情
     */
    T selectByPrimaryKey(String fid);

    /**
     * 更新记录（过滤空值）
     *
     * @param record 待更新记录数据
     * @return 返回影响的记录行数
     */
    int updateByPrimaryKeySelective(T record);

    /**
     * 更新记录
     *
     * @param record 待更新的数
     * @return 返回影响的行数
     */
    int updateByPrimaryKey(T record);


}
