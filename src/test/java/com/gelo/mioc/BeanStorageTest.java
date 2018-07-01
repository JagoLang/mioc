package com.gelo.mioc;

import com.gelo.mioc.data.Todo;
import com.gelo.mioc.bean.scope.BeanFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BeanStorageTest {

    @Before
    public void setUp() throws Exception {
        BeanStorage.clear();
    }

    @Test
    public void getOrElse() {
        StringBuilder other = new StringBuilder();
        StringBuilder storageOrElse = BeanStorage.getOrElse(StringBuilder.class, other);
        Assert.assertSame(other, storageOrElse);
    }

    @Test
    public void getWithCast() {
        Long l1 = 1L;
        BeanStorage.singleton(l1);

        Number number = BeanStorage.get(Number.class);

        Assert.assertEquals(l1, number);
    }

    @Test
    public void getSingletonByQualifier() {
        BeanStorage.singleton(322L);
        BeanStorage.singleton("SHARU");
        BeanStorage.singleton("id", 2L);
        BeanStorage.singleton("task", "KEK");
        BeanStorage.singleton(Todo.class);

        Todo todo = BeanStorage.get(Todo.class);

        Assert.assertEquals(todo.getId().intValue(), 2);
        Assert.assertEquals(todo.getTask(), "KEK");
    }

    @Test
    public void getSingleton() {
        BeanStorage.singleton(new Todo(1L, "TEST"));
        Todo todo = BeanStorage.get(Todo.class);
        Todo todo1 = BeanStorage.get(Todo.class);
        Assert.assertSame(todo, todo1);
        BeanStorage.singleton(Todo.class);
        todo = BeanStorage.get(Todo.class);
        todo1 = BeanStorage.get(Todo.class);
        Assert.assertSame(todo, todo1);
    }

    @Test
    public void getPrototype() {
        BeanStorage.prototype(Todo.class);
        Todo todo = BeanStorage.get(Todo.class);
        Todo todo1 = BeanStorage.get(Todo.class);
        Assert.assertNotSame(todo, todo1);
        BeanStorage.scope("todo", new BeanFactory<Todo>() {
            @Override
            public Class<Todo> forClass() {
                return Todo.class;
            }

            @Override
            public Todo create() {
                return new Todo(1L, "TEST");
            }
        });
        todo = BeanStorage.get(Todo.class);
        todo1 = BeanStorage.get(Todo.class);
        Assert.assertNotSame(todo, todo1);
    }

}