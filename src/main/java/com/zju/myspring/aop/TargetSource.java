package com.zju.myspring.aop;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zcz
 * @CreateTime 2020/2/4 13:56
 */
@Data
public class TargetSource {

    // 类的对象实例
    private Object target;

    // 类的Class对象
    private Class<?> targetClass;

    //类的接口
    private Class<?>[] targetInterfaces;

    /**  用...  这样也行*/
    public TargetSource(Object target, Class<?> targetClass, Class<?>... targetInterfaces) {
        this.target = target;
        this.targetClass = targetClass;
        this.targetInterfaces = targetInterfaces;
    }

}
