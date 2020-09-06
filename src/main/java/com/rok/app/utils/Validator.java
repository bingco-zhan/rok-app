package com.rok.app.utils;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Iterator;
import java.util.Set;
import java.util.function.BiConsumer;

/**
 * <h3>校验器
 * <p>
 * create: 2020/9/4 <br/>
 * email: bingco.zn@gmail.com <br/>
 *
 * @author zhan_bingcong
 * @version 1.0
 * @since jdk8+
 */
public class Validator {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static volatile Validator validator;
    private Set<ConstraintViolation<Object>> violations;

    private Validator() {
    }

    public static Validator instance() {
        if (validator == null) {
            synchronized (Validator.class) {
                if (validator == null) {
                    validator = new Validator();
                }
            }
        }
        return validator;
    }

    public static <T> Validator V(T record) {
        Validator validator = instance();
        validator.violations = validator.factory
                .getValidator()
                .validate(record);
        return validator;
    }

    public boolean onError(BiConsumer<String, ConstraintViolation<?>> consumer) {
        if (this.violations.size() == 0) {
            return false;
        }
        for (ConstraintViolation<Object> violation : this.violations) {
            Path propertyPath = violation.getPropertyPath();
            Iterator<Path.Node> iterator = propertyPath.iterator();
            if (iterator.hasNext()) {
                Path.Node next = iterator.next();
                consumer.accept(next.getName(), violation);
            }
        }
        return true;
    }
}
