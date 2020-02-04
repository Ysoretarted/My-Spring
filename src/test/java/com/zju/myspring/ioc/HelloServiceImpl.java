package com.zju.myspring.ioc;

import lombok.Data;

/**
 * @author zcz
 * @CreateTime 2020/1/15 17:36
 */
public class HelloServiceImpl implements HelloService {
    private String attribute;


    private OutputServiceImpl outputService;
    @Override
    public void helloWorld() {
        System.out.println("这是HelloService的实现类");
        outputService.printByReference(attribute);
    }
}
