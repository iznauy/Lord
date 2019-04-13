package top.iznauy.framework.context.resource;

import top.iznauy.framework.context.resource.Resource;

/**
 * Created on 13/04/2019.
 * Description:
 *
 * @author iznauy
 */
public interface ResourceAccessor {

    Resource loadResource(String descriptor);

}
