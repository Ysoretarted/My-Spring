package com.zju.myspring;

/**
 * @author zcz
 * @CreateTime 2020/2/3 13:20
 */

import lombok.Data;

/**
 * 保存bean之间的引用
 */
@Data
public class BeanReference {
    /**
     * bean 的名称
     */
    private String name;

    /**
     * bean的实例
     */
    private Object bean;
}
