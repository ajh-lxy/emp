package com.fh.utils.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author Lenovo
 * @title: RedisPools
 * @projectName drug_web
 * @description: TODO
 * @date 2019/12/1817:12
 */
public class RedisPools {
    private static JedisPool jedisPool;


    private RedisPools (){

    }

    static {
//        配置连接信息
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        最大连接数
        jedisPoolConfig.setMaxTotal(100);
//        最大空闲数
        jedisPoolConfig.setMaxIdle(100);
//        最小空闲数
        jedisPoolConfig.setMinIdle(10);
//      创建连接对象
        jedisPool=new JedisPool(jedisPoolConfig,"192.168.119.132",6379);
    }

//    从连接池中拿一个
    public static Jedis getJeDis(){
        Jedis jedis=jedisPool.getResource();
        return jedis;
    }

//    把连接池放回连接池中
    public static void returnJeDis(Jedis jedis){
        if(jedis!=null){
            jedis.close();
        }
    }
}
