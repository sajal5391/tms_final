/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lge.tms2.wrapper.json;

import com.lge.tms2.utils.Util;
import com.lge.tms2.wrapper.EmpInfo;
import com.lge.tms2.wrapper.LogEffort;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ramesh.nagarajan
 */
public class EffortInputJson implements Serializable {  
    private static long serialVersionUID = 1L;
    private String emp_id;
    private String emp_name;
    private String iris_date;
    private String filled_state = "0";
    private String iris_time;
    private String comments="";
    private boolean isActive;    
    private String total_log_time;
    private List<LogEffort> effort;

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
     * @return the iris_date
     */
    public String getIris_date() {
        return iris_date;
    }

    /**
     * @param iris_date the iris_date to set
     */
    public void setIris_date(String iris_date) {
        
        this.iris_date = Util.getStringFromEMMMddToDDMMYYYY(iris_date);
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
     * @return the effort
     */
    public List<LogEffort> getEffort() {
        return effort;
    }

    /**
     * @param effort the effort to set
     */
    public void setEffort(List<LogEffort> effort) {
        this.effort = effort;
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
        this.iris_time = iris_time + " Hours";
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

    /**
     * @return the isActive
     */
    public boolean isIsActive() {
        return isActive;
    }

    /**
     * @param isActive the isActive to set
     */
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * @return the total_log_time
     */
    public String getTotal_log_time() {       
        return total_log_time;
    }

    /**
     * @param total_log_time the total_log_time to set
     */
    public void setTotal_log_time(String total_log_time) {
        this.total_log_time = total_log_time;
    }
    
    
    private void populateFields(){
        if(effort != null) {
            LogEffort log = null;
            if(!effort.isEmpty()) {
                log = effort.get(0);          
                
            } else {
                log = new LogEffort();
                effort.add(log);
            }
            setFilled_state(log.getFilled_state());
            setComments(log.getReason());
            setIris_time(log.getIris_time());
            String value = "";            
            for(LogEffort ef : effort) {
                // Add all times from hrs + mins
               value = Util.addHoursMins(value, ef.getHours()+":"+ef.getMins());
            }            
            setTotal_log_time(value + " Hours");
        }
    }
    
    public void populateFields(EmpInfo info){
        if(!Util.isEmpty(info.getEmp_name())) {
            setEmp_name(info.getEmp_name());
        } else {
            populateFields();
        }
    }

    /**
     * @return the emp_name
     */
    public String getEmp_name() {
        return emp_name;
    }

    /**
     * @param emp_name the emp_name to set
     */
    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }
    
}
