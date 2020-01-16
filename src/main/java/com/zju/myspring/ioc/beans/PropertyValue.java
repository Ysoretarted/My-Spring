package com.zju.myspring.ioc.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * @author zcz
 * @CreateTime 2020/1/16 10:59

 * 这个类用来存储 一个类的成员 及其成员属性
 */
@AllArgsConstructor
@Data
public class PropertyValue {
    /**
     * 一个成员的名字总是可以用String来表示
     * //TODO 还要理解final起到的作用
     *   这个只是知道要设置成员的名字，这样可以通过反射  获得set+Name（setName())方法  或者 对应的 field 设置相应的属性
     */
    private final String name;

    /**
     * 该成员的类型不确定，故只能用Object
     */
    private final Object value;
}
