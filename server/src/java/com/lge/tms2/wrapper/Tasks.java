/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lge.tms2.wrapper;

import java.io.Serializable;

/**
 *
 * @author ramesh.nagarajan
 */
public class Tasks implements Serializable {  
    private static final long serialVersionUID = 1L; 
    private String project_type;
    private String tasks;

    /**
     * @return the project_type
     */
    public String getProject_type() {
        return project_type;
    }

    /**
     * @param project_type the project_type to set
     */
    public void setProject_type(String project_type) {
        this.project_type = project_type;
    }

    /**
     * @return the tasks
     */
    public String getTasks() {
        return tasks;
    }

    /**
     * @param tasks the tasks to set
     */
    public void setTasks(String tasks) {
        this.tasks = tasks;
    }
}
