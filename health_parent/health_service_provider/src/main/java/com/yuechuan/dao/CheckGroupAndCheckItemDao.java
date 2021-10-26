package com.yuechuan.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CheckGroupAndCheckItemDao {

    /**
     * 根据检查组id查询检查项id集
     * @param id
     * @return
     */
    @Select("select " +
            "checkitem_id " +
            "from " +
            "t_checkgroup_checkitem " +
            "where " +
            "checkgroup_id = #{id}")
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    /**
     * 添加检查项和检查组中间表
     * @param idMap
     */
    @Insert("insert into " +
            "t_checkgroup_checkitem(checkgroup_id,checkitem_id) " +
            "values(#{checkGroupId},#{checkItemId})")
    public void add(Map<String,Integer> idMap);

    @Delete("delete from " +
            "t_checkgroup_checkitem " +
            "where checkgroup_id = #{checkGroupId}")
    public void deleteByCheckGroupId(Integer checkGroupId);
}
