package cn.xiaoyu.framework.aop.adapter;

import cn.xiaoyu.framework.aop.advisor.Advisor;
import cn.xiaoyu.framework.aop.advisor.BeforeMethodAdvice;
import cn.xiaoyu.framework.aop.interceptor.AopMethodInterceptor;
import cn.xiaoyu.framework.aop.interceptor.BeforeMethodAdviceInterceptor;

/**
 * @author Roin_zhang
 * @date 2018/4/10 19:38
 */
public class BeforeMethodAdviceAdapter implements AdviceAdapter {
    private BeforeMethodAdviceAdapter() {
    }

    private static final BeforeMethodAdviceAdapter instance = new BeforeMethodAdviceAdapter();

    public static BeforeMethodAdviceAdapter getInstance() {
        return instance;
    }

    @Override
    public AopMethodInterceptor getInterceptor(Advisor advisor) {
        BeforeMethodAdvice advice = (BeforeMethodAdvice) advisor.getAdvice();
        return new BeforeMethodAdviceInterceptor(advice);
    }
}
