package com.yuechuan.dao;

import com.github.pagehelper.Page;
import com.yuechuan.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

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
}
