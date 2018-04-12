package cn.xiaoyu.framework.ioc.core;

import cn.xiaoyu.framework.ioc.bean.BeanDefinition;
import cn.xiaoyu.framework.ioc.bean.ConstructorArg;
import cn.xiaoyu.framework.ioc.bean.PropertyArg;
import cn.xiaoyu.framework.ioc.utils.BeanUtils;
import cn.xiaoyu.framework.ioc.utils.ClassUtils;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 通过BeanDefinition来实例化Bean
 * 1. 通过beanMap获取bean，如果有直接返回
 * 2. 没有，则通过beanMap判断是否存在对应的数据结构
 * 3. 存在对应的数据结构则通过beanDefinitionMap来获取对应的BeanDefinition
 * 4. 通过BeanDefinition对象来实例化bean
 * 5. 通过属性注入，将Bean中的其他属性注入
 *
 * @author Roin_zhang
 * @date 2018/4/9 16:27
 */
public class BeanFactoryImpl implements BeanFactory {

    public BeanFactoryImpl() {
    }

    // 保存beanName和实例化之后的对象
    private static final ConcurrentHashMap<String, Object> beanMap = new ConcurrentHashMap<>();
    // 存储的是对象名称和对象对应的数据结构的映射
    private static final ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    // 存储已经BeanDefinition的key的集合
    private static final Set<String> beanNameSet = Collections.synchronizedSet(new HashSet<>());
    // 循环依赖的解决 - 作为位置占用
    private final Map<String, Object> earlySingletonObjects = new HashMap<String, Object>(16);


    @Override
    public Object getBean(String name) throws Exception {
        // 查找对象是否已经实例化 - 如果已经被实例化的化，直接通过缓存中获取
        Object bean = beanMap.get(name);
        if (bean != null) {
            return bean;
        }

        Object earlyBean = earlySingletonObjects.get(name);
        if (earlyBean != null) {
            System.out.println("发生循环依赖的问题，提前返回尚未加载完成的Bean:" + name);
            return earlyBean;
        }

        // 如果没有实例化，那就需要调用createBean来创建对象
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        bean = createBean(beanDefinition);
        if (bean != null) {
            // 解决循环依赖的问题，在属性注入之前，先添加到map中
            earlySingletonObjects.put(name, bean);

            // 属性注入
            populateBean(bean, beanDefinition);

            // 再把对象存入Map中方便下次使用
            beanMap.put(name, bean);

            // 从早期单例map中移除
            earlySingletonObjects.remove(name);
        }
        return bean;
    }

    /**
     * 注册BeanDefinition对象
     *
     * @param name
     * @param beanDefinition
     */
    protected void registerBean(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
        beanNameSet.add(name);
    }

    /**
     * 创建Bean，分无参和有参两种情况
     *
     * @param beanDefinition
     * @return
     * @throws Exception
     */
    private Object createBean(BeanDefinition beanDefinition) throws Exception {
        String beanName = beanDefinition.getClassName();
        Class clazz = ClassUtils.loadClass(beanName);
        if (clazz == null) {
            throw new Exception("can not find bean by beanName");
        }

        List<ConstructorArg> constructorArgs = beanDefinition.getConstructorArgs();
        if (constructorArgs != null && !constructorArgs.isEmpty()) {
            List<Object> objects = new ArrayList<>();
            for (ConstructorArg constructorArg : constructorArgs) {
                objects.add(getBean(constructorArg.getRef()));
            }
            // 有参的构造方法
            return BeanUtils.instanceByCglib(clazz, clazz.getConstructor(), objects.toArray());
        } else {
            // 无参的构造方法
            return BeanUtils.instanceByCglib(clazz, null, null);
        }
    }

    /**
     * 属性注入
     *
     * @param bean
     */
    private void populateBean(Object bean, BeanDefinition beanDefinition) throws Exception {
        List<PropertyArg> propertyArgs = beanDefinition.getPropertyArgs();
        if (propertyArgs != null && !propertyArgs.isEmpty()) {
            // 遍历在Json中配置的属性
            for (PropertyArg propertyArg : propertyArgs) {
                String propertyName = propertyArg.getName();
                String value = propertyArg.getValue();
                String ref = propertyArg.getRef();

                Object injectValue = null;
                if (value != null) {
                    // 如果有值的话，那么直接赋值
                    injectValue = value;
                } else if (ref != null && !"".equals(ref)) {
                    // 如果是引用值的话，通过getBean的方式实现
                    injectValue = getBean(ref);
                }

                // 通过set方法来完成值的依赖注入
                Method method = getPropertySet(beanDefinition, propertyName, injectValue);
                method.invoke(bean, injectValue);
            }
        }

        // 通过反射的方式来完成值的依赖注入
//        Field[] fields = bean.getClass().getSuperclass().getDeclaredFields();
//        if (fields != null && fields.length > 0) {
//            for (Field field : fields) {
//                String beanName = field.getName();
//                beanName = StringUtils.uncapitalize(beanName);
//                if (beanNameSet.contains(field.getName())) {
//                    Object fieldBean = getBean(field.getName());
//                    if (fieldBean != null) {
//                        // 如果属性bean不为空的话，那么注入属性
//                        ReflectionUtils.injectField(field, bean, fieldBean);
//                    }
//                }
//            }
//        }
    }

    /**
     * 获取具体某个属性的setter方法
     * 此处做法比较简单粗暴
     * 实际上Spring在读取配置文件时就已经将各属性，方法，getter/setter都读取好了。
     * 在这就只需要调用BeanWrapper的方法来为属性赋值就可以了。
     */
    private Method getPropertySet(BeanDefinition beanDefinition, String propertyName, Object injectValue) throws ClassNotFoundException, NoSuchMethodException {
        Class beanClass = Class.forName(beanDefinition.getClassName());
        Class injectClazz = injectValue.getClass();
        Class supClass = injectValue.getClass().getSuperclass();
        if (supClass != null && supClass != Object.class) {
            injectClazz = supClass;
        }
        // 首字母大写
        propertyName = propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
        Method setter = beanClass.getMethod("set" + propertyName, injectClazz);
        return setter;
    }
}
