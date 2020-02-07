package com.zju.myspring.aop.proxy;

import com.zju.myspring.aop.AdvisedSupport;
import com.zju.myspring.aop.ReflectiveMethodInvocation;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**   Cglib 代理方式
 * @author zcz
 * @CreateTime 2020/2/7 11:59
 */
public class Cglib2AopAopProxy extends AbstractAopProxy {


    public Cglib2AopAopProxy(AdvisedSupport advised){
        super(advised);
    }

    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();

        /**
         * 绑定要代理的类
         */
        enhancer.setSuperclass(advised.getTargetSource().getTargetClass());

        enhancer.setInterfaces(advised.getTargetSource().getTargetInterfaces());

        enhancer.setCallback(new DynamicAdvisedInterceptor(advised));
        Object enhanced = enhancer.create();
        return enhanced;
    }


    private static class DynamicAdvisedInterceptor implements MethodInterceptor{
        private AdvisedSupport advised;
        private org.aopalliance.intercept.MethodInterceptor delegatedMethodInterceptor;

        public DynamicAdvisedInterceptor(AdvisedSupport advised){
            this.advised = advised;
            delegatedMethodInterceptor = advised.getMethodInterceptor();
        }
        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            if(advised.getMethodMatcher() == null
                ||advised.getMethodMatcher().matches(method, advised.getTargetSource().getTargetClass())){
                Object result = delegatedMethodInterceptor.invoke(new CglibMethodInvocation(advised.getTargetSource().getTarget(),
                        method,args,proxy));
                return result;

            }
            Object anotherResult = new CglibMethodInvocation(advised.getTargetSource().getTarget(),
                    method,args,proxy).proceed();
            return anotherResult;
        }

    }

    /**
     * 跟方法有关
     */

    private static class CglibMethodInvocation extends ReflectiveMethodInvocation{
        private MethodProxy methodProxy;

        public CglibMethodInvocation(Object target, Method method,Object[] arguments, MethodProxy methodProxy){
            super(target, method, arguments);
            this.methodProxy = methodProxy;
        }

        @Override
        public Object proceed() throws Throwable {
            return this.methodProxy.invoke(this.target, this.arguments);
        }
    }

}
