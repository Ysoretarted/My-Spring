package com.zju.myspring.ioc;

import lombok.Data;

/**
 * @author zcz
 * @CreateTime 2020/1/15 17:36
 */
@Data
public class HelloServiceImpl implements HelloService {
    private String attribute;

    @Override
    public void helloWorld() {
        System.out.println("这是HelloService的实现类");
        System.out.println(attribute);
    }
}
