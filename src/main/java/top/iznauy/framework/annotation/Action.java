package top.iznauy.framework.annotation;

import top.iznauy.framework.web.RequestMethod;

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
@Target(ElementType.METHOD)
public @interface Action {

    RequestMethod method() default RequestMethod.GET;

    String url();

}
