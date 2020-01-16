package com.zju.myspring.ioc.beans.factory;

import com.zju.myspring.BeanDefinition;
import com.zju.myspring.ioc.HelloService;
import com.zju.myspring.ioc.HelloServiceImpl;
import com.zju.myspring.ioc.beans.PropertyValue;
import com.zju.myspring.ioc.beans.PropertyValues;
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

    /**
     * 测试第三步的属性注入
     */
    @Test
    public void testThree() throws Exception{
        BeanFactory beanFactory = new AutowireCapableBeanFactory();

        BeanDefinition beanDefinition = new BeanDefinition();

        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("attribute","这是HelloService的成员属性"));

        beanDefinition.setBeanClass(HelloServiceImpl.class);
        /*beanDefinition.setBeanClassName("com.zju.myspring.ioc.HelloServiceImpl");*/
        beanDefinition.setPropertyValues(propertyValues);

        beanFactory.registerBeanDefinition("HelloService",beanDefinition);

        HelloService helloService = (HelloService)beanFactory.getBean("HelloService");
        helloService.helloWorld();
    }
}
