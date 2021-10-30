package com.yuechuan.dao;

import com.github.pagehelper.Page;
import com.yuechuan.pojo.CheckGroup;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CheckGroupDao {
    /**
     * 按条件查询检查组数据
     * @return
     */
    @Select("<script>" +
            "select " +
            "id,code,name,helpCode,sex,remark,attention " +
            "from " +
            "t_checkgroup " +
            "<if test='value != null and value.length > 0'> " +
            "where code like concat('%',#{value},'%') or name like concat('%',#{value},'%') or helpCode like concat('%',#{value},'%')" +
            "</if>" +
            "</script>")
    public Page<CheckGroup> findByCondition(@Param("value") String value);

    /**
     * 添加单条检查组
     * @param checkGroup
     */
    @Insert("insert into " +
            "t_checkgroup(id,code,name,helpCode,sex,remark,attention) " +
            "values(#{id},#{code},#{name},#{helpCode},#{sex},#{remark},#{attention})")
    @SelectKey(statement = "select last_insert_id()",keyColumn = "id",keyProperty = "id",resultType = Integer.class,before=false)
    public void add(CheckGroup checkGroup);

    /**
     * 根据id查询检查组
     * @param checkGroupId
     * @return
     */
    @Select("select " +
            "id,code,name,helpCode,sex,remark,attention " +
            "from " +
            "t_checkgroup " +
            "where " +
            "id = #{checkGroupId}")
    public CheckGroup findCheckGroupById(Integer checkGroupId);


    @Update("update " +
            "t_checkgroup " +
            "set " +
            "code = #{code},name = #{name},helpCode = #{helpCode},sex = #{sex},remark = #{remark},attention = #{attention} " +
            "where " +
            "id = #{id}")
    public void update(CheckGroup checkGroup);

    @Delete("delete from " +
            "t_checkgroup " +
            "where " +
            "id = #{checkGroupId}")
    public void delete(Integer checkGroupId);

    @Select("select " +
            "id,code,name,helpCode,sex,remark,attention " +
            "from " +
            "t_checkgroup ")
    public List<CheckGroup> findAll();

}
