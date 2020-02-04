package com.zju.myspring.ioc.context;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import com.zju.myspring.ioc.HelloService;
import com.zju.myspring.ioc.HelloServiceImpl;
import org.junit.Test;

import java.io.IOException;

/**
 * @author zcz
 * @CreateTime 2020/2/3 19:13
 */
public class ClassPathXmlApplicationContextTest {
    @Test
    public void testSix() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("tinyioc.xml");
        HelloService helloService = (HelloServiceImpl)context.getBean("helloWorldService");
        helloService.helloWorld();
    }

}
