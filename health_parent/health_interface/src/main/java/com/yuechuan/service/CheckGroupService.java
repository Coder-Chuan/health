package com.yuechuan.service;

import com.yuechuan.entity.PageResult;
import com.yuechuan.entity.QueryPageBean;
import com.yuechuan.pojo.CheckGroup;
import com.yuechuan.pojo.CheckItem;

import java.util.List;

public interface CheckGroupService {
    /**
     * 获取分页检查组数据
     * @param queryPageBean
     * @return
     */
    public PageResult findPage(QueryPageBean queryPageBean);

    /**
     * 添加检查组
     * @param checkItemIds
     * @param checkGroup
     */
    public void add(Integer[] checkItemIds,CheckGroup checkGroup);

    /**
     * 根据检查组id查询检查项id集
     * @return
     */
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    /**
     * 根据id查询检查组
     * @param id
     * @return
     */
    public CheckGroup findCheckGroupById(Integer id);

    /**
     * 更新检查组
     * @param checkItemIds
     * @param checkGroup
     */
    public void edit(Integer[] checkItemIds,CheckGroup checkGroup);

    /**
     * 删除检查组
     * @param checkGroupId
     */
    public void delete(Integer checkGroupId);

}
