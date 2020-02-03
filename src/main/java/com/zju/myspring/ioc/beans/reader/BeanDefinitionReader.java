package com.zju.myspring.ioc.beans.reader;

import java.io.IOException;

/**
 * @author zcz
 * @CreateTime 2020/1/16 20:29
 */
public interface BeanDefinitionReader {

    void loadBeanDefinitions(String location) throws Exception;
}
