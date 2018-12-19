package com.jt.manage.test;

import java.util.*;

import com.zuql.jt.manage.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


import redis.clients.jedis.*;

public class TestRedis {
	
	/**
	 * 1.链接Redis IP:端口
	 */
	@Test
	public void testString(){
		Jedis jedis =new Jedis("192.168.227.133",6379);
		jedis.set("1808", "今天星期一,不想上课,累得慌");
		System.out.println("输出:"+jedis.get("1808"));
		jedis.expire("1808",10);
		
		//设定数据同时设定超时时间
		jedis.setex("abc", 20, "123");
		
	}
	
	
	@Test
	public void testHash(){
		User user = new User();
		/*user.setId(1000);
		user.setName("达内");
		user.setAge(20);
		user.setSex("男");*/
		Jedis jedis = new Jedis("192.168.227.133",6379);
		jedis.hset("user1","id", user.getId()+"");
		/*jedis.hset("user1","name", user.getName());
		jedis.hset("user1","age", user.getAge()+"");*/
		Map<String,String> map = jedis.hgetAll("user1");
		System.out.println(map);
	}
	
	
	//操作List集合
	@Test
	public void testList(){
		Jedis jedis = new Jedis("192.168.227.133",6379);
		jedis.lpush("list1", "1","2","3");
		//当做队列使用
		System.out.println(jedis.rpop("list1"));
	}
	
	
	//测试Redis中的事务
	@Test
	public void testTx(){
		Jedis jedis = new Jedis("192.168.227.133",6379);
		
		//开启事务
		Transaction transaction = jedis.multi();
		
		//数据操作
		transaction.set("1809","tomcat猫大战葫芦娃");
		//transaction.exec();
		transaction.discard();
	}
	
	
	//通过程序链接redis分片
	@Test
	public void testShard(){
		//定义链接池
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(1000);
		poolConfig.setMaxIdle(50);//最大的空闲数量
		poolConfig.setMinIdle(10);//定义最小空闲链接数量
		//定义多个redis
		List<JedisShardInfo> shards = new ArrayList<>();
		shards.add(new JedisShardInfo("192.168.227.133",6379));
		shards.add(new JedisShardInfo("192.168.227.133",6380));
		shards.add(new JedisShardInfo("192.168.227.133",6381));
		ShardedJedisPool pool = 
				new ShardedJedisPool(poolConfig, shards);
		ShardedJedis shardedJedis = pool.getResource();
		shardedJedis.set("shards","redis分片技术");
		System.out.println(shardedJedis.get("shards"));
		pool.returnResource(shardedJedis);
	}
    //实现哨兵的测试
    @Test
    public void testSentinel(){
        //1.定义池
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(1000);

        //转化IP和端口工具类
        HostAndPort port =  new HostAndPort("192.168.1.1", 8080);
        System.out.println(port);


        //2将redis哨兵节点写入集合
        Set<String> sentinels = new HashSet<>();
        sentinels.add("192.168.227.133:26379");
        sentinels.add("192.168.227.133:26380");
        sentinels.add("192.168.227.133:26381");

        //3.定义链接池
        JedisSentinelPool pool =
                new JedisSentinelPool("mymaster", sentinels, poolConfig);
        Jedis jedis = pool.getResource();
        jedis.set("1111", "1808班");
        System.out.println(jedis.get("1111"));
        pool.returnResource(jedis);

    }
    @Test
    public void test01(){
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.227.133", 7000));
        nodes.add(new HostAndPort("192.168.227.133", 7001));
        nodes.add(new HostAndPort("192.168.227.133", 7002));
        nodes.add(new HostAndPort("1192.168.227.133", 7003));
        nodes.add(new HostAndPort("1192.168.227.133", 7004));
        nodes.add(new HostAndPort("1192.168.227.133", 7005));
        nodes.add(new HostAndPort("192.168.227.133", 7006));
        nodes.add(new HostAndPort("1192.168.227.133", 7007));
        nodes.add(new HostAndPort("192.168.227.133", 7008));
        JedisCluster jedisCluster = new JedisCluster(nodes);

        jedisCluster.set("redis", "学习集群使用");
        System.out.println("获取数据:"+jedisCluster.get("redis"));
    }


}
