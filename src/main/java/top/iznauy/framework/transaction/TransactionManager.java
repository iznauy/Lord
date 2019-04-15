package top.iznauy.framework.transaction;

/**
 * Created on 15/04/2019.
 * Description:
 *
 * @author iznauy
 */
public interface TransactionManager {

    void beginTransaction();

    void commitTransaction();

    void rollbackTransaction();

}
