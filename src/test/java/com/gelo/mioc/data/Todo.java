package com.gelo.mioc.data;

import com.gelo.mioc.annotation.Qualifier;

public class Todo {
    private Long id;
    private String task;

    public Todo(@Qualifier("id") Long id, @Qualifier("task") String task) {
        this.id = id;
        this.task = task;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
