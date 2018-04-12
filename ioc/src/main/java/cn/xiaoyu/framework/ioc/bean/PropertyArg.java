package cn.xiaoyu.framework.ioc.bean;

/**
 * @author Roin_zhang
 * @date 2018/4/9 16:26
 */
public class PropertyArg {
    private String name;
    private String value;
    private String typeName;
    private String ref;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}
