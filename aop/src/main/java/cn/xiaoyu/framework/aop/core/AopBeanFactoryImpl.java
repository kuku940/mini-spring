package cn.xiaoyu.framework.aop.core;

import cn.xiaoyu.framework.aop.adapter.AfterRunningAdviceAdapter;
import cn.xiaoyu.framework.aop.adapter.BeforeMethodAdviceAdapter;
import cn.xiaoyu.framework.aop.advisor.*;
import cn.xiaoyu.framework.aop.bean.AopBeanDefinition;
import cn.xiaoyu.framework.aop.interceptor.AopMethodInterceptor;
import cn.xiaoyu.framework.ioc.core.BeanFactoryImpl;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 产生代理对象的工厂类
 *
 * @author Roin_zhang
 * @date 2018/4/11 6:10
 */
public class AopBeanFactoryImpl extends BeanFactoryImpl {
    private static final ConcurrentHashMap<String, AopBeanDefinition> aopBeanDefinitionMap = new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<String, Object> aopBeanMap = new ConcurrentHashMap<>();

    @Override
    public Object getBean(String name) throws Exception {
        Object aopBean = aopBeanMap.get(name);

        if (aopBean != null) {
            return aopBean;
        }

        if (aopBeanDefinitionMap.containsKey(name)) {
            AopBeanDefinition aopBeanDefinition = aopBeanDefinitionMap.get(name);
            AdvisedSupport advisedSupport = getAdvisedSupport(aopBeanDefinition);

            aopBean = new CglibAopProxy(advisedSupport).getProxy();
            aopBeanMap.put(name, aopBean);
            return aopBean;
        }

        return super.getBean(name);
    }

    protected void registerBean(String name, AopBeanDefinition aopBeanDefinition) {
        aopBeanDefinitionMap.put(name, aopBeanDefinition);
    }

    private AdvisedSupport getAdvisedSupport(AopBeanDefinition aopBeanDefinition) throws Exception {
        AdvisedSupport advisedSupport = new AdvisedSupport();
        List<String> interceptorNames = aopBeanDefinition.getInterceptorNames();
        if (interceptorNames != null && !interceptorNames.isEmpty()) {
            for (String interceptorName : interceptorNames) {
                Advice advice = (Advice) getBean(interceptorName);

                Advisor advisor = new Advisor();
                advisor.setAdvice(advice);

                // 添加前置拦截器
                if (advice instanceof BeforeMethodAdvice) {
                    AopMethodInterceptor interceptor = BeforeMethodAdviceAdapter.getInstance().getInterceptor(advisor);
                    advisedSupport.addAopMethodInterceptor(interceptor);
                }
                // 添加后置拦截器
                if (advice instanceof AfterRunningAdvice) {
                    AopMethodInterceptor interceptor = AfterRunningAdviceAdapter.getInstance().getInterceptor(advisor);
                    advisedSupport.addAopMethodInterceptor(interceptor);
                }
            }
        }

        TargetSource targetSource = new TargetSource();
        Object object = getBean(aopBeanDefinition.getTarget());
        targetSource.setTargetClass(object.getClass());
        targetSource.setTargetObject(object);
        advisedSupport.setTargetSource(targetSource);
        return advisedSupport;
    }
}
