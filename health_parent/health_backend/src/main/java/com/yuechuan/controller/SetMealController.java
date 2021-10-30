package com.yuechuan.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yuechuan.constant.MessageConstant;
import com.yuechuan.constant.RedisConstant;
import com.yuechuan.entity.PageResult;
import com.yuechuan.entity.QueryPageBean;
import com.yuechuan.entity.Result;
import com.yuechuan.pojo.CheckGroup;
import com.yuechuan.pojo.Setmeal;
import com.yuechuan.service.CheckGroupService;
import com.yuechuan.service.SetMealService;
import com.yuechuan.util.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/setmeal")
public class SetMealController {

    @Reference
    private CheckGroupService checkGroupService;

    @Reference
    private SetMealService setMealService;

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/upload.do")
    public Result imgUpload(@RequestParam("imgFile") MultipartFile imgFile) {
        String imgOriginalFilename = imgFile.getOriginalFilename();
        int index = imgOriginalFilename.lastIndexOf(".");
        String subFileName = imgOriginalFilename.substring(index);
        String fileName = UUID.randomUUID().toString() + subFileName;
        try {
            QiniuUtils.upload2Qiniu(imgFile.getBytes(),fileName);
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.PIC_UPLOAD_FAIL);
        }
        return new Result(true,MessageConstant.PIC_UPLOAD_SUCCESS,fileName);
    }

    @RequestMapping("/findCheckGroupList.do")
    public Result findCheckGroupList(){
        try{
            List<CheckGroup> list = checkGroupService.findCheckGroupList();
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,list);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }

    }

    @RequestMapping("/add.do")
    public Result add(Integer[] checkGroupIds, @RequestBody Setmeal setmeal){
        try{
            setMealService.add(checkGroupIds,setmeal);
            return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_SETMEAL_FAIL);
        }
    }

    @RequestMapping("/findPage.do")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return setMealService.findPage(queryPageBean);
    }
}
