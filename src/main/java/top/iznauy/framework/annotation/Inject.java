package top.iznauy.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created on 12/04/2019.
 * Description:
 *
 * @author iznauy
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface Inject {

    /**
     * 接口注入的时候，指定注入的 bean 的具体名称
     * 本框架仅仅支持属性注入，没有指明的话，而且提供了多个实现类，就会报错
     * @return
     */
    Class<?> target() default Inject.class;

}
