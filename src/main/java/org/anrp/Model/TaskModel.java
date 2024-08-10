package org.anrp.Model;

import java.util.Date;


public class TaskModel {

    /**
     * Task Number
     */
    private int taskNumber;

    /**
     * Task added Date
     */
    private Date taskAddedDate;

    /**
     * Task Date
     */
    private Date taskDate;

    /**
     * Task updated Date
     */
    private Date taskUpdatedDate;

    /**
     * Task Title
     */
    private String taskTitle;

    /**
     * Task Description
     */
    String taskDescription;

    public int getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public Date getTaskAddedDate() {
        return taskAddedDate;
    }

    public void setTaskAddedDate(Date taskAddedDate) {
        this.taskAddedDate = taskAddedDate;
    }

    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }

    public Date getTaskUpdatedDate() {
        return taskUpdatedDate;
    }

    public void setTaskUpdatedDate(Date taskUpdatedDate) {
        this.taskUpdatedDate = taskUpdatedDate;
    }

    public TaskModel(String taskDescription, String taskTitle, Date taskUpdatedDate, Date taskDate) {
        this.taskDescription = taskDescription;
        this.taskTitle = taskTitle;
        this.taskUpdatedDate = taskUpdatedDate;
        this.taskDate = taskDate;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }



}
