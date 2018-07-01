package com.gelo.mioc.bean.scope.impl;

import com.gelo.mioc.bean.scope.BeanFactory;
import com.gelo.mioc.util.DIResolver;

/**
 * The type Prototype bean factory.
 *
 * @param <T> the type parameter
 */
public class PrototypeBeanFactory<T> implements BeanFactory<T> {
    private final Class<T> clazz;

    /**
     * Instantiates a new Prototype bean factory.
     *
     * @param clazz the clazz
     */
    public PrototypeBeanFactory(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Class<T> forClass() {
        return clazz;
    }

    @Override
    public T create() {
        return DIResolver.autowire(clazz);
    }
}
