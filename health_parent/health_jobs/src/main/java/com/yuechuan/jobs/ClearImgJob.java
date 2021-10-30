package com.yuechuan.jobs;

import com.yuechuan.constant.RedisConstant;
import com.yuechuan.util.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * 定时清理套餐管理垃圾图片
 */
public class ClearImgJob {
    @Autowired
    private JedisPool jedisPool;

    public void clearImg(){
        Set<String> imgSet = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES,
                                                                RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        if (imgSet != null){
            for(String imgName: imgSet) {
                QiniuUtils.deleteFileFromQiniu(imgName);
                jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES,imgName);
                System.out.println(imgName+ " 已删除！");
            }
        }
    }
}
