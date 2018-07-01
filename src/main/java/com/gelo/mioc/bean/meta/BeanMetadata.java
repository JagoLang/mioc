package com.gelo.mioc.bean.meta;

import java.util.Objects;

/**
 * The type BeanMetadata.
 *
 * @param <T> the type parameter
 */
public class BeanMetadata<T> {
    private String name;
    private Class<T> clazz;

    /**
     * Instantiates a new BeanMetadata.
     *
     * @param name  the name
     * @param clazz the clazz
     */
    public BeanMetadata(String name, Class<T> clazz) {
        this.name = name;
        this.clazz = clazz;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets clazz.
     *
     * @return the clazz
     */
    public Class<T> getClazz() {
        return clazz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BeanMetadata)) return false;
        BeanMetadata<?> qualifier = (BeanMetadata<?>) o;
        return Objects.equals(name, qualifier.name) &&
                Objects.equals(clazz, qualifier.clazz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, clazz);
    }
}
