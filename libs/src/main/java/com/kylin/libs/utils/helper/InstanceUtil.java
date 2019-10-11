package com.kylin.libs.utils.helper;

import java.lang.reflect.ParameterizedType;

/**
 * 泛型实例化工具
 * @author  Kylin
 * @date 2018/3/30.
 */
public class InstanceUtil {
    /**
     * 泛型实例化
     *
     * @param clazz
     * @param i
     * @param <T>
     * @return
     */
    public static <T> T instance(Object clazz, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (clazz.getClass().getGenericSuperclass()))
                    .getActualTypeArguments()[i]).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
