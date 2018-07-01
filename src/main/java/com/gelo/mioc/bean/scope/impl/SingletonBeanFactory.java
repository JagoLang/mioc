package com.gelo.mioc.bean.scope.impl;


import com.gelo.mioc.bean.scope.BeanFactory;
import com.gelo.mioc.util.DIResolver;

/**
 * The type Singleton bean factory.
 *
 * @param <T> the type parameter
 */
public class SingletonBeanFactory<T> implements BeanFactory<T> {
    private final T INSTANCE;

    /**
     * Instantiates a new Singleton bean factory.
     *
     * @param INSTANCE the instance
     */
    public SingletonBeanFactory(T INSTANCE) {
        this.INSTANCE = INSTANCE;
    }

    /**
     * Instantiates a new Singleton bean factory.
     *
     * @param clazz the clazz
     */
    public SingletonBeanFactory(Class<T> clazz) {
        INSTANCE = DIResolver.autowire(clazz);
    }


    @Override
    public Class<T> forClass() {
        return (Class<T>) INSTANCE.getClass();
    }

    @Override
    public T create() {
        return INSTANCE;
    }
}
