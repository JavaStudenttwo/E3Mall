package cn.e3mall.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @Date 2017/12/23 10:42
 * @Author CycloneKid sk18810356@gmail.com
 * @PackageName: cn.e3mall.service
 * @ClassName: ServicePublish
 * @Description: 初始化Spring容器，发布服务
 *
 */
public class ServicePublish {


    public void servicePublish() throws IOException {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        System.out.println("服务发布");
        System.in.read();
        System.out.println("服务关闭");
    }
}
