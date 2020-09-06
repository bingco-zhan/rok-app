package com.rok.app.utils;

import java.lang.reflect.Constructor;
import java.util.Arrays;

/**
 * <h3>断言类
 * <p>
 * create: 2020/9/4 <br/>
 * email: bingco.zn@gmail.com <br/>
 *
 * @author zhan_bingcong
 * @version 1.0
 * @since jdk8+
 */
public class Assert {
    /**
     * 非空断言
     *
     * @param target 目标对象
     * @param errMsg 错误信息
     */
    public static void notNull(Object target, String errMsg) {
        if (target == null) {
            throw new NullPointerException(errMsg);
        }
    }

    /**
     * 非空断言
     *
     * @param target 目标对象
     */
    public static void notNull(Object target) {
        Assert.notNull(target, "the object don't null!");
    }


    /**
     * 空参构造断言
     *
     * @param clazz 类模板
     * @param <T>   类型
     */
    public static <T> Constructor<T> NotArgumentConstructor(Class<T> clazz) {
        @SuppressWarnings("unchecked")
        Constructor<T>[] constructors = (Constructor<T>[]) clazz.getConstructors();
        if (constructors.length == 0) {
            throw new RuntimeException("not search empty argument constructor by class: " + clazz);
        }
        Constructor<T> constructor1 = Arrays.stream(constructors)
                .filter(constructor -> constructor.getParameterCount() == 0)
                .findFirst()
                .orElse(null);
        if (constructor1 == null) {
            throw new RuntimeException("not search empty argument constructor by class: " + clazz);
        }
        return constructor1;
    }

}
