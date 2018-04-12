package cn.xiaoyu.framework.aop.interceptor;

import cn.xiaoyu.framework.aop.advisor.AfterRunningAdvice;
import cn.xiaoyu.framework.aop.invocation.MethodInvocation;

/**
 * 后置拦截器
 *
 * @author Roin_zhang
 * @date 2018/4/11 6:12
 */
public class AfterRunningAdviceInterceptor implements AopMethodInterceptor {
    private AfterRunningAdvice advice;

    public AfterRunningAdviceInterceptor(AfterRunningAdvice advice) {
        this.advice = advice;
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        Object returnVal = mi.proceed();
        advice.after(returnVal, mi.getMethod(), mi.getArguments(), mi);
        return returnVal;
    }
}
