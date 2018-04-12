package cn.xiaoyu.framework.aop.test;

import cn.xiaoyu.framework.aop.advisor.AfterRunningAdvice;

import java.lang.reflect.Method;

/**
 * @author Roin_zhang
 * @date 2018/4/11 17:08
 */
public class EndTimeAfterMethod implements AfterRunningAdvice {

    @Override
    public Object after(Object returnVal, Method method, Object[] args, Object target) {
        long endTime = System.currentTimeMillis();
        long startTime = ThreadLocalUtils.get();
        ThreadLocalUtils.remove();
        System.out.println("方法耗时：" + (endTime - startTime) + "ms");
        return returnVal;
    }
}
