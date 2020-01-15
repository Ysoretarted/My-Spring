package com.zju.myspring.ioc.beans.factory;

import com.zju.myspring.BeanDefinition;

/**
 * @author zcz
 * @CreateTime 2020/1/15 9:37
 */
public interface BeanFactory {
    Object getBean(String beanName);

   /* void registerBean();*/
    void registerBeanDefinition(String name, BeanDefinition beanDefinition);
}
