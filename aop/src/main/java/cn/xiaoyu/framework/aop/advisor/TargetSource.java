package cn.xiaoyu.framework.aop.advisor;

/**
 * @author Roin_zhang
 * @date 2018/4/11 6:09
 */
public class TargetSource {
    private Class<?> targetClass;

    private Object targetObject;

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    public Object getTargetObject() {
        return targetObject;
    }

    public void setTargetObject(Object targetObject) {
        this.targetObject = targetObject;
    }
}
