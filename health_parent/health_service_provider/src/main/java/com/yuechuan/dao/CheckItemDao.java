package com.yuechuan.dao;

import com.yuechuan.entity.QueryPageBean;
import com.yuechuan.pojo.CheckItem;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckItemDao {
    /**
     * 添加单条检查项
     * @param checkItem
     */
    @Insert("insert into " +
            "t_checkitem(code,name,sex,age,price,type,attention,remark) " +
            "values(#{code},#{name},#{sex},#{age},#{price},#{type},#{attention},#{remark})")
    public void add(CheckItem checkItem);

    @Select("<script>" +
                "select " +
                "id,code,name,sex,age,price,type,attention,remark " +
                "from " +
                "t_checkitem " +
                "<if test='queryString != null and queryString.length > 0 '> " +
                "where code like concat('%',#{queryString},'%') or name like concat('%',#{queryString},'%')" +
                " </if>" +
            "</script>")
    public List<CheckItem> findPage(@Param("queryString") String queryString);



    @Update("<script>" +
            "update " +
            "t_checkitem " +
            "<set> " +
                "<if test='code != null'> code = #{code}</if>, " +
                "<if test='name != null'> name = #{name}</if>, " +
                "<if test='sex != null'> sex = #{sex}</if>, " +
                "<if test='age != null'> age = #{age}</if>, " +
                "<if test='price != null'> price = #{price}</if>, " +
                "<if test='type != null'> type = #{type}</if>, " +
                "<if test='attention != null'> attention = #{attention}</if>, " +
                "<if test='remark != null'> remark = #{remark}</if> " +
            "</set> " +
            "where id = #{id}" +
            "</script>")
    public void update(CheckItem checkItem);

    @Select("select " +
            "id,code,name,sex,age,price,type,attention,remark " +
            "from " +
            "t_checkItem " +
            "where " +
            "id = #{id}")
    public CheckItem findById(Integer id);

    @Delete("delete from t_checkitem where id = #{id}")
    public void deleteById(Integer id);

}
