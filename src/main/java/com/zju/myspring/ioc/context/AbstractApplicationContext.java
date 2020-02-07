package com.zju.myspring.ioc.context;

import com.zju.myspring.BeanDefinition;
import com.zju.myspring.ioc.beans.BeanPostProcessor;
import com.zju.myspring.ioc.beans.factory.AbstractBeanFactory;

import java.io.IOException;
import java.util.List;

/**
 * @author zcz
 * @CreateTime 2020/2/3 14:10
 */
public abstract class AbstractApplicationContext implements ApplicationContext {
    private AbstractBeanFactory abstractBeanFactory;

    public AbstractApplicationContext(AbstractBeanFactory abstractBeanFactory) {
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
     * 这里的权限为private会怎么样?   private 不能和abstract组合使用
     * 抽象方法是为了让子类重写
     */
    protected abstract void loadBeanDefinitions(AbstractBeanFactory abstractBeanFactory) throws Exception;


    /**
     * TODO  这里用private会怎么样  试试（本来是protected）
     */
    private void registerBeanPostProcessors(AbstractBeanFactory beanFactory) throws Exception {
        /**
         * 因为返回的类型是不确定有，有很多可能
         */
        List beanPostProcessors = beanFactory.getBeansForType(BeanPostProcessor.class);
        for (Object beanPostProcessor : beanPostProcessors) {
            beanFactory.addBeanPostProcessor((BeanPostProcessor) beanPostProcessor);

        }
    }

    public void refresh() throws Exception{
        /**
         *   根据配置文件  加载所有的 BeanDefinition
         */
        loadBeanDefinitions(abstractBeanFactory);

        /**
         * 注册所有的BeanPostProcessor 放在bean工厂里
         */
        registerBeanPostProcessors(abstractBeanFactory);

        /**
         * 其实是根据所有的BeanDefinitionName 去 循环一遍 getBean
         *    所以一开始注册环境那步  会有点慢  ApplicationContext 那一步
         */
        onRefresh();
    }

    /**
     * TODO  原本是protected
     */
    private void onRefresh() throws Exception{
        abstractBeanFactory.preInstantiateSingletons();
    }
}
