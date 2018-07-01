package com.gelo.mioc;

import com.gelo.mioc.bean.Bean;
import com.gelo.mioc.bean.meta.BeanMetadata;
import com.gelo.mioc.bean.scope.BeanFactory;
import com.gelo.mioc.bean.scope.impl.PrototypeBeanFactory;
import com.gelo.mioc.bean.scope.impl.SingletonBeanFactory;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The enum Bean storage.
 * This class stores Instances of Classes aka Beans.
 */
public final class BeanStorage {

    /**
     * The Beans.
     */
    private final static List<Bean> beans = new CopyOnWriteArrayList<>();

    /**
     * Add.
     *
     * @param <T>     the type parameter
     * @param name    the name
     * @param factory the factory
     */
    private static <T> void add(String name, BeanFactory<T> factory) {
        BeanMetadata<T> metadata = new BeanMetadata<>(name, factory.forClass());

        for (Bean bean : beans) {
            if (bean.getMetadata().equals(metadata)) {
                beans.remove(bean);
            }
        }

        beans.add(new Bean(metadata, factory));
    }

    /**
     * Creates bean with singleton scope from instance of object
     *
     * @param object object that would be singleton
     */
    public static <T> void singleton(T object) {
        singleton(object.getClass().getName(), object);
    }

    /**
     * Creates bean with singleton scope from instance of object with given name
     *
     * @param name   name of the bean
     * @param object object that would be singleton
     */
    public static <T> void singleton(String name, T object) {
        add(name, new SingletonBeanFactory<>(object));
    }

    /**
     * Creates bean with singleton scope using DI
     *
     * @param clazz class of the bean that would be stored
     */
    public static <T> void singleton(Class<T> clazz) {
        singleton(clazz.getName(), clazz);
    }


    /**
     * Creates bean with singleton scope using DI with a given name
     *
     * @param name  name of the bean
     * @param clazz object that would be singleton
     */
    public static <T> void singleton(String name, Class<T> clazz) {
        add(name, new SingletonBeanFactory<>(clazz));
    }

    /**
     * Scope. Create bean with your own scope and given key
     *
     * @param key     the key
     * @param factory the factory
     */
    public static <T> void scope(String key, BeanFactory<T> factory) {
        add(key, factory);
    }

    /**
     * Scope. Create bean with your own scope
     *
     * @param factory the factory
     */
    public static <T> void scope(BeanFactory<T> factory) {
        add(factory.getClass().getName(), factory);
    }

    /**
     * Prototype. Create with a prototype scope using DI
     *
     * @param clazz the clazz
     */
    public static <T> void prototype(Class<T> clazz) {
        prototype(clazz.getName(), clazz);
    }

    /**
     * Prototype. Create with a prototype scope using DI and given name
     *
     * @param name  the name
     * @param clazz the clazz
     */
    public static <T> void prototype(String name, Class<T> clazz) {
        add(name, new PrototypeBeanFactory<>(clazz));
    }

    /**
     * Get object with a given name
     *
     * @param name the name
     */
    public static Object get(String name) {
        return getOrElse(name, null);
    }

    /**
     * Gets object with a given name or else returns other value
     *
     * @param name  the name
     * @param other the other
     */
    public static Object getOrElse(String name, Object other) {
        for (Bean bean : beans) {
            if (bean.getMetadata().getName().equals(name)) {
                return bean.getFactory().create();
            }
        }
        return other;
    }

    /**
     * Gets object with a given name or throw
     *
     * @param name      the name
     * @param exception the exception
     * @return the or throw
     */
    public static Object getOrThrow(String name, RuntimeException exception) {
        for (Bean bean : beans) {
            if (bean.getMetadata().getName().equals(name)) {
                return bean.getFactory().create();
            }
        }

        throw exception;
    }

    /**
     * Get object of a given class
     *
     * @param clazz the clazz
     * @return the t
     */
    public static <T> T get(Class<T> clazz) {
        return getOrElse(clazz, null);
    }

    /**
     * Gets object of a given class or else returns other value
     *
     * @param clazz the clazz
     * @param other the other
     * @return the or else
     */
    public static <T> T getOrElse(Class<T> clazz, T other) {
        for (Bean bean : beans) {
            if (clazz.isAssignableFrom(bean.getMetadata().getClazz())) {
                return clazz.cast(bean.getFactory().create());
            }
        }

        return other;
    }

    /**
     * Gets object of a given class or throws given exception
     *
     * @param clazz     the clazz
     * @param exception the exception
     * @return the or throw
     */
    public static <T> T getOrThrow(Class<T> clazz, RuntimeException exception) {
        for (Bean bean : beans) {
            if (clazz.isAssignableFrom(bean.getMetadata().getClazz())) {
                return clazz.cast(bean.getFactory().create());
            }
        }

        throw exception;
    }

    /**
     * Clears bean storage
     */
    public static void clear() {
        beans.clear();
    }

    /**
     * Removes bean of a given class
     *
     * @param clazz the clazz
     * @return the bean
     */
    public static <T> Bean remove(Class<T> clazz) {
        Bean deleted = null;
        for (Bean bean : beans) {
            if (bean.getMetadata().getClazz().equals(clazz)) {
                deleted = bean;
                beans.remove(bean);
            }
        }
        return deleted;
    }

    /**
     * Removes bean with a given name
     *
     * @param name the name
     * @return the bean
     */
    public static Bean remove(String name) {
        Bean deleted = null;
        for (Bean bean : beans) {
            if (bean.getMetadata().getName().equals(name)) {
                deleted = bean;
                beans.remove(bean);
            }
        }
        return deleted;
    }
}