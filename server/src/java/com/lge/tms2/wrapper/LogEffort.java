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
public class LogEffort implements Serializable {  
    private static long serialVersionUID = 1L;    
    private String emp_id;
    private String project_name;
    private String project_type;
    private String primary_skill;
    private String secondary_skill;
    private String tasks;
    private String total_time;
    private String time_spent;
    private String status;
    private String reason;

    /**
     * @return the emp_id
     */
    public String getEmp_id() {
        return emp_id;
    }

    /**
     * @param emp_id the emp_id to set
     */
    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    /**
     * @return the project_name
     */
    public String getProject_name() {
        return project_name;
    }

    /**
     * @param project_name the project_name to set
     */
    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

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
     * @return the primary_skill
     */
    public String getPrimary_skill() {
        return primary_skill;
    }

    /**
     * @param primary_skill the primary_skill to set
     */
    public void setPrimary_skill(String primary_skill) {
        this.primary_skill = primary_skill;
    }

    /**
     * @return the secondary_skill
     */
    public String getSecondary_skill() {
        return secondary_skill;
    }

    /**
     * @param secondary_skill the secondary_skill to set
     */
    public void setSecondary_skill(String secondary_skill) {
        this.secondary_skill = secondary_skill;
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

    /**
     * @return the total_time
     */
    public String getTotal_time() {
        return total_time;
    }

    /**
     * @param total_time the total_time to set
     */
    public void setTotal_time(String total_time) {
        this.total_time = total_time;
    }

    /**
     * @return the time_spent
     */
    public String getTime_spent() {
        return time_spent;
    }

    /**
     * @param time_spent the time_spent to set
     */
    public void setTime_spent(String time_spent) {
        this.time_spent = time_spent;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }
    
}
