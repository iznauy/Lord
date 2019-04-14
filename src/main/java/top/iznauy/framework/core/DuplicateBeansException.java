package top.iznauy.framework.core;

/**
 * Created on 13/04/2019.
 * Description: 某一个接口要求注入bean，系统中有个实现该接口的类，但是没有指明注入哪一个
 *
 * @author iznauy
 */
public class DuplicateBeansException extends Exception {

    public DuplicateBeansException(String msg) {
        super(msg);
    }

}
