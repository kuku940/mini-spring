package cn.xiaoyu.framework.aop.invocation;

import cn.xiaoyu.framework.aop.interceptor.AopMethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author Roin_zhang
 * @date 2018/4/11 6:13
 */
public class CglibMethodInvocation extends ReflectionMethodInvocation {
    private MethodProxy methodProxy;

    public CglibMethodInvocation(Object proxy, Object target, Method method, Object[] arguments,
                                 List<AopMethodInterceptor> interceptorList, MethodProxy methodProxy) {
        super(proxy, target, method, arguments, interceptorList);
        this.methodProxy = methodProxy;
    }

    /**
     * 重写invokeOriginal方法，使用代理类来调用被增强的方法
     *
     * @return
     * @throws Throwable
     */
    @Override
    protected Object invokeOriginal() throws Throwable {
        return methodProxy.invoke(target, arguments);
    }
}
