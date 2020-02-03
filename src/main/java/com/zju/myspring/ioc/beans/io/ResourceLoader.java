package com.zju.myspring.ioc.beans.io;

import java.net.URL;

/**
 * @author zcz
 * @CreateTime 2020/1/19 10:21
 */
public class ResourceLoader {
    /**
     *  TODOxx   getClassLoader()
     * @return
     */

    public Resource getResource(String location){
        URL resource = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(resource);
    }
}
