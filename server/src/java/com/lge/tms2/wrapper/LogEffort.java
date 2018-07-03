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
    private String s_no;
    private String emp_id;
    private String project_name="";
    private String skill_set="";
    private String task_name="";
    private String iris_time = "08:00";
    private String hours="00";
    private String mins="00";
    private String filled_state="0";
    private String reason = "";
    private String comments = "";
    private String iris_date;

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
     * @return the task_name
     */
    public String getTask_name() {
        return task_name;
    }

    /**
     * @param task_name the task_name to set
     */
    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    /**
     * @return the iris_time
     */
    public String getIris_time() {
        return iris_time;
    }

    /**
     * @param iris_time the iris_time to set
     */
    public void setIris_time(String iris_time) {
        this.iris_time = iris_time;
    }

    /**
     * @return the hours
     */
    public String getHours() {
        return hours;
    }

    /**
     * @param hours the hours to set
     */
    public void setHours(String hours) {
        this.hours = hours;
    }

    /**
     * @return the filled_state
     */
    public String getFilled_state() {
        return filled_state;
    }

    /**
     * @param filled_state the filled_state to set
     */
    public void setFilled_state(String filled_state) {
        this.filled_state = filled_state;
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

    /**
     * @return the s_no
     */
    public String getS_no() {
        return s_no;
    }

    /**
     * @param s_no the s_no to set
     */
    public void setS_no(String s_no) {
        this.s_no = s_no;
    }

    /**
     * @return the skill_set
     */
    public String getSkill_set() {
        return skill_set;
    }

    /**
     * @param skill_set the skill_set to set
     */
    public void setSkill_set(String skill_set) {
        this.skill_set = skill_set;
    }

    /**
     * @return the mins
     */
    public String getMins() {
        return mins;
    }

    /**
     * @param mins the mins to set
     */
    public void setMins(String mins) {
        this.mins = mins;
    }

    /**
     * @return the iris_date
     */
    public String getIris_date() {
        return iris_date;
    }

    /**
     * @param iris_date the iris_date to set
     */
    public void setIris_date(String iris_date) {
        this.iris_date = iris_date;
    }

    /**
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }
    
}
