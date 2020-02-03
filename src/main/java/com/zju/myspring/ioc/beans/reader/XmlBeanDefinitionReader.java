package com.zju.myspring.ioc.beans.reader;

import com.zju.myspring.BeanDefinition;
import com.zju.myspring.BeanReference;
import com.zju.myspring.ioc.beans.PropertyValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import com.zju.myspring.ioc.beans.io.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;

/** TODo   这个类有点难写
 * @author zcz
 * @CreateTime 2020/1/19 16:17
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader{
    public XmlBeanDefinitionReader(ResourceLoader resourceLoader){
        super(resourceLoader);
    }

    /**
     * 这个方法是最大的函数， 一层层调用
     *    就是填充  Map<String, BeanDefinition>  registry
     * @param location
     * @throws Exception
     */
    @Override
    public void loadBeanDefinitions(String location) throws Exception {
        InputStream inputStream = getResourceLoader().getResource(location).getInputStream();
        doLoadBeanDefinitions(inputStream);
    }


    protected void doLoadBeanDefinitions(InputStream inputStream) throws Exception{
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        /**
         * .newDocument()  是调用DocumentBuilderImpl的方法 （该类 继承了DocumentBuilder）
         */
        Document document = documentBuilder.parse(inputStream);
        registerBeanDefinitions(document);

    }

    /**
     * 注册
     * @param document
     */
    protected void registerBeanDefinitions(Document document){
        Element root = document.getDocumentElement();
        parseBeanDefinitions(root);
    }

    /**
     * 解析
     * @param element
     */
    protected void parseBeanDefinitions(Element element){
        NodeList childNodes = element.getChildNodes();
        int len = childNodes.getLength();
        for(int i = 0; i < len; ++i){
            Node node = childNodes.item(i);
            if(node instanceof Element){
                Element ele = (Element) node;
                processBeanDefinitions(ele);
            }
        }
    }

    /**
     * 处理
     * @param element
     */
    protected void processBeanDefinitions(Element  element){
        String name = element.getAttribute("id");
        String className = element.getAttribute("class");

        System.out.println(name + "    "+ className);

        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName(className);

        /**
         * 填充属性
         */
        processProperty(element,beanDefinition);
        /**
         * put 进去  是为了获取（根据name 获取）
         *
         * 这里一定要注册
         */
        getRegistry().put(name, beanDefinition);
    }

    private void processProperty(Element element, BeanDefinition beanDefinition){
        NodeList propertyList = element.getElementsByTagName("property");
        for(int i = 0; i < propertyList.getLength(); ++i){
            /**
             * 这里的node 调试  显示  [property123: null] （123是根据配置文件里的）
             *   null（其实本身不为null），并不影响下面的程序
             */
            Node node = propertyList.item(i);
            //System.out.println(node);
            if(node instanceof Element){
                Element propertyEle = (Element) node;

                String name = propertyEle.getAttribute("name");
                String value = propertyEle.getAttribute("value");
                //System.out.println("ABCDEFG   " + name + value);
                /**
                 * 这里要加判断，因为第5步有 "ref"  value可能为空
                 */
                if(value != null && value.length() > 0)
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
                else{
                    String ref = propertyEle.getAttribute("ref");
                    if(null == ref || ref.length() == 0){
                        throw new IllegalArgumentException("Configuration problem: <property> element for property " + name +" must specify a ref or value");
                    }

                    BeanReference beanReference = new BeanReference();
                    beanReference.setName(ref);
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name,beanReference));
                }
            }
        }
    }
}
