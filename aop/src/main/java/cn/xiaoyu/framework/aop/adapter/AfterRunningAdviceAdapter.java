package cn.xiaoyu.framework.aop.adapter;

import cn.xiaoyu.framework.aop.advisor.Advisor;
import cn.xiaoyu.framework.aop.advisor.AfterRunningAdvice;
import cn.xiaoyu.framework.aop.interceptor.AfterRunningAdviceInterceptor;
import cn.xiaoyu.framework.aop.interceptor.AopMethodInterceptor;

/**
 * @author Roin_zhang
 * @date 2018/4/10 19:38
 */
public class AfterRunningAdviceAdapter implements AdviceAdapter {
    private AfterRunningAdviceAdapter() {

    }

    private static final AfterRunningAdviceAdapter instance = new AfterRunningAdviceAdapter();

    public static AfterRunningAdviceAdapter getInstance() {
        return instance;
    }

    @Override
    public AopMethodInterceptor getInterceptor(Advisor advisor) {
        AfterRunningAdvice advice = (AfterRunningAdvice) advisor.getAdvice();
        return new AfterRunningAdviceInterceptor(advice);
    }
}
