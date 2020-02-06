package com.zju.myspring.aop;

import com.zju.myspring.ioc.beans.factory.BeanFactory;

/**
 * 用来设置 bean工厂
 * @author zcz
 * @CreateTime 2020/2/6 10:51
 */
public interface BeanFactoryAware {

    void setBeanFactory(BeanFactory beanFactory);

}
