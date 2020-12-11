package com.offcn.test;

import com.offcn.UserStartApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserStartApp.class)
public class TestRedis {

    private Logger log =  LoggerFactory.getLogger(getClass());

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void redisTest(){
        redisTemplate.opsForValue().set("key1","添加redis成功");
        redisTemplate.opsForValue().set("key2","添加限时redis成功",10, TimeUnit.SECONDS);
        System.out.println("添加redis成功");

        log.error("我说一个错误");

        String s = "我不是一个错误";
        log.debug("我出错了 {}",s);

    }



}
