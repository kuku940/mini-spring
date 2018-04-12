package cn.xiaoyu.framework.aop.advisor;

import java.lang.reflect.Method;

/**
 * @author Roin_zhang
 * @date 2018/4/11 6:08
 */
public interface AfterRunningAdvice extends Advice {
    Object after(Object returnVal, Method method, Object[] args, Object target);
}
