package com.zju.myspring.aop.factory;

import com.zju.myspring.aop.AdvisedSupport;
import com.zju.myspring.aop.proxy.AopProxy;

/**  代理工厂
 * @author zcz
 * @CreateTime 2020/2/6 13:09
 */
public class ProxyFactory extends AdvisedSupport implements AopProxy {

    @Override
    public Object getProxy() {
        // TOdo
        return null;
    }

    /**
     * 原来的是protected  final
     */
    /*private final AopProxy*/
}
