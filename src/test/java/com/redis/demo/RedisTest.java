package com.redis.demo;

import com.redis.demo.model.RedisMsgPubSubListener;
import com.redis.demo.redis.RedisTemplateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplateService redisTemplateService;

    /**
     * @Description 测试插入
     * @Param
     * @Author lujian
     * @Date 10:13 2018/11/5
     * @Remark
     * @Return void
     */
    @Test
    public void testInsert() {
//        Map<String, Object> map = new HashMap<>();
//        map.put("parkingName", "123");
//        map.put("inDate", "2012-5-9");
//        redisTemplateService.addMap("testkey", map);
    }

    /**
     * @Description 测试查询
     * @Param
     * @Author lujian
     * @Date 16:05 2018/11/1
     * @Return void
     */
    @Test
    public void testGet() {
        String indateStr = (String) redisTemplateService.hashGet("CAR_PARKING_NOW_苏F997A5", "inDate");
        System.out.println(indateStr);
    }

    /**
     * @Description 测试删除
     * @Param
     * @Author lujian
     * @Date 16:05 2018/11/1
     * @Return void
     */
    @Test
    public void testRemove() {
//        redisTemplateService.removeMap("testkey");
    }

    @Test
    public void testSubscribe() throws Exception {
        Jedis jedis = new Jedis("localhost");
        RedisMsgPubSubListener listener = new RedisMsgPubSubListener();
        jedis.subscribe(listener, "__keyevent@0__:expired");
        //other code
        System.out.println("hello");
    }

    @Test
    public void testPublish() throws Exception {
        Jedis jedis = new Jedis("localhost");
        jedis.publish("__keyevent@0__:expired", "我是天才");
        Thread.sleep(1000);
        jedis.publish("redisChatTest", "我牛逼");
        Thread.sleep(1000);
        jedis.publish("redisChatTest", "哈哈");
    }
}
