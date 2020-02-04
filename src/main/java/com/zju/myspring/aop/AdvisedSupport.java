package com.zju.myspring.aop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * @author zcz
 * @CreateTime 2020/2/4 13:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvisedSupport {
    //原始对象的实例
    private TargetSource targetSource;

    // 方法拦截器
    private MethodInterceptor methodInterceptor;

    /**
     * TODO?
     * 方法匹配器？  干嘛用的
     */
    private MethodMatcher methodMatcher;
}
