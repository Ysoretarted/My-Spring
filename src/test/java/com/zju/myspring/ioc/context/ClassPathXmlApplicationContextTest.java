package com.zju.myspring.ioc.context;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import com.zju.myspring.ioc.HelloService;
import org.junit.Test;

import java.io.IOException;

/**
 * @author zcz
 * @CreateTime 2020/2/3 19:13
 */
public class ClassPathXmlApplicationContextTest {
    @Test
    public void testSix() throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("tinyioc.xml");
        HelloService helloService = (HelloService)context.getBean("helloWorldService");
        helloService.helloWorld();
    }

}
