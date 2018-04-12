package cn.xiaoyu.framework.aop.interceptor;

import cn.xiaoyu.framework.aop.advisor.BeforeMethodAdvice;
import cn.xiaoyu.framework.aop.invocation.MethodInvocation;

/**
 * 前置拦截器
 *
 * @author Roin_zhang
 * @date 2018/4/11 6:13
 */
public class BeforeMethodAdviceInterceptor implements AopMethodInterceptor {
    private BeforeMethodAdvice advice;

    public BeforeMethodAdviceInterceptor(BeforeMethodAdvice advice) {
        this.advice = advice;
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        advice.before(mi.getMethod(), mi.getArguments(), mi);
        // 执行原有的方法
        return mi.proceed();
    }
}
