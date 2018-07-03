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
    private String project_task;
    private String common_task;

    /**
     * @return the project_task
     */
    public String getProject_task() {
        return project_task;
    }

    /**
     * @param project_task the project_task to set
     */
    public void setProject_task(String project_task) {
        this.project_task = project_task;
    }

    /**
     * @return the common_task
     */
    public String getCommon_task() {
        return common_task;
    }

    /**
     * @param common_task the common_task to set
     */
    public void setCommon_task(String common_task) {
        this.common_task = common_task;
    }
}
