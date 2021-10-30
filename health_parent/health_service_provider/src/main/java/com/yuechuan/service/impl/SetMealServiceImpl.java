package com.yuechuan.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yuechuan.constant.RedisConstant;
import com.yuechuan.dao.SetMealDao;
import com.yuechuan.dao.SetmealAndCheckgroupDao;
import com.yuechuan.entity.PageResult;
import com.yuechuan.entity.QueryPageBean;
import com.yuechuan.pojo.CheckGroup;
import com.yuechuan.pojo.Setmeal;
import com.yuechuan.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SetMealServiceImpl implements SetMealService {

    @Autowired
    private SetMealDao setMealDao;

    @Autowired
    private SetmealAndCheckgroupDao setmealAndCheckgroupDao;

    @Autowired
    private JedisPool jedisPool;

    /**
     * 添加套餐
     * @param checkGroupIds
     * @param setmeal
     */
    @Override
    public void add(Integer[] checkGroupIds, Setmeal setmeal) {
        setMealDao.add(setmeal);
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,setmeal.getImg());
        Integer setmealId = setmeal.getId();

        if(checkGroupIds != null && checkGroupIds.length > 0)
            this.addSetmealAndCheckGroup(checkGroupIds,setmealId);
    }

    /**
     * 添加套餐与检查组中间表
     * @param checkGroupIds
     * @param setmealId
     */
    @Override
    public void addSetmealAndCheckGroup(Integer[] checkGroupIds,Integer setmealId){
        for(Integer checkGroupId: checkGroupIds) {
            Map<String, Integer> idMap = new HashMap<>();
            idMap.put("checkGroupId",checkGroupId);
            idMap.put("setmealId",setmealId);
            setmealAndCheckgroupDao.add(idMap);
        }
    }

    /**
     * 查询套餐分页
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();

        PageHelper.startPage(currentPage,pageSize);
        Page<Setmeal> page = setMealDao.findByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }
}
