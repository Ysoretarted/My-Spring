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
            /**
             *   TODO 这里也就是说 父类可以调用子类的方法  错的（这只是向上转型的结果）
             */
            applyPropertyValue(bean,beanDefinition);

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

    /**
     * 通过反射  调用method 的invoke方法是，会有一些异常要处理，这里抛下异常
     * @param bean
     * @param beanDefinition
     * @throws Exception
     */
    protected void applyPropertyValue(Object bean, BeanDefinition beanDefinition) throws Exception{
        /**
         * 这句话并没有输出，应该定义的是  AutowireCapableBeanFactory类型的
         */
        System.out.println("这个是" + this.getClass() + "的applyPropertyValue方法");
    }
}
