package com.zju.myspring.aop.proxy;

import com.zju.myspring.aop.AdvisedSupport;
import com.zju.myspring.aop.ReflectiveMethodInvocation;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * @author zcz
 * @CreateTime 2020/2/4 14:08
 */
public class JdkDynamicAopAopProxy extends AbstractAopProxy implements InvocationHandler {

    public JdkDynamicAopAopProxy(AdvisedSupport advised){
        super(advised);
    }

    /**
     * TODO  第三个参数  this::invoke  (自动弹出) ::的用法 ？？
     * @return
     */
    @Override
    public Object getProxy( ) {
        return Proxy.newProxyInstance(getClass().getClassLoader(),advised.getTargetSource().getTargetInterfaces(),this);
    }

    //02-04
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是invoke方法，我被调用了");

        MethodInterceptor methodInterceptor = advised.getMethodInterceptor();

        /**
         * TODO
         * advised.getTargetSource().getTargetClass()
         * advised.getTargetSource().getTarget().getClass()
         * 这两个是一样的吗
         */
        if(advised.getMethodMatcher() != null
            && advised.getMethodMatcher().matches(method,advised.getTargetSource().getTargetClass())){
            return methodInterceptor.invoke(new ReflectiveMethodInvocation(advised.getTargetSource().getTarget(), method,args));
        }
        else{
            System.out.println("等等我，还没实现");
            return method.invoke(advised.getTargetSource().getTarget(),args);
        }

    }
}
