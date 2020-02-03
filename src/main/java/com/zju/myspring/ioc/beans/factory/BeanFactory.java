package com.zju.myspring.ioc.beans.factory;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import com.zju.myspring.BeanDefinition;

import java.io.IOException;

/**
 * @author zcz
 * @CreateTime 2020/1/15 9:37
 */

/**
 * 这个在stepOne  应该是一个类的，但在step-two 用了Extract interface 的方法，变成接口
 */
public interface BeanFactory {
    Object getBean(String beanName) throws  Exception;

   /* void registerBean();*/
    void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception;
}
