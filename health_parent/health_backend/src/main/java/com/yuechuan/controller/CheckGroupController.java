package com.yuechuan.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yuechuan.entity.PageResult;
import com.yuechuan.entity.QueryPageBean;
import com.yuechuan.service.CheckGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkGroup")
public class CheckGroupController {

    @Reference
    private CheckGroupService checkGroupService;

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return checkGroupService.findPage(queryPageBean);
    }

}
