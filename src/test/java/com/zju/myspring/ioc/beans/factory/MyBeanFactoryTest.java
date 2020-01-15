package com.zju.myspring.ioc.beans.factory;

import com.zju.myspring.BeanDefinition;
import com.zju.myspring.ioc.HelloService;
import com.zju.myspring.ioc.HelloServiceImpl;
import org.junit.Test;

/**
 * @author zcz
 * @CreateTime 2020/1/15 17:33
 */
public class MyBeanFactoryTest {
    @Test
    public void testOne() throws Exception{
        /**
         * 先创建一个工厂
         */
        BeanFactory beanFactory = new MyBeanFactory();

        /**
         * 创建一个 bean
         */
        HelloServiceImpl helloService = new HelloServiceImpl();

        /**
         *  创建一个 beanDefinition ，并绑定一下 bean
         */
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBean(helloService);

        /**
         * 在工厂里面 注册 一个 beanDefinition
         */
        beanFactory.registerBeanDefinition("helloService", beanDefinition);

        /**
         * 获取实例
         */
        HelloService helloServiceTest = (HelloService)beanFactory.getBean("helloService");
        helloServiceTest.helloWorld();
    }
}
