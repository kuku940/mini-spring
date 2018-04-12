package cn.xiaoyu.framework.aop.utils;

import java.lang.reflect.Method;

/**
 * @author Roin_zhang
 * @date 2018/4/11 6:15
 */
public class ReflectionUtils {
    /**
     *
     * @param target    目标对象
     * @param method    方法
     * @param arguments 参数
     * @return
     */
    public static Object involeMethodUsrReflection(Object target, Method method, Object[] arguments) {
        method.setAccessible(true);

        try {
            return method.invoke(target, arguments);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
