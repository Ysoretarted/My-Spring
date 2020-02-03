package com.zju.myspring.ioc.context;

import com.zju.myspring.BeanDefinition;
import com.zju.myspring.ioc.beans.factory.AbstractBeanFactory;

import java.io.IOException;

/**
 * @author zcz
 * @CreateTime 2020/2/3 14:10
 */
public abstract class AbstractApplicationContext implements ApplicationContext {
    private AbstractBeanFactory abstractBeanFactory;

    public AbstractApplicationContext(AbstractBeanFactory abstractBeanFactory){
        this.abstractBeanFactory = abstractBeanFactory;
    }
    @Override
    public Object getBean(String beanName) throws Exception {
        return abstractBeanFactory.getBean(beanName);
    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception {

    }

    /**
     *   这里的权限为private会怎么样?   private 不能和abstract组合使用
     *   抽象方法是为了让子类重写
     *
     */
    protected abstract void loadBeanDefinitions(AbstractBeanFactory abstractBeanFactory) throws Exception;
}
