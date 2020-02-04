package com.zju.myspring.aop;


import java.lang.reflect.Method;

/**
 * @author zcz
 * @CreateTime 2020/2/4 14:19
 */
public interface MethodMatcher {
    boolean matches(Method method, Class targetClass);
}
