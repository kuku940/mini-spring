package cn.xiaoyu.framework.aop.interceptor;

import cn.xiaoyu.framework.aop.invocation.MethodInvocation;

/**
 * AOP容器所有拦截器都要实现的接口
 *
 * @author Roin_zhang
 * @date 2018/4/11 6:13
 */
public interface AopMethodInterceptor {
    Object invoke(MethodInvocation mi) throws Throwable;
}
