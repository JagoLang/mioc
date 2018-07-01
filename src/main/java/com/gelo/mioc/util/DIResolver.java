package com.gelo.mioc.util;

import com.gelo.mioc.BeanStorage;
import com.gelo.mioc.annotation.Qualifier;
import com.gelo.mioc.exception.BeanCreationException;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;

/**
 * The type Di resolver.
 */
public final class DIResolver {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DIResolver.class);

    /**
     * Autowire t.
     *
     * @param <T>   the type parameter
     * @param clazz the clazz
     * @return the t
     */
    public static <T> T autowire(Class<T> clazz) {
        try {
            Constructor<?> mainConstructor = null;
            for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
                if (mainConstructor == null) {
                    mainConstructor = constructor;
                    continue;
                }

                if (constructor.getParameterCount() >= mainConstructor.getParameterCount()) {
                    mainConstructor = constructor;
                }
            }

            if (mainConstructor == null) {
                logger.error("No constructor configured for Dependency Injection");
                return null;
            }

            Parameter[] parameters = mainConstructor.getParameters();

            Class<?>[] parameterTypes = mainConstructor.getParameterTypes();

            Object[] injected = new Object[parameterTypes.length];

            for (int i = 0; i < parameterTypes.length; i++) {
                Qualifier qualifier = parameters[i].getAnnotation(Qualifier.class);

                if (qualifier == null) {
                    injected[i] = BeanStorage.get(parameterTypes[i]);
                } else {
                    injected[i] = BeanStorage.get(qualifier.value());
                }

            }

            mainConstructor.setAccessible(true);

            return (T) mainConstructor.newInstance(injected);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            BeanCreationException beanCreationException = new BeanCreationException("Error creating bean using Dependency Injecting");
            logger.error("Bean creation error: ", beanCreationException);
            throw beanCreationException;
        }
    }
}
