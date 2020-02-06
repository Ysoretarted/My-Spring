package com.zju.myspring.aop;

import org.aopalliance.aop.Advice;

/**
 * @author zcz
 * @CreateTime 2020/2/6 12:51
 */
public interface Advisor {

    // 获取通知时间
    Advice getAdvice();
}
