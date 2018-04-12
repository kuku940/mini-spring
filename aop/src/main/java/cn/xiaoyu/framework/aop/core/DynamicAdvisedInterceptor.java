package cn.xiaoyu.framework.aop.core;

import cn.xiaoyu.framework.aop.advisor.TargetSource;
import cn.xiaoyu.framework.aop.interceptor.AopMethodInterceptor;
import cn.xiaoyu.framework.aop.invocation.CglibMethodInvocation;
import cn.xiaoyu.framework.aop.invocation.MethodInvocation;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author Roin_zhang
 * @date 2018/4/11 6:11
 */
public class DynamicAdvisedInterceptor implements MethodInterceptor {
    protected final List<AopMethodInterceptor> interceptorList;
    protected final TargetSource targetSource;

    public DynamicAdvisedInterceptor(List<AopMethodInterceptor> interceptorList, TargetSource targetSource) {
        this.interceptorList = interceptorList;
        this.targetSource = targetSource;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        MethodInvocation invocation = new CglibMethodInvocation(o,
                targetSource.getTargetObject(), method, objects, interceptorList, methodProxy);
        return invocation.proceed();
    }
}
