package cn.xiaoyu.framework.aop.advisor;

/**
 * 描述切面的数据结构
 *
 * @author Roin_zhang
 * @date 2018/4/10 19:39
 */
public class Advisor {
    // 通知
    private Advice advice;
    // 切入点
    private Pointcut pointcut;

    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public Pointcut getPointcut() {
        return pointcut;
    }

    public void setPointcut(Pointcut pointcut) {
        this.pointcut = pointcut;
    }
}
