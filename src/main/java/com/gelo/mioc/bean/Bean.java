package com.gelo.mioc.bean;

import com.gelo.mioc.bean.meta.BeanMetadata;
import com.gelo.mioc.bean.scope.BeanFactory;

/**
 * The type Bean.
 */
public class Bean {
    private BeanMetadata<?> metadata;
    private BeanFactory<?> factory;

    /**
     * Instantiates a new Bean.
     *
     * @param metadata the metadata
     * @param factory  the factory
     */
    public Bean(BeanMetadata<?> metadata, BeanFactory<?> factory) {
        this.metadata = metadata;
        this.factory = factory;
    }

    /**
     * Gets metadata.
     *
     * @return the metadata
     */
    public BeanMetadata<?> getMetadata() {
        return metadata;
    }

    /**
     * Gets factory.
     *
     * @return the factory
     */
    public BeanFactory<?> getFactory() {
        return factory;
    }
}
