package com.yuechuan.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface SetmealAndCheckgroupDao {

    /**
     * 添加单条依赖关系
     * @param idsMap
     */
    @Insert("insert into " +
            "t_setmeal_checkgroup(setmeal_id,checkgroup_id) " +
            "values(#{setmealId},#{checkGroupId})")
    public void add(Map idsMap);


    //public List<>

}
