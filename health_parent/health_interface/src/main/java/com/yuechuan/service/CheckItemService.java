package com.yuechuan.service;


import com.github.pagehelper.PageInfo;
import com.yuechuan.entity.PageResult;
import com.yuechuan.entity.QueryPageBean;
import com.yuechuan.pojo.CheckItem;


public interface CheckItemService {
    /**
     * 检查项添加
     * @param checkItem
     */
    public void add(CheckItem checkItem);

    /**
     * 检查项分页查询
     * @param queryPageBean
     * @return
     */
    public PageResult findPage(QueryPageBean queryPageBean);

    /**
     * 根据id查询检查项
     * @param id
     * @return
     */
    public CheckItem findById(Integer id);

    /**
     * 更新检查项
     * @param checkItem
     */
    public void update(CheckItem checkItem);

    /**
     * 根据id删除检查项
     * @param id
     */
    public void deleteById(Integer id);
}
