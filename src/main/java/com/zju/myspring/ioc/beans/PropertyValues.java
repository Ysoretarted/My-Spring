package com.zju.myspring.ioc.beans;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
/**
 * @author zcz
 * @CreateTime 2020/1/16 11:03

 * 一个类可能有很多成员变量
 * 这个类用来封装成员变量
 */
@Data
public class PropertyValues {
    /**
     *  这里也用final修饰
     */
    private final List<PropertyValue> propertyValueList= new ArrayList<>();

    //TODO   这里可以进行判重操作
    public void addPropertyValue(PropertyValue propertyValue){
        String field = propertyValue.getName();
        for(PropertyValue propertyValue1 : propertyValueList){
            if(field.equals(propertyValue1.getName()))
                return ;
        }
        this.propertyValueList.add(propertyValue);
    }
}
