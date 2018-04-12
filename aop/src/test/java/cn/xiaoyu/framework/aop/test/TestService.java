package cn.xiaoyu.framework.aop.test;

/**
 * @author Roin_zhang
 * @date 2018/4/12 9:23
 */
public class TestService {
    public void testMethod() throws InterruptedException {
        System.out.println("this is a test method!");
        Thread.sleep(1000);
    }
}
