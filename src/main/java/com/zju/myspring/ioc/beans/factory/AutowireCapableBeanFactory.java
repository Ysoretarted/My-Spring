package com.zju.myspring.ioc.beans.factory;

import com.zju.myspring.BeanDefinition;
import com.zju.myspring.BeanReference;
import com.zju.myspring.aop.BeanFactoryAware;
import com.zju.myspring.ioc.beans.PropertyValue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author zcz
 * @CreateTime 2020/1/15 20:50
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {
    /**
     * @param bean   TODO 到底是什么东东  bean是某个对象实例？？
     * @param beanDefinition
     */
    @Override
    protected void applyPropertyValue(Object bean, BeanDefinition beanDefinition) throws Exception {

        /**  TODOx  这句话也要加
         *   不加的话 AspectJAwareAdvisorAutoProxyCreator 的beanFactory成员是空的
         *   这句话大概就是  如果要赋值属性的实例  是工厂的话,  就set一下工厂
         *
         */
        if (bean instanceof BeanFactoryAware) {
            ((BeanFactoryAware) bean).setBeanFactory(this);
        }
        for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValueList()) {
            Object value = propertyValue.getValue();
            if(value instanceof BeanReference){
                BeanReference beanReference = (BeanReference) value;
                /**
                 * 获取引用bean 的实例，  等会通过反射赋值给另外一个bean
                 */
                value = getBean(beanReference.getName());
            }
            String attribute = propertyValue.getName();
            /**
             * 获取set方法  如 setName
             */
            try {
                Method setMethod = bean.getClass().getDeclaredMethod("set"
                        + attribute.substring(0, 1).toUpperCase() + attribute.substring(1), value.getClass());
                setMethod.setAccessible(true);
                /**
                 * 第一个参数 表明哪个对象调用该方法
                 *    参数数组，表明该方法的形参
                 */
                setMethod.invoke(bean,value);
            } catch (NoSuchMethodException e) {
                System.out.println(this.getClass() + "  并没有这样的set" + attribute+"方法");
                try {
                    Field field = bean.getClass().getDeclaredField(attribute);
                    field.setAccessible(true);
                    field.set(bean,value);
                } catch (NoSuchFieldException e1) {
                    System.out.println(this.getClass() + "  " + attribute + "这样的field并不存在");
                }

            }
        }

    }
}
