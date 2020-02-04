package com.zju.myspring.aop;

/**
 * @author zcz
 * @CreateTime 2020/2/4 21:20
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * 02-04
 */
@Data
@AllArgsConstructor
public class ReflectiveMethodInvocation implements MethodInvocation {

    // 某个对象实例
    private Object target;

    //改对象实例的某个方法
    private Method method;

    //对应的方法所包含的参数  可能有多个，用数组，而且参数的类型也不确定
    private Object[] arguments;


    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return arguments;
    }

    @Override
    public Object proceed() throws Throwable {
        //通过反射调用方法
        /**
         * 目前已经知道方法
         * 还需知道调用方法的实体对象，以及该方法所需的参数
         * p.say("hello"); 和方法调用一样，只是通过反射的形式
         */
        return method.invoke(target,arguments);
    }

    @Override
    public Object getThis() {
        return target;
    }

    @Override
    public AccessibleObject getStaticPart() {
        System.out.println(getClass() + "------  getStaticPart方法");
        return method;
    }
}
