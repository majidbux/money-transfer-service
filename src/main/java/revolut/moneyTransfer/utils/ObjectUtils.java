package revolut.moneyTransfer.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

public final class ObjectUtils {

    private ObjectUtils() {
    }

    /**
     * Method to copy properties from object to another object
     *
     * @param target  to object
     * @param source from object
     */
    public static void copyProperties(Object target, Object source) {
        try {
            BeanUtils.copyProperties(target, source);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
