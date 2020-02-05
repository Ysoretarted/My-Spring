package com.zju.myspring.aop;

/**
 * @author zcz
 * @CreateTime 2020/2/5 12:15
 */
public interface Pointcut {
    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();
}
