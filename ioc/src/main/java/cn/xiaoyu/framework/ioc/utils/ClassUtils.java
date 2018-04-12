package cn.xiaoyu.framework.ioc.utils;

/**
 * 负责加载Java类
 *
 * @author Roin_zhang
 * @date 2018/4/9 16:28
 */
public class ClassUtils {
    public static ClassLoader getDefaultClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 通过className来获取对象的class
     *
     * @param className
     * @return
     */
    public static Class loadClass(String className) {
        try {
            return getDefaultClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
