package com.zju.myspring.ioc.beans;

/**
 * @author zcz
 * @CreateTime 2020/2/6 10:41
 */
public interface BeanPostProcessor {

    Object postProcessorBeforeInitialization(Object bean, String beanName) throws Exception;

    Object postProcessorAfterInitialization(Object bean, String beanName) throws Exception;
}
