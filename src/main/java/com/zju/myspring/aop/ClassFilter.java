package com.zju.myspring.aop;

/**
 * 类的匹配器
 * @author zcz
 * @CreateTime 2020/2/5 12:14
 */
public interface ClassFilter {

    boolean matches(Class<?> targetClass);
}
