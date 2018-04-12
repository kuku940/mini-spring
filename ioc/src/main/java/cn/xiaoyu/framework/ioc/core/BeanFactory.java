package cn.xiaoyu.framework.ioc.core;

/**
 * @author Roin_zhang
 * @date 2018/4/9 16:26
 */
public interface BeanFactory {
    /**
     * 通过BeanName注入
     *
     * @param name
     * @return
     * @throws Exception
     */
    Object getBean(String name) throws Exception;
}
