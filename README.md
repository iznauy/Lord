# Lord
Lord 是一个轻量级的 Java Web 框架。

项目的范围：

- IOC
  - 依赖管理，允许用户使用接口或者具体的类名。仅仅支持单例 Bean
  - 依赖注入，允许通过方法或者属性进行注入，需要用户手动指定注入哪个类。
  - Bean相关：需要 BeanDefinition 来指定对 Bean 进行一些基本的封装（因为 CGLIB 动态代理会修改 Class 信息），支持 BeanDefinitionProcessor。支持用户的钩子函数：InitializingBean，DisposableBean。
    - 目前只有 Component 注解
- AOP
  - 支持注解实现AOP，每个 AOP 类都能匹配到多个 Component，对其进行代理
  - 仅仅支持使用静态代理的方式来实现 AOP（具体的，是使用 BeanDefinitionProcessor，来修改 Bean 定义）
- MVC
  - 仅仅支持 RESTful 风格的应用。
  - 使用注解的方式来实现URL的映射
- 事务管理
  - 支持最原始的事务概念，但不支持事务传播
  - 对方法添加注解来实现事务
