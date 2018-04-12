package cn.xiaoyu.framework.ioc.bean;

import java.util.List;

/**
 * @author Roin_zhang
 * @date 2018/4/9 16:25
 */
public class BeanDefinition {
    private String name;
    private String className;
    private String interfaceName;
    // 构造函数的传参的列表contructorArg
    private List<ConstructorArg> constructorArgs;
    // 需要注入的参数列表
    private List<PropertyArg> propertyArgs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public List<ConstructorArg> getConstructorArgs() {
        return constructorArgs;
    }

    public void setConstructorArgs(List<ConstructorArg> constructorArgs) {
        this.constructorArgs = constructorArgs;
    }

    public List<PropertyArg> getPropertyArgs() {
        return propertyArgs;
    }

    public void setPropertyArgs(List<PropertyArg> propertyArgs) {
        this.propertyArgs = propertyArgs;
    }
}
