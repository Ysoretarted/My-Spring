package com.zju.myspring;

import com.zju.myspring.ioc.beans.PropertyValues;
import lombok.Data;

/**
 * @author zcz
 * @CreateTime 2020/1/15 9:40
 */
@Data
public class BeanDefinition {
    private Object bean;

    private Class beanClass;

    private String beanClassName;

    //TODOxx     这个字段用来保存 bean 的一些属性
    private PropertyValues propertyValues = new PropertyValues();


    /**
     *
     * @param beanClassName
     */
    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
        try{
            this.beanClass = Class.forName(beanClassName);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
