package com.zju.myspring.aop.proxy;

import com.zju.myspring.aop.AdvisedSupport;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Proxy;

/**
 * @author zcz
 * @CreateTime 2020/2/4 18:14
 */
@AllArgsConstructor
@Data
public abstract class AbstractAopProxy implements AopProxy {
    /**
     * 这里是private  会怎么样
     * protected ，子类就可以使用这个变量
     */
    protected  AdvisedSupport advised;

}
