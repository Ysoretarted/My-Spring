package com.zju.myspring.aop.porxy;

import com.zju.myspring.aop.AdvisedSupport;
import com.zju.myspring.aop.TargetSource;
import com.zju.myspring.aop.proxy.JdkDynamicAopProxy;
import com.zju.myspring.interceptor.TimeInterceptor;
import com.zju.myspring.ioc.HelloService;
import com.zju.myspring.ioc.HelloServiceImpl;
import com.zju.myspring.ioc.context.ApplicationContext;
import com.zju.myspring.ioc.context.ClassPathXmlApplicationContext;
import org.junit.Before;
import org.junit.Test;

/**
 * @author zcz
 * @CreateTime 2020/2/4 21:58
 */
public class JdkDynamicAopProxyTest {
    private ApplicationContext applicationContext;

    @Before
    public void init() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("tinyioc.xml");
    }

    @Test
    public void testTimeInterceptor() throws Exception{
        //  不经过AOP
        HelloService helloService = (HelloServiceImpl)applicationContext.getBean("helloWorldService");
        helloService.helloWorld();

        System.out.println("---------------------------------------------------------------------我是分割线");

        //经过AOP的
        TimeInterceptor timeInterceptor = new TimeInterceptor();
        TargetSource targetSource = new TargetSource(helloService, HelloServiceImpl.class, HelloService.class);
        AdvisedSupport advisedSupport= new AdvisedSupport();

        advisedSupport.setTargetSource(targetSource);
        advisedSupport.setMethodInterceptor(timeInterceptor);

        JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(advisedSupport);
        HelloService helloServiceProxy = (HelloService)jdkDynamicAopProxy.getProxy();
        helloServiceProxy.helloWorld();




    }
}
