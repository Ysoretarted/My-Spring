package com.zju.myspring.ioc.beans.factory;

import com.zju.myspring.BeanDefinition;
import com.zju.myspring.ioc.beans.BeanPostProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zcz
 * @CreateTime 2020/1/15 19:49
 */
public class AbstractBeanFactory implements BeanFactory {
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    /**
     * 记录工厂 所有已经注册的 beanDefinition 的名称
     *//*
    private final List<String> beanDefinitionName = new ArrayList<>();*/

    private List<BeanPostProcessor>beanPostProcessors = new ArrayList<>();

    @Override
    public Object getBean(String beanName) throws Exception {
        BeanDefinition beanDefinition = this.beanDefinitionMap.get(beanName);

        if (null == beanDefinition) {
            throw new IllegalArgumentException(this.getClass().toString() + "  " + "beanDefinition called "
                    + beanName + " is not defined");
        }
        Object bean = beanDefinition.getBean();
        if (null == bean) {
            bean = doCreateBean(beanDefinition);
            /**
             *   TODO 这里也就是说 父类可以调用子类的方法  错的（这只是向上转型的结果）
             */
            applyPropertyValue(bean, beanDefinition);

            //TODo
            /**
             * 这里的bean  和上面的bean 是不一样的
             * 这里的bean是经过处理的，上面的bean 仅仅是因为 为空，然后被创建出来的
             * 这里的bean可以加一些自己的操作，比如 代理
             */
            bean = initializeBean(bean, beanName);

            beanDefinition.setBean(bean);
        }
        return bean;
    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(name, beanDefinition);
    }


    protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
        Object bean = createBeanInstance(beanDefinition);

        beanDefinition.setBean(bean);

        //TODOx  都行，执行一遍就行了
        /*applyPropertyValues(bean, beanDefinition);*/

        return bean;
    }


    protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
        return beanDefinition.getBeanClass().newInstance();
    }

    /**
     * 通过反射  调用method 的invoke方法是，会有一些异常要处理，这里抛下异常
     *
     * @param bean
     * @param beanDefinition
     * @throws Exception
     */
    protected void applyPropertyValue(Object bean, BeanDefinition beanDefinition) throws Exception {
        /**
         * 这句话并没有输出，应该定义的是  AutowireCapableBeanFactory类型的
         */
        System.out.println("这个是" + this.getClass() + "的applyPropertyValue方法");
    }


    /**
     * 根据Class  来获取beans
     * 判断已经注册的beanDefinition的bean的类型是不是  给定类型的本身 以及 子类
     * 是的话  就弄成一个集合返回
     *
     * @param type
     * @return
     */
    public List getBeansForType(Class type) throws Exception {
        List beans = new ArrayList<>();
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : beanDefinitionMap.entrySet()) {
            /**
             * beanDefinitionEntry.getValue().getClass() 这里错了（这样结果是BeanDefinition.class)
             *   应该是 beanDefinitionEntry.getValue().getBeanClass()
             */
            if (type.isAssignableFrom((beanDefinitionEntry.getValue().getBeanClass()))) {
                beans.add(getBean(beanDefinitionEntry.getKey()));
            }
        }
        return beans;
    }

    /**
     * 做一些有关 bean 的初始化工作
     * TODO 原来是protected
     */
    private Object initializeBean(Object bean, String name) throws Exception{
        for(BeanPostProcessor beanPostProcessor : beanPostProcessors){
            beanPostProcessor.postProcessorBeforeInitialization(bean, name);
        }

        // TODO  这里也可以加一下方法
        for(BeanPostProcessor beanPostProcessor : beanPostProcessors){
            /**
             * 这里少了一个 bean =
             */
            bean = beanPostProcessor.postProcessorAfterInitialization(bean, name);
        }
        return bean;
    }

    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        this.beanPostProcessors.add(beanPostProcessor);
    }


    /**
     * 根据工厂已有的 bean的Name  ，全都跑一遍 getBean()
     * @throws Exception
     */
    public void preInstantiateSingletons() throws Exception{
        for(Map.Entry<String,BeanDefinition> beanDefinitionEntry : beanDefinitionMap.entrySet()){
            getBean(beanDefinitionEntry.getKey());
        }
    }
}