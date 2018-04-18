package cn.e3mall.jedis;

import cn.e3mall.content.service.JedisClient;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;


/**
 * @Date 2017/12/29 15:45
 * @Author CycloneKid sk18810356@gmail.com
 * @PackageName: cn.e3mall.jedis
 * @ClassName: JedisTest
 * @Description: Jedis工具连接Redis数据库
 *
 */
public class JedisTest {

    /**
     * @Date 2017/12/29 15:46
     * @Author CycloneKid sk18810356@gmail.com
     * @MethodName: testJedis
     * @Params: []
     * @ReturnType: void
     * @Description: Jedis连接单机版Redis
     *
     */
    //@Test
    public void testJedis() throws Exception {
        Jedis jedis = new Jedis("192.168.25.129", 6379);
        String result = jedis.get("a");
        System.out.println(result);
        jedis.close();
    }

    /**
     * @Date 2017/12/29 15:49
     * @Author CycloneKid sk18810356@gmail.com
     * @MethodName: testJedisPool
     * @Params: []
     * @ReturnType: void
     * @Description: 使用Jedis连接池连接单机版Redis
     *
     */
    //@Test
    public void testJedisPool() throws Exception {
        JedisPool jedisPool = new JedisPool("192.168.25.129", 6379);
        Jedis jedis = jedisPool.getResource();
        jedis.set("JedisPool", "test");
        String result = jedis.get("JedisPool");
        System.out.println(result);
        jedis.close();
        jedisPool.close();
    }

    /**
     * @Date 2017/12/29 15:52
     * @Author CycloneKid sk18810356@gmail.com
     * @MethodName: testJedisCluster
     * @Params: []
     * @ReturnType: void
     * @Description: 使用Jedis连接集群版Redis
     *
     */
    //@Test
    public void testJedisCluster() throws Exception {
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.25.129", 7001));
        nodes.add(new HostAndPort("192.168.25.129", 7002));
        nodes.add(new HostAndPort("192.168.25.129", 7003));
        nodes.add(new HostAndPort("192.168.25.129", 7004));
        nodes.add(new HostAndPort("192.168.25.129", 7005));
        nodes.add(new HostAndPort("192.168.25.129", 7006));
        JedisCluster jedisCluster = new JedisCluster(nodes);
        jedisCluster.set("JedisCluster", "testCluster");
        String result = jedisCluster.get("JedisCluster");
        System.out.println(result);
        jedisCluster.close();
        String s = "./redis-trib.rb create –replicas 1 192.168.25.129:7001 192.168.25.129:7002 192.168.25.129:7003 192.168.25.129:7004 192.168.25.129:7005 192.168.25.129:7006";
    }

    /**
     * @Date 2017/12/29 22:48
     * @Author CycloneKid sk18810356@gmail.com
     * @PackageName: cn.e3mall.jedis
     * @ClassName: JedisTest
     * @Description: 使用Spring容器
     *
     */
    //@Test
    public void testJedisClient() throws Exception {

        //初始化Spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
        //从容器中获得JedisClient对象
        JedisClient jedisClient = (JedisClient) applicationContext.getBean("jedisClientCluster");
        jedisClient.set("Jedis", "first");
        String result = jedisClient.get("Jedis");
        System.out.println(result);


    }



}
