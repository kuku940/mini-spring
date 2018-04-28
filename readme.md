## IoC - 控制反转
控制反转（Inversion of Control，IoC），是面向对象编程中的一种设计原则，可以用来减低计算机代码之间耦合度。  
最常见的方式叫做依赖注入(Dependency Injection，DI)。通过控制反转，对象在被创建的时候，
由一个调控系统内所有对象的外界实体，将其所依赖的对象的引用传递给它，也可以说是依赖注入对象。

### IoC容器初始化 - BeanDefinition解析和注册
1. 初始化IoC容器，读取配置文件 -> BeanDefinition解析和注册；
2. 将配置文件转换为容器能够识别的数据结构BeanDefinition
    - 默认Bean标签解析: import, bean, beans, alias
    - 自定义标签解析: aop, context, tx
3. 注册BeanDefinition到beanDefinitionMap中
 
### Bean加载
1. 利用BeanDefinition实例化Bean对象
2. 解决循环依赖问题，提早曝光Bean对象
3. 属性注入
    - 根据类型自动注入 - **byType**
    - 根据名称自动注入 - **byName**
4. 完成创建，根据scope创建Bean


## AOP - 面向切面编程
面向切面编程，实际上就是通过预编译或者**动态代理**的技术在**不修改源代码**的情况下给原来的**程序统一添加功能**的一种技术。
在很多方面都有应用，如：权限，缓存，日志，事务等等。

### AOP实现
1. 在Bean初始化时，会调用后AbstractAutoProxyCreator.postProcessAfterInitialization()后处理器，
判断当前Bean是否存在增强方法，如果存在则创建代理；
2.提取所有的增强器，寻找匹配当前Bean的增强器，并对匹配的增强器进行排序
    - 遍历所有的Bean，对有AspectJ注释的类进行增强器提取，并加入缓存[Before, After, Around, AfterReturning, AfterThrowing]
    - 寻找所有增强器中适用于当前class的增强器
    - 对增强器进行排序
3. 构建ProxyFactory，添加代理接口，封装增强器，设置要代理的类，最后创建代理
    - JDK动态代理[spring的默认实现]
    - CGLib动态代理[proxy-target-class配置为true]

        
    public AopProxy createAopProxy(AdvisedSupport config) throws AopConfigException {
         //optimize：用来控制通过CGLib创建的代理是否使用激进的优化策略；
         //proxyTargetClass：目标类本身被代理，而不是目标类的接口
         //hasNoUserSuppliedProxyInterfaces： 是否存在代理接口
        if (config.isOptimize() || config.isProxyTargetClass() || hasNoUserSuppliedProxyInterfaces(config)) {
            Class targetClass = config.getTargetClass();
            if (targetClass == null) {
                throw new AopConfigException("TargetSource cannot determine target class: " +
                        "Either an interface or a target is required for proxy creation.");
            }
            if (targetClass.isInterface()) {
                return new JdkDynamicAopProxy(config);
            }
            return CglibProxyFactory.createCglibProxy(config);
        } else {
            return new JdkDynamicAopProxy(config);
        }
    }
    
4. JDK动态代理
    - 获取拦截器
    - 判断拦截器链是否为null，如果是空的话直接调用切点方法
    - 如果拦截器链不为空的话，使用ReflectiveMethodInvocation类封装，把拦截器方法都封装在里面
    - 然后使用ReflectiveMethodInvocation.proceed()实现拦截器的逐一调用
    
   CGLib动态代理和JDK类似都是创建方法调用链，CGLib创建CglibMethodInvocation。
    

### 自己实现AOP的主要步骤：
1. 初始化AOP容器；
2. 读取配置文件；
3. 将配置文件装换为AOP能够识别的数据结构– **Advisor**。Advisor对象中包含了两个重要的数据结构：Advice和Pointcut。  
    两个数据结的组合就是“在哪里，干什么”。这样Advisor就包含了”在哪里干什么“的信息，就能够全面的描述切面了。
    - **Advice** - 通知，描述切面的行为；
    - **pointcut** - 切入点，描述切面的位置。  
      
4. Spring 将这个 Advisor 转换成自己能够识别的数据结构 – AdvicedSupport。Spirng 动态的将这些方法拦截器织入到对应的方法；
5. 生成动态代理代理；
6. 提供调用，在使用的时候，调用方调用的就是代理方法。也就是已经织入了增强方法的方法。

### 包的作用
- `invocation` 描述的就是一个方法的调用。注意这里指的是“方法的调用”，而不是调用这个动作；
- `interceptor` 拦截器，拦截器的拦截的目标就是`incation`包中里面的调用；
- `advisor` 用来描述切面的数据结构
- `adapter` 适配器方法，将`advice`包里的对象适配为`interceptor`
- `bean` 描述我们json配置的对象
- `core` 核心逻辑

`adaper`将`advisor`适配为`interceptor`去拦截`invoaction`。

## 参考
[徒手撸框架-实现IoC](https://www.xilidou.com/2018/01/08/spring-ioc/)  
[极简Spring框架-浅析循环依赖](http://heeexy.com/2018/01/28/IoC/)  
[徒手撸框架-实现Aop](https://www.xilidou.com/2018/01/13/spring-aop/)  
