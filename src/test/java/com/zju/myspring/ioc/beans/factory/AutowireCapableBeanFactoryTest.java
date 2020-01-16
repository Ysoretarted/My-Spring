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
        /**
         * 再添加属性键值对的时候，进行了判重，所以输出为上面那句话
         *   没有进行判重的话，  会有两个输出
         */
        propertyValues.addPropertyValue(new PropertyValue("attribute","AAAAA"));

        beanDefinition.setBeanClass(HelloServiceImpl.class);
        /*beanDefinition.setBeanClassName("com.zju.myspring.ioc.HelloServiceImpl");*/
        beanDefinition.setPropertyValues(propertyValues);

        beanFactory.registerBeanDefinition("HelloService",beanDefinition);

        HelloService helloService = (HelloService)beanFactory.getBean("HelloService");
        helloService.helloWorld();
        HelloService helloService1 = (HelloService)beanFactory.getBean("HelloService");
        helloService1.helloWorld();

        /**
         * 这样就覆盖了 会输出AAA
         */
        /*PropertyValues propertyValues1 = new PropertyValues();
        propertyValues1.addPropertyValue(new PropertyValue("attribute","AAAA"));
        BeanDefinition beanDefinition1 = new BeanDefinition();
        beanDefinition1.setPropertyValues(propertyValues1);
        beanDefinition1.setBeanClass(HelloServiceImpl.class);
        beanFactory.registerBeanDefinition("HelloService", beanDefinition1);
        HelloService helloService1 = (HelloService)beanFactory.getBean("HelloService");
        helloService1.helloWorld();*/

    }
}
