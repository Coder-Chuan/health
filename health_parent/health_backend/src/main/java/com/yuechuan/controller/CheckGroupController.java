package com.yuechuan.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yuechuan.constant.MessageConstant;
import com.yuechuan.entity.PageResult;
import com.yuechuan.entity.QueryPageBean;
import com.yuechuan.entity.Result;
import com.yuechuan.pojo.CheckGroup;
import com.yuechuan.pojo.CheckItem;
import com.yuechuan.service.CheckGroupService;
import com.yuechuan.service.CheckItemService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/checkGroup")
public class CheckGroupController {

    @Reference
    private CheckGroupService checkGroupService;

    @Reference
    private CheckItemService checkItemService;

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return checkGroupService.findPage(queryPageBean);
    }

    @RequestMapping("/findCheckItemList.do")
    public Result findCheckItemList(){
        try{
            List<CheckItem> dataList = checkItemService.findCheckItemList();
            return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,dataList);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }

    }

    @RequestMapping("/add.do")
    public Result add(@RequestBody CheckGroup checkGroup,Integer[] checkItemIds){
        try{
            checkGroupService.add(checkItemIds,checkGroup);
            return new Result(true,MessageConstant.ADD_CHECKGROUP_SUCCESS);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_CHECKGROUP_FAIL);
        }
    }

    @RequestMapping("/findCheckItemIdsByCheckGroupId.do")
    public Result findCheckItemIdsByCheckGroupId(Integer id){
        try{
            List<Integer> idList = checkGroupService.findCheckItemIdsByCheckGroupId(id);
            return new Result(true,MessageConstant.GET_CHECKITEMIDS_SUCCESS,idList);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_CHECKITEMIDS_FAIL);
        }
    }

    @RequestMapping("/findCheckGroupById.do")
    public Result findCheckGroupById(Integer id){
        try{
            CheckGroup checkGroup = checkGroupService.findCheckGroupById(id);
            return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroup);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(true,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    @RequestMapping("/edit.do")
    public Result edit(@RequestBody CheckGroup checkGroup, Integer[] checkItemIds){
        try{
            checkGroupService.edit(checkItemIds,checkGroup);
            return new Result(true,MessageConstant.EDIT_CHECKGROUP_SUCCESS);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
    }

    @RequestMapping("/delete.do")
    public Result delete(Integer checkGroupId){
        try{
            checkGroupService.delete(checkGroupId);
            return new Result(true,MessageConstant.DELETE_CHECKGROUP_SUCCESS);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
    }

}
