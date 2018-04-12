package cn.xiaoyu.framework.aop.invocation;

import java.lang.reflect.Method;

/**
 * 所有方法调用的接口
 *
 * @author Roin_zhang
 * @date 2018/4/11 6:14
 */
public interface MethodInvocation {
    /**
     * 获取方法本身
     *
     * @return
     */
    Method getMethod();

    /**
     * 获取方法的参数
     *
     * @return
     */
    Object[] getArguments();

    /**
     * 执行方法本身
     *
     * @return
     * @throws Throwable
     */
    Object proceed() throws Throwable;
}
