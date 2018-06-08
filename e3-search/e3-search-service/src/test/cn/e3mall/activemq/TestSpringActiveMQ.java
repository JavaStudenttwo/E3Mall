package cn.e3mall.activemq;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

/**
 * @Date 2018/6/8 20:31
 * @Author CycloneKid sk18810356@gmail.com
 * @PackageName: cn.e3mall.activemq
 * @ClassName: TestSpringActiveMQ
 * @Description: ActiveMQ与Spring整合后的消息接收方式测试
 *
 */
public class TestSpringActiveMQ {

    /**
     * @Date 2018/6/8 20:32
     * @Author CycloneKid sk18810356@gmail.com
     * @MethodName: testQueueConsumerTemplete
     * @Params: []
     * @ReturnType: void
     * @Description: 初始化Spring容器，监听对象就会被创建并开始监听
     *
     */
    @Test
    public void testQueueConsumerTemplete() throws Exception {
        //初始化spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq.xml");
        //等待
        System.in.read();
    }
}
