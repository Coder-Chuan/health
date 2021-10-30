package com.yuechuan.dao;

import com.github.pagehelper.Page;
import com.yuechuan.pojo.CheckGroup;
import com.yuechuan.pojo.Setmeal;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SetMealDao {


    /**
     * 添加单条套餐
     * @param setmeal
     */
    @Insert("insert into " +
            "t_setmeal(name,code,helpCode,sex,age,price,remark,attention,img) " +
            "values(#{name},#{code},#{helpCode},#{sex},#{age},#{price},#{remark},#{attention},#{img})")
    @SelectKey(keyColumn = "id",keyProperty = "id",resultType = Integer.class,before = false,statement = "select last_insert_id()")
    public void add(Setmeal setmeal);


    @Select("<script>" +
            "select " +
            "id,name,code,helpCode,sex,age,price,remark,attention,img " +
            "from " +
            "t_setmeal " +
            "<if test='queryString != null and queryString.length > 0'>" +
            "where code like concat('%',#{queryString},'%') or name like concat('%',#{queryString},'%') or helpCode like concat('%',#{queryString},'%') " +
            "</if>" +
            "</script>")
    public Page<Setmeal> findByCondition(@Param("queryString") String queryString);

   /*
    连表查询
   @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "code",property = "code"),
            @Result(column = "helpCode",property = "helpCode"),
            @Result(column = "sex",property = "sex"),
            @Result(column = "age",property = "age"),
            @Result(column = "price",property = "price"),
            @Result(column = "remark",property = "remark"),
            @Result(column = "attention",property = "img"),
            @Result(column="id",property = "checkGroups",javaType = CheckGroup.class,
                    many = @Many(select = "com.yuechuan.dao."))
    })*/
}
