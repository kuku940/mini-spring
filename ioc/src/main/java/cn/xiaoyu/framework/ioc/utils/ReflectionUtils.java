package cn.xiaoyu.framework.ioc.utils;

import java.lang.reflect.Field;

/**
 * 通过反射来完成依赖注入
 *
 * @author Roin_zhang
 * @date 2018/4/9 16:28
 */
public class ReflectionUtils {
    /**
     * 通过Java的反射原理来完成对象的依赖注入
     *
     * @param field  对象属性
     * @param object 对象
     * @param value  对象属性的值
     * @throws IllegalAccessException
     */
    public static void injectField(Field field, Object object, Object value) throws IllegalAccessException {
        if (field != null) {
            field.setAccessible(true);
            field.set(object, value);
        }
    }
}
