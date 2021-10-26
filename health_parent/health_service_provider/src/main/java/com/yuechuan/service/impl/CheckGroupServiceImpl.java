package com.yuechuan.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yuechuan.dao.CheckGroupAndCheckItemDao;
import com.yuechuan.dao.CheckGroupDao;
import com.yuechuan.dao.CheckItemDao;
import com.yuechuan.entity.PageResult;
import com.yuechuan.entity.QueryPageBean;
import com.yuechuan.pojo.CheckGroup;
import com.yuechuan.pojo.CheckItem;
import com.yuechuan.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service(interfaceClass = CheckGroupService.class)
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupDao checkGroupDao;

    @Autowired
    private CheckGroupAndCheckItemDao checkGroupAndCheckItemDao;

    /**
     * 获取分页检查组数据
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();

        PageHelper.startPage(currentPage,pageSize);
        Page<CheckGroup> page = checkGroupDao.findByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    /**
     * 添加检查组
     * @param checkItemIds
     * @param checkGroup
     */
    @Transactional(readOnly = false,propagation = Propagation.REQUIRED)
    @Override
    public void add(Integer[] checkItemIds, CheckGroup checkGroup) {
        checkGroupDao.add(checkGroup);
        Integer checkGroupId = checkGroup.getId();
        this.addCheckGroupAndCheckItem(checkItemIds,checkGroupId);
    }

    /**
     * 根据检查组id查询检查项id集
     * @return
     */
    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
        return checkGroupAndCheckItemDao.findCheckItemIdsByCheckGroupId(id);
    }

    /**
     * 根据id查询检查组
     * @param id
     * @return
     */
    @Override
    public CheckGroup findCheckGroupById(Integer id) {
        return checkGroupDao.findCheckGroupById(id);
    }

    /**
     * 更新检查组
     * @param checkItemIds
     * @param checkGroup
     */
    @Override
    @Transactional(readOnly = false,propagation = Propagation.REQUIRED)
    public void edit(Integer[] checkItemIds, CheckGroup checkGroup) {
        Integer checkGroupId = checkGroup.getId();
        checkGroupDao.update(checkGroup);
        checkGroupAndCheckItemDao.deleteByCheckGroupId(checkGroupId);
        this.addCheckGroupAndCheckItem(checkItemIds,checkGroupId);

    }

    /**
     * 添加检查组检查项中间表数据
     * @param checkItemIds
     * @param checkGroupId
     */
    public void addCheckGroupAndCheckItem(Integer[] checkItemIds, Integer checkGroupId) {
        for(Integer checkItemId: checkItemIds){
            HashMap<String, Integer> idMap = new HashMap<>();
            idMap.put("checkItemId",checkItemId);
            idMap.put("checkGroupId",checkGroupId);
            checkGroupAndCheckItemDao.add(idMap);
        }
    }

    /**
     * 删除检查组
     * @param checkGroupId
     */
    @Override
    @Transactional(readOnly = false,propagation = Propagation.REQUIRED)
    public void delete(Integer checkGroupId) {
        checkGroupAndCheckItemDao.deleteByCheckGroupId(checkGroupId);
        checkGroupDao.delete(checkGroupId);
    }
}
