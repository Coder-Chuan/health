package com.yuechuan.service;

import com.yuechuan.entity.PageResult;
import com.yuechuan.entity.QueryPageBean;

public interface CheckGroupService {
    public PageResult findPage(QueryPageBean queryPageBean);
}
