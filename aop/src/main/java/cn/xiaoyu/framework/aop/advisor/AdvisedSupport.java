package cn.xiaoyu.framework.aop.advisor;

import cn.xiaoyu.framework.aop.interceptor.AopMethodInterceptor;

import java.util.LinkedList;
import java.util.List;

/**
 * 对于某些数据增加那些拦截器
 *
 * @author Roin_zhang
 * @date 2018/4/10 19:39
 */
public class AdvisedSupport extends Advisor {
    /**
     * 目标对象
     */
    private TargetSource targetSource;
    /**
     * 拦截器列表
     */
    private List<AopMethodInterceptor> list = new LinkedList<>();

    public void addAopMethodInterceptor(AopMethodInterceptor interceptor) {
        list.add(interceptor);
    }

    public void addAopMethodInterceptors(List<AopMethodInterceptor> interceptorList) {
        list.addAll(interceptorList);
    }

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public List<AopMethodInterceptor> getList() {
        return list;
    }

    public void setList(List<AopMethodInterceptor> list) {
        this.list = list;
    }
}
