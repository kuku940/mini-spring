package cn.xiaoyu.framework.aop.test;

import cn.xiaoyu.framework.aop.advisor.BeforeMethodAdvice;

import java.lang.reflect.Method;

/**
 * @author Roin_zhang
 * @date 2018/4/11 16:41
 */
public class StartTimeBeforeMethod implements BeforeMethodAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) {
        long startTime = System.currentTimeMillis();
        System.out.println("开始计时");
        ThreadLocalUtils.set(startTime);
    }
}
