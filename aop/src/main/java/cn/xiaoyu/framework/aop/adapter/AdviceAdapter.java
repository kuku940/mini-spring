package cn.xiaoyu.framework.aop.adapter;

import cn.xiaoyu.framework.aop.advisor.Advisor;
import cn.xiaoyu.framework.aop.interceptor.AopMethodInterceptor;

/**
 * @author Roin_zhang
 * @date 2018/4/10 19:37
 */
public interface AdviceAdapter {
    AopMethodInterceptor getInterceptor(Advisor advisor);
}
