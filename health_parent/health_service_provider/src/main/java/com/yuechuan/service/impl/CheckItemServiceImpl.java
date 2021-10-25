package com.yuechuan.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuechuan.dao.CheckItemDao;
import com.yuechuan.entity.PageResult;
import com.yuechuan.entity.QueryPageBean;
import com.yuechuan.pojo.CheckItem;
import com.yuechuan.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = CheckItemService.class)
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    private CheckItemDao checkItemDao;

    /**
     * 添加单条检查项
     * @param checkItem
     */
    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    /**
     * 检查项分页查询
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        List<CheckItem> list = checkItemDao.findPage(queryPageBean.getQueryString());
        PageInfo<CheckItem> pageInfo = new PageInfo<>(list);
        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 根据id查询检查项
     * @param id
     * @return
     */
    @Override
    public CheckItem findById(Integer id) {
        return checkItemDao.findById(id);
    }

    /**
     * 更新检查项
     * @param checkItem
     */
    @Override
    @Transactional(readOnly = false,propagation = Propagation.REQUIRED)
    public void update(CheckItem checkItem) {
        checkItemDao.update(checkItem);
    }

    /**
     * 根据id删除检查项
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        checkItemDao.deleteById(id);
    }
}
