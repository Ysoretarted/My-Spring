package com.zju.myspring.ioc.beans.Logger;

import com.zju.myspring.ioc.beans.BeanPostProcessor;

/** 初始化-bean 的日志类
 * @author zcz
 * @CreateTime 2020/2/6 10:45
 */
public class BeanInitalizeLogger implements BeanPostProcessor {
    @Override
    public Object postProcessorBeforeInitialization(Object bean, String beanName) throws Exception {
        System.out.println(getClass() + "   实例bean " + beanName + "初始化开始");
        return bean;
    }

    @Override
    public Object postProcessorAfterInitialization(Object bean, String beanName) throws Exception {
        System.out.println(getClass() + "   实例bean " + beanName + "初始化开始");
        return bean;
    }
}
