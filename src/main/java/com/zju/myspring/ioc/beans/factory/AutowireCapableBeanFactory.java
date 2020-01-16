package com.zju.myspring.ioc.beans.factory;

import com.zju.myspring.BeanDefinition;
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
        for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValueList()) {
            Object value = propertyValue.getValue();
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
                System.out.println(this.getClass() + "  并没有这样的set方法");
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
