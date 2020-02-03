package com.zju.myspring.ioc.beans.reader;

import com.zju.myspring.BeanDefinition;
import com.zju.myspring.ioc.beans.io.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**TODo  作用？
 * @author zcz
 * @CreateTime 2020/1/19 16:04
 */
@Data
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {
    private Map<String, BeanDefinition>  registry;

    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(ResourceLoader resourceLoader){
        this.registry = new HashMap<>();
        this.resourceLoader = resourceLoader;
    }



}
