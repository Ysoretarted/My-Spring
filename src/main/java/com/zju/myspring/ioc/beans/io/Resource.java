package com.zju.myspring.ioc.beans.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zcz
 * @CreateTime 2020/1/19 9:38
 */
public interface Resource {

    InputStream getInputStream() throws IOException;
}
