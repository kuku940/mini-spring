package cn.xiaoyu.framework.aop.test;

/**
 * @author Roin_zhang
 * @date 2018/4/11 17:03
 */
public class ThreadLocalUtils {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /**
     * 获取线程变量
     *
     * @return
     */
    public static long get() {
        if (threadLocal == null) {
            throw new IllegalStateException("ThreadLocal Exception");
        }
        return threadLocal.get();
    }

    /**
     * 设置线程变量
     *
     * @return
     */
    public static void set(long startTime) {
        threadLocal.set(startTime);
    }

    /**
     * 移除线程变量
     */
    public static void remove() {
        threadLocal.remove();
    }
}
