package cn.xiaoyu.framework.ioc.utils;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * 负责处理对象的实例化 - CGLib工具包
 *
 * @author Roin_zhang
 * @date 2018/4/9 16:27
 */
public class BeanUtils {
    public static <T> T instanceByCglib(Class<T> clz, Constructor constructor, Object[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clz);
        enhancer.setCallback(NoOp.INSTANCE);

        if (constructor == null) {
            return (T) enhancer.create();
        } else {
            return (T) enhancer.create(constructor.getParameterTypes(), args);
        }
    }
}
