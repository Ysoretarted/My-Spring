package com.zju.myspring.ioc.beans.factory;

import com.zju.myspring.BeanDefinition;
import com.zju.myspring.ioc.HelloService;
import org.junit.Test;

/**
 * @author zcz
 * @CreateTime 2020/1/15 20:50
 */
public class AutowireCapableBeanFactoryTest {
    @Test
    public void testAutowireCapableBeanFactoryGetBean() throws  Exception{
        BeanFactory beanFactory = new AutowireCapableBeanFactory();

        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName("com.zju.myspring.ioc.HelloServiceImpl");

        beanFactory.registerBeanDefinition("HelloService", beanDefinition);

        HelloService helloService = (HelloService) beanFactory.getBean("HelloService");
        helloService.helloWorld();
    }
}
