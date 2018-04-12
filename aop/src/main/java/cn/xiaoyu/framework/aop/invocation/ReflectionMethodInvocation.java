package cn.xiaoyu.framework.aop.invocation;

import cn.xiaoyu.framework.aop.interceptor.AopMethodInterceptor;
import cn.xiaoyu.framework.aop.utils.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author Roin_zhang
 * @date 2018/4/11 6:14
 */
public class ReflectionMethodInvocation implements ProxyMethodInvocation {
    protected final Object proxy;
    protected final Object target;
    protected final Method method;
    protected Object[] arguments = new Object[0];

    // 存储所有的拦截器
    protected final List<AopMethodInterceptor> interceptorList;
    private int currentInterceptorIndex = -1;

    public ReflectionMethodInvocation(Object proxy, Object target, Method method, Object[] arguments, List<AopMethodInterceptor> interceptorList) {
        this.proxy = proxy;
        this.target = target;
        this.method = method;
        this.arguments = arguments;
        this.interceptorList = interceptorList;
    }

    @Override
    public Object getProxy() {
        return proxy;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return arguments;
    }

    @Override
    public Object proceed() throws Throwable {
        // 执行完所有的拦截器后，执行目标方法
        if (currentInterceptorIndex == this.interceptorList.size() - 1) {
            return invokeOriginal();
        }

        // 迭代的执行拦截器，回顾上面的讲解，我们实现的拦截都会执行im.proceed()
        // 实际上又会调用这个方法，实现了一个递归调用，直到执行完所有的拦截器
        AopMethodInterceptor interceptor = interceptorList.get(++currentInterceptorIndex);
        return interceptor.invoke(this);
    }

    protected Object invokeOriginal() throws Throwable {
        return ReflectionUtils.involeMethodUsrReflection(target, method, arguments);
    }
}
