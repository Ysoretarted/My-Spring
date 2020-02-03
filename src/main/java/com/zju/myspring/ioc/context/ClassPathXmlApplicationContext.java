package com.zju.myspring.ioc.context;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import com.zju.myspring.BeanDefinition;
import com.zju.myspring.ioc.beans.factory.AbstractBeanFactory;
import com.zju.myspring.ioc.beans.factory.AutowireCapableBeanFactory;
import com.zju.myspring.ioc.beans.io.ResourceLoader;
import com.zju.myspring.ioc.beans.reader.XmlBeanDefinitionReader;

import java.io.IOException;
import java.util.Map;

/**
 * @author zcz
 * @CreateTime 2020/2/3 18:42
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {
    private String configLocation;

    /**
     * 只有一个参数（配置文件路径）   就默认为AutowireCapableBeanFactory 工厂
     *
     * @param configLocation
     * @throws IOException
     */
    public ClassPathXmlApplicationContext(String configLocation) throws Exception {
        this(configLocation, new AutowireCapableBeanFactory());
    }

    /**
     * 这里就可以指定工厂类型，  只要继承了 AbstractBeanFactory
     * @param configLocation
     * @param abstractBeanFactory
     * @throws IOException
     */
    public ClassPathXmlApplicationContext(String configLocation, AbstractBeanFactory abstractBeanFactory) throws Exception {
        super(abstractBeanFactory);
        this.configLocation = configLocation;
        loadBeanDefinitions(abstractBeanFactory);
    }

    @Override
    public Object getBean(String beanName) throws Exception {
        return super.getBean(beanName);
    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception{


    }

    @Override
    protected void loadBeanDefinitions(AbstractBeanFactory abstractBeanFactory) throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("tinyioc.xml");

        for(Map.Entry<String,BeanDefinition>beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()){
            abstractBeanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }
    }

}