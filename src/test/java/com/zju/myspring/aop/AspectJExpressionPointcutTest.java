package com.zju.myspring.aop;

import com.zju.myspring.ioc.HelloServiceImpl;
import com.zju.myspring.ioc.OutputService;
import org.junit.Assert;
import org.junit.Test;

/**
 * step-8 的测试类
 * @author zcz
 * @CreateTime 2020/2/5 13:35
 */
public class AspectJExpressionPointcutTest {
    // TODOx  结果为false
    // myspring.. 就可以了
    @Test
    public void testMethodInterceptor() throws Exception{
        String expression = "execution(* com.zju.myspring..*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matches = aspectJExpressionPointcut.getMethodMatcher().matches(HelloServiceImpl.class.getDeclaredMethod("helloWorld"),HelloServiceImpl.class);
        Assert.assertTrue(matches);
    }

    @Test
    public void testClassFilter(){
        String expression = "execution(* com.zju.myspring..*.*(..))";

        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);

        boolean matches = aspectJExpressionPointcut.getClassFilter().matches(OutputService.class);
        Assert.assertTrue(matches);
    }
}
