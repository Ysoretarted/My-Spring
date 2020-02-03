package com.zju.myspring.ioc;

import com.zju.myspring.ioc.OutputService;

/**
 * @author zcz
 * @CreateTime 2020/2/3 13:39
 */
public class OutputServiceImpl implements OutputService {
    @Override
    public void printByReference(String text) {
        System.out.println("通过引用调用输出   " + text);
    }
}
