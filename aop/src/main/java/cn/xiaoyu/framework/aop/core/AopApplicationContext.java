package cn.xiaoyu.framework.aop.core;

import cn.xiaoyu.framework.aop.bean.AopBeanDefinition;
import cn.xiaoyu.framework.ioc.bean.BeanDefinition;
import cn.xiaoyu.framework.ioc.utils.ClassUtils;
import cn.xiaoyu.framework.ioc.utils.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.InputStream;
import java.util.List;

/**
 * @author Roin_zhang
 * @date 2018/4/11 6:10
 */
public class AopApplicationContext extends AopBeanFactoryImpl {
    private String fileName;

    public AopApplicationContext(String fileName) {
        this.fileName = fileName;
    }

    public void init() {
        loadFile();
    }

    private void loadFile() {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        List<AopBeanDefinition> beanDefinitions = JsonUtils.readValue(inputStream, new TypeReference<List<AopBeanDefinition>>() {
        });

        if (beanDefinitions != null && !beanDefinitions.isEmpty()) {
            for (AopBeanDefinition beanDefinition : beanDefinitions) {
                Class<?> clazz = ClassUtils.loadClass(beanDefinition.getClassName());
                if (clazz == ProxyFactoryBean.class) {
                    registerBean(beanDefinition.getName(), beanDefinition);
                } else {
                    registerBean(beanDefinition.getName(), (BeanDefinition) beanDefinition);
                }
            }
        }
    }
}
