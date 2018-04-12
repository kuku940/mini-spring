package cn.xiaoyu.framework.aop.test;

import cn.xiaoyu.framework.aop.core.AopApplicationContext;

/**
 * 主要测试方法
 *
 * @author Roin_zhang
 * @date 2018/4/12 9:23
 */
public class MainTest {
    public static void main(String[] args) throws Exception {
        AopApplicationContext aopApplicationContext = new AopApplicationContext("application.json");
        aopApplicationContext.init();

        TestService testService = (TestService) aopApplicationContext.getBean("testServiceProxy");
        testService.testMethod();
    }
}
