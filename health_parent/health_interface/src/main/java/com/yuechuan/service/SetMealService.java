package com.yuechuan.service;

import com.yuechuan.entity.PageResult;
import com.yuechuan.entity.QueryPageBean;
import com.yuechuan.pojo.CheckGroup;
import com.yuechuan.pojo.Setmeal;

import java.util.List;

public interface SetMealService {

    /**
     * 添加套餐
     * @param checkGroupIds
     * @param setmeal
     */
    public void add(Integer[] checkGroupIds, Setmeal setmeal);

    /**
     * 添加套餐与检查组中间表
     * @param checkGroupIds
     * @param setmealId
     */
    public void addSetmealAndCheckGroup(Integer[] checkGroupIds,Integer setmealId);

    /**
     *查询套餐分页
     * @return
     */
    public PageResult findPage(QueryPageBean queryPageBean);
}
