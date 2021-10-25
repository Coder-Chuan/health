package com.yuechuan.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yuechuan.constant.MessageConstant;
import com.yuechuan.entity.PageResult;
import com.yuechuan.entity.QueryPageBean;
import com.yuechuan.entity.Result;
import com.yuechuan.pojo.CheckItem;
import com.yuechuan.service.CheckItemService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkItem")
public class CheckItemController {

    @Reference
    private CheckItemService checkItemService;

    @RequestMapping("/add.do")
    public Result add(@RequestBody CheckItem chekcitem){
        try{
            checkItemService.add(chekcitem);
            return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }

    }

    @RequestMapping("/findPage.do")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return checkItemService.findPage(queryPageBean);
    }

    @RequestMapping("/findById")
    public Result findById(String id) {
        try{
            CheckItem checkItem = checkItemService.findById(Integer.parseInt(id));
            return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItem);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    @RequestMapping("/alter.do")
    public Result alter(@RequestBody CheckItem checkItem) {
        try{
            checkItemService.update(checkItem);
            return new Result(true,MessageConstant.EDIT_CHECKITEM_SUCCESS);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_CHECKITEM_FAIL);
        }
    }

    @RequestMapping("/deleteById.do")
    public Result deleteById(String id) {
        System.out.println("进入方法");
        try{
            checkItemService.deleteById(Integer.parseInt(id));
            return new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_CHECKITEM_FAIL);
        }
    }
}
