package com.zju.myspring.ioc.beans.reader;

import com.zju.myspring.BeanDefinition;
import com.zju.myspring.ioc.HelloService;
import com.zju.myspring.ioc.beans.factory.AutowireCapableBeanFactory;
import com.zju.myspring.ioc.beans.factory.BeanFactory;
import com.zju.myspring.ioc.beans.io.ResourceLoader;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * @author zcz
 * @CreateTime 2020/1/19 16:31
 */
public class XmlBeanDefinitionReaderTest {
    /**  step4
     * 测试读取xml 的内容
     *    可以读到了
     */
    @Test
    public void test1() throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("tinyioc.xml");


        BeanFactory beanFactory = new AutowireCapableBeanFactory();
        for(Map.Entry<String,BeanDefinition>beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()){
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(),beanDefinitionEntry.getValue());
        }

        HelloService helloService = (HelloService)beanFactory.getBean("helloWorldService");
        System.out.println(helloService);
        helloService.helloWorld();

    }
}
