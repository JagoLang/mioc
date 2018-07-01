# MIoC is Minimalistic Inversion of Control

library that can ease development of your application with minimal code amount. If you are experienced Java developer you
know that IoC is very powerful tool without which ouc applications would have been much more harder to understand and maintain.
There are huge amount of great libraries for IoC but mostly they huge framework and IoC is just a part of it.
Wouldn't it be great to have IoC without using this huge frameworks? Yes, and this is where MIoC comes handful.

> Maven config

For now it is stored on a branch of github so you need to add repository and the dependency itself.

```xml
    <dependencies>
        <dependency>
            <groupId>com.gelo</groupId>
            <artifactId>com.gelo.mioc</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>

    <repositories>
        <repository>
            <id>mioc-mvn-repo</id>
            <url>https://raw.github.com/JagoLang/mioc/mvn-repo/</url>
            <snapshots>
                <enabled>true</enabled>
                <updatePolicy>always</updatePolicy>
            </snapshots>
        </repository>
    </repositories>
```

>Documentation

There is already two scopes: singleton and prototype. 
But you also can create your own custom scopes using scope(...) methods. 
Feel free to delegate BeanStorage to your own class while creating scopes like this:
```java
class CustomBeanStorage{
    public static <T> void singleton(T object){BeanStorage.singleton(object);}
    ...
    public static <T> customScope(T object){BeanStorage.scope(new CustomBeanFactory<>(object));}
}
```
 I`ll add one interesting scope that will be really helpful.
 Here is list of current methods with their description.

```java
class Documentation {

    /**
     * Creates bean with singleton scope from instance of object
     *
     * @param object object that would be singleton
     */
    public static <T> void singleton(T object);

    /**
     * Creates bean with singleton scope from instance of object with given name
     *
     * @param name   name of the bean
     * @param object object that would be singleton
     */
    public static <T> void singleton(String name, T object);

    /**
     * Creates bean with singleton scope using DI
     *
     * @param clazz class of the bean that would be stored
     */
    public static <T> void singleton(Class<T> clazz);

    /**
     * Creates bean with singleton scope using DI with a given name
     *
     * @param name  name of the bean
     * @param clazz object that would be singleton
     */
    public static <T> void singleton(String name, Class<T> clazz);

    /**
     * Scope. Create bean with your own scope and given key
     *
     * @param key     the key
     * @param factory the factory
     */
    public static <T> void scope(String key, BeanFactory<T> factory);

    /**
     * Scope. Create bean with your own scope
     *
     * @param factory the factory
     */
    public static <T> void scope(BeanFactory<T> factory);

    /**
     * Prototype. Create with a prototype scope using DI
     *
     * @param clazz the clazz
     */
    public static <T> void prototype(Class<T> clazz);

    /**
     * Prototype. Create with a prototype scope using DI and given name
     *
     * @param clazz the clazz
     */
    public static <T> void prototype(String name, Class<T> clazz);

    /**
     * Get object with a given name
     *
     * @param name the name
     */
    public static Object get(String name);

    /**
     * Gets object with a given name or else returns other value
     *
     * @param name  the name
     * @param other the other
     */
    public static Object getOrElse(String name, Object other);

    /**
     * Gets object with a given name or throw
     *
     * @param name      the name
     * @param exception the exception
     * @return the or throw
     */
    public static Object getOrThrow(String name, RuntimeException exception);

    /**
     * Get object of a given class
     *
     * @param clazz the clazz
     * @return the t
     */
    public static <T> T get(Class<T> clazz);

    /**
     * Gets object of a given class or else returns other value
     *
     * @param clazz the clazz
     * @param other the other
     * @return the or else
     */
    public static <T> T getOrElse(Class<T> clazz, T other);

    /**
     * Gets object of a given class or throws given exception
     *
     * @param clazz     the clazz
     * @param exception the exception
     * @return the or throw
     */
    public static <T> T getOrThrow(Class<T> clazz, RuntimeException exception);

    /**
     * Clears bean storage
     */
    public static void clear();

    /**
     * Removes bean of a given class
     *
     * @param clazz the clazz
     * @return the bean
     */
    public static <T> Bean remove(Class<T> clazz);

    /**
     * Removes bean with a given name
     *
     * @param name the name
     * @return the bean
     */
    public static Bean remove(String name);
}
```

>Developers 
* [Oleg Dubinskiy](https://github.com/0lejk4)


