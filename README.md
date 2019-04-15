# Lord

[![LICENSE](https://img.shields.io/badge/license-Anti%20996-blue.svg)](https://github.com/996icu/996.ICU/blob/master/LICENSE)



Lord 是一个轻量级的 Java Web 框架。

项目的范围：

- IOC
  - 依赖管理：使用 Component 注解指定 Bean
  - 依赖注入，允许通过方法或者属性进行注入
  - Bean 生命周期：提供了 InitializingBean，DisposableBean 接口，允许用户在 Bean 创建期间对其进行进行动态修改
- AOP
  - 使用注解、模板方法的方式定义切面
- MVC
  - 支持 RESTful 风格的应用
  - 使用注解的方式来实现 URL 的映射
- 事务管理
  - 支持事务概念，不支持事务传播
  - 使用注解来定义需要事务管理的方法
  - 提供 `TransactionManager` 接口，用户通过实现该接口屏蔽不同数据库之间的差异。



目前**基本**能用，不过 Bug **比较多**，功能也在进一步的完善中。

## License

Anti 996-License-1.0