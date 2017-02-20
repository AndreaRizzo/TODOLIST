package com.example.andrearizzo.todolist.models;

import java.util.Date;

/**
 * Created by Andrea Rizzo on 20/02/2017.
 */

public class Note  {

    private String title;
    private String body;
    private Status status;
    private Date createdAt,modifiedAt,dueDate;

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Status getStatus() {
        return status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
