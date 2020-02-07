package com.zju.myspring.aop.factory;

import com.zju.myspring.aop.AdvisedSupport;
import com.zju.myspring.aop.proxy.AopProxy;
import com.zju.myspring.aop.proxy.Cglib2AopAopProxy;

/**  代理工厂
 * 用于根据TargetSource类型自动创建代理
 * @author zcz
 * @CreateTime 2020/2/6 13:09
 */
public class ProxyFactory extends AdvisedSupport implements AopProxy {

    @Override
    public Object getProxy() {
        // TOdo
        return createAopProxy().getProxy();
    }

    /**
     * 原来的是protected  final
     */
    private final AopProxy createAopProxy(){
        return new Cglib2AopAopProxy(this);
    }
}
