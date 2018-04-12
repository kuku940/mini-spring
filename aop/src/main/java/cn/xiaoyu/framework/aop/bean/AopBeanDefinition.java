package cn.xiaoyu.framework.aop.bean;

import cn.xiaoyu.framework.ioc.bean.BeanDefinition;

import java.util.List;

/**
 * @author Roin_zhang
 * @date 2018/4/11 6:09
 */
public class AopBeanDefinition extends BeanDefinition {
    private String target;
    private List<String> interceptorNames;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public List<String> getInterceptorNames() {
        return interceptorNames;
    }

    public void setInterceptorNames(List<String> interceptorNames) {
        this.interceptorNames = interceptorNames;
    }
}
