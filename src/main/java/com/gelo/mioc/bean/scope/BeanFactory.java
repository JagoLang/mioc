package com.gelo.mioc.bean.scope;

/**
 * The interface Bean factory.
 *
 * @param <T> the type parameter
 */
public interface BeanFactory<T> {
    /**
     * For class class.
     *
     * @return the class
     */
    Class<T> forClass();

    /**
     * Create t.
     *
     * @return the t
     */
    T create();
}
