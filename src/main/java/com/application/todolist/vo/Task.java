package com.application.todolist.vo;

import java.util.Date;

public class Task {
    private int id;
    private String description;
    private boolean completed;
    private Date createdDate;

    public Task() {
    }

    public Task(int id, String description, boolean completed, Date createdDate) {
        this.id = id;
        this.description = description;
        this.completed = completed;
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

}
