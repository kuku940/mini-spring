package cn.xiaoyu.framework.aop.core;

/**
 * @author Roin_zhang
 * @date 2018/4/11 6:10
 */
public interface AopProxy {
    Object getProxy();

    Object getProxy(ClassLoader classLoader);
}
