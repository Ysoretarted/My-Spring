package com.zju.myspring.aop;

/**
 * @author zcz
 * @CreateTime 2020/2/6 12:52
 */
public interface PointcutAdvisor extends Advisor{

    Pointcut getPointcut();
}
