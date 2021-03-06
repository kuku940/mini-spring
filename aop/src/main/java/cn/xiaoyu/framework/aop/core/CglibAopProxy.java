package cn.xiaoyu.framework.aop.core;

import cn.xiaoyu.framework.aop.advisor.AdvisedSupport;
import cn.xiaoyu.framework.ioc.utils.ClassUtils;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;

/**
 * 使用cglib生成代理类
 *
 * @author Roin_zhang
 * @date 2018/4/11 6:11
 */
public class CglibAopProxy implements AopProxy {
    private AdvisedSupport advisedSupport;
    private Object[] constructorArgs;
    private Class<?>[] constructorArgTypes;

    public CglibAopProxy(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    @Override
    public Object getProxy() {
        return getProxy(null);
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        Class<?> rootClass = advisedSupport.getTargetSource().getTargetClass();
        if (classLoader == null) {
            classLoader = ClassUtils.getDefaultClassLoader();
        }
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(rootClass.getSuperclass());

        // 增加拦截器的核心方法
        Callback callback = getCallBack(advisedSupport);
        enhancer.setCallback(callback);
        enhancer.setClassLoader(classLoader);
        if (constructorArgs != null && constructorArgs.length > 0) {
            return enhancer.create(constructorArgTypes, constructorArgs);
        }
        return enhancer.create();
    }

    private Callback getCallBack(AdvisedSupport advisedSupport) {
        return new DynamicAdvisedInterceptor(advisedSupport.getList(), advisedSupport.getTargetSource());
    }

    public AdvisedSupport getAdvisedSupport() {
        return advisedSupport;
    }

    public void setAdvisedSupport(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object[] getConstructorArgs() {
        return constructorArgs;
    }

    public void setConstructorArgs(Object[] constructorArgs) {
        this.constructorArgs = constructorArgs;
    }

    public Class<?>[] getConstructorArgTypes() {
        return constructorArgTypes;
    }

    public void setConstructorArgTypes(Class<?>[] constructorArgTypes) {
        this.constructorArgTypes = constructorArgTypes;
    }
}
