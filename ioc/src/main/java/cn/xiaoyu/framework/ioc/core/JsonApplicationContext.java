package cn.xiaoyu.framework.ioc.core;

import cn.xiaoyu.framework.ioc.bean.BeanDefinition;
import cn.xiaoyu.framework.ioc.utils.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.InputStream;
import java.util.List;

/**
 * 此容器的作用是读取配置文件，将配置文件转换为容器能够理解的BeanDefinition，
 * 然后使用registerBean方法，注册BeanDefinition
 *
 * @author Roin_zhang
 * @date 2018/4/9 16:27
 */
public class JsonApplicationContext extends BeanFactoryImpl {
    private String fileName;

    public JsonApplicationContext(String fileName) {
        this.fileName = fileName;
    }

    public void init() {
        loadFile();
    }

    /**
     * 从配置文件中解析BeanDefinition数据结构
     */
    private void loadFile() {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

        List<BeanDefinition> beanDefinitions = JsonUtils.readValue(is, new TypeReference<List<BeanDefinition>>() {
        });

        if (beanDefinitions != null && !beanDefinitions.isEmpty()) {
            for (BeanDefinition beanDefinition : beanDefinitions) {
                registerBean(beanDefinition.getName(), beanDefinition);
                System.out.println("注册BeanDefinition: " + beanDefinition.getName());
            }

        } else {
            System.out.println("未注册任何BeanDefinition");
        }
    }

}
