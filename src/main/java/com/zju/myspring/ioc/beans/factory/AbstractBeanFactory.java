package com.zju.myspring.ioc.beans.factory;

import com.zju.myspring.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zcz
 * @CreateTime 2020/1/15 19:49
 */
public class AbstractBeanFactory implements BeanFactory {
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    @Override
    public Object getBean(String beanName) throws Exception {
        BeanDefinition beanDefinition = this.beanDefinitionMap.get(beanName);

        if(null == beanDefinition){
            throw new IllegalArgumentException(this.getClass().toString() + "  " + "beanDefinition called "
                    + beanName + " is not defined");
        }
        Object bean = beanDefinition.getBean();
        if(null == bean){
            bean = doCreateBean(beanDefinition);

            //TODo
            /*bean = initializeBean(bean, name);*/

            beanDefinition.setBean(bean);
        }
        return bean;
    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(name, beanDefinition);
    }


    protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception{
        Object bean = createBeanInstance(beanDefinition);

        beanDefinition.setBean(bean);

        //TODO
        /*applyPropertyValues(bean, beanDefinition);*/

        return bean;
    }


    protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception{
        return beanDefinition.getBeanClass().newInstance();
    }
}
