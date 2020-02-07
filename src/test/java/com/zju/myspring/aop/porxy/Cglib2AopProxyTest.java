package com.zju.myspring.aop.porxy;

import com.zju.myspring.aop.AdvisedSupport;
import com.zju.myspring.aop.TargetSource;
import com.zju.myspring.aop.proxy.Cglib2AopAopProxy;
import com.zju.myspring.interceptor.TimeInterceptor;
import com.zju.myspring.ioc.HelloService;
import com.zju.myspring.ioc.HelloServiceImpl;
import com.zju.myspring.ioc.context.ApplicationContext;
import com.zju.myspring.ioc.context.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @author zcz
 * @CreateTime 2020/2/7 14:03
 */
public class Cglib2AopProxyTest {
    @Test
    public void testStepTen() throws Exception{
        ApplicationContext context = new ClassPathXmlApplicationContext("tinyioc.xml");
        System.out.println("分割线1 ---------------------------------------------------------------------------");
        HelloService helloService = (HelloService)context.getBean("helloWorldService");
        System.out.println("分割线2 ---------------------------------------------------------------------------");
        helloService.helloWorld();

        AdvisedSupport advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(helloService, HelloServiceImpl.class,HelloService.class);

        advisedSupport.setTargetSource(targetSource);
        advisedSupport.setMethodInterceptor(new TimeInterceptor());


        //  创建代理
        Cglib2AopAopProxy cglib2AopAopProxy = new Cglib2AopAopProxy(advisedSupport);
        HelloService helloServiceProxy = (HelloService)cglib2AopAopProxy.getProxy();
        helloServiceProxy.helloWorld();
    }
}
