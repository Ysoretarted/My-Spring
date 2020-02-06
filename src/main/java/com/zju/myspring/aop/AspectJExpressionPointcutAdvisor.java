package com.zju.myspring.aop;

import lombok.Data;
import org.aopalliance.aop.Advice;

/**
 * TODO   这个类是干嘛
 * @author zcz
 * @CreateTime 2020/2/6 12:53
 */
@Data
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    private AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();

    private Advice advice;


    public void setExpression(String expression){
        this.pointcut.setExpression(expression);
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }
}
