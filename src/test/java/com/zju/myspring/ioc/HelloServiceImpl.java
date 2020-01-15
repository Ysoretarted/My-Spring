package com.zju.myspring.ioc;

/**
 * @author zcz
 * @CreateTime 2020/1/15 17:36
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public void helloWorld() {
        System.out.println("这是HelloService的实现类");
    }
}
