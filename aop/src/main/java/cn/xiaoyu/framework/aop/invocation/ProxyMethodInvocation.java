package cn.xiaoyu.framework.aop.invocation;

/**
 * 代理方法的调用
 *
 * @author Roin_zhang
 * @date 2018/4/11 6:14
 */
public interface ProxyMethodInvocation extends MethodInvocation {
    /**
     * 获取代理的方法
     *
     * @return
     */
    Object getProxy();
}
