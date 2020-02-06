package com.zju.myspring.aop;

import com.zju.myspring.aop.factory.ProxyFactory;
import com.zju.myspring.ioc.beans.BeanPostProcessor;
import com.zju.myspring.ioc.beans.factory.AbstractBeanFactory;
import com.zju.myspring.ioc.beans.factory.BeanFactory;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.List;

/**
 * 从字面上看是  XX代理 创造器
 *
 * @author zcz
 * @CreateTime 2020/2/6 10:53
 */
public class AspectJAwareAdvisorAutoProxyCreator implements BeanFactoryAware, BeanPostProcessor {

    private AbstractBeanFactory beanFactory;


    @Override
    public Object postProcessorBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    @Override
    public Object postProcessorAfterInitialization(Object bean, String beanName) throws Exception {
        if (bean instanceof AspectJExpressionPointcutAdvisor) {
            return bean;
        }

        if (bean instanceof MethodInterceptor) {
            return bean;
        }

        List<AspectJExpressionPointcutAdvisor> advisors =
                beanFactory.getBeansForType(AspectJExpressionPointcutAdvisor.class);

        for (AspectJExpressionPointcutAdvisor advisor : advisors) {

            if (advisor.getPointcut().getClassFilter().matches(bean.getClass())) {
                // 以下操作都是填充 adviseSupport的成员属性
                ProxyFactory advisedSupport = new ProxyFactory();
                advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
                advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());

                TargetSource targetSource = new TargetSource(bean, bean.getClass(), bean.getClass().getInterfaces());
                advisedSupport.setTargetSource(targetSource);

                /**
                 * 这里说明 getProxy 方法 和 ProxyFactory的三个成员有关系
                 */
                return advisedSupport.getProxy();
            }
        }
        return bean;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = (AbstractBeanFactory) beanFactory;
    }
}
