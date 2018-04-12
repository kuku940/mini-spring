## IOC - 控制反转
控制反转（Inversion of Control，IoC），是面向对象编程中的一种设计原则，可以用来减低计算机代码之间耦合度。  
最常见的方式叫做依赖注入(Dependency Injection，DI)。通过控制反转，对象在被创建的时候，
由一个调控系统内所有对象的外界实体，将其所依赖的对象的引用传递给它，也可以说是依赖注入对象。

1. 初始化IoC容器，读取配置文件；
2. 将配置文件转换为容器能够识别的数据结构BeanDefinition
    - 默认Bean标签解析
    - 自定义标签解析
3. 利用数据结构BeanDefinition依次实例化对象Bean
4. 注入对象之间的依赖关系
    - 根据类型
    - 根据名称


## AOP - 面向切面编程
面向切面编程，实际上就是通过预编译或者**动态代理**的技术在**不修改源代码**的情况下给原来的**程序统一添加功能**的一种技术。
在很多方面都有应用，如：权限，缓存，日志，事务等等。

spring的AOP的实现主要步骤：
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
