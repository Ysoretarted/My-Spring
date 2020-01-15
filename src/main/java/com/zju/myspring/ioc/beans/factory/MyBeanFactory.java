package com.zju.myspring.ioc.beans.factory;

import com.zju.myspring.BeanDefinition;
import com.zju.myspring.ioc.beans.factory.BeanFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zcz
 * @CreateTime 2020/1/15 10:35
 */
public class MyBeanFactory implements BeanFactory {
    // 要通过实例的名称获取实例，可以用map实现
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public Object getBean(String beanName) {
        BeanDefinition beanDefinition = this.beanDefinitionMap.get(beanName);
        if (null == beanDefinition) {
            throw new IllegalArgumentException("No bean called " + beanName + " is defined");
        }
        Object object = beanDefinition.getBean();
        if (null == object) {
            // TODO
            throw new IllegalArgumentException(beanName + "得到的BeanDefinition 的bean为空");
        }
        return object;

    }

    /**
     * 有获取就有注册
     */
    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(name, beanDefinition);
    }


}
