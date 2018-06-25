/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lge.tms2.wrapper;

import com.lge.tms2.utils.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author ramesh.nagarajan
 */
public class CalendarDate implements Serializable {  
    private static final long serialVersionUID = 1L; 
    public static final int NOT_FILLED = 0;
    public static final int FILLED = 1;
    public static final int SUBMITTED = 2;
    public static final int APPROVED = 3;
    public static final int REJECTED = 4;
    
    private String empId;
    private List<String> approvedDates;
    private List<String> rejectedDates;
    private List<String> notFilledDates;

    public CalendarDate(){
        approvedDates = new ArrayList<>();
        notFilledDates = new ArrayList<>();
        rejectedDates = new ArrayList<>();        
    }
    
    public void addStatusValue(int status, String value) {
        switch(status) {
            case NOT_FILLED:
                addNotFilledDates(value);
                break;
            case REJECTED:
                addRejectedDates(value);
                break;
            case APPROVED:
                addApprovedDates(value);
                break;
        }
    }
    
    
    public void addApprovedDates(String dates) {
         approvedDates.add(dates);
    }
    
    /**
     * @return the approvedDates
     */
    public List<String> getApprovedDates() {
        return approvedDates;
    }

    public void setApprovedDates(String approvedDates) {
        if(!Util.isEmpty(approvedDates)) {          
          this.approvedDates = new ArrayList<String>(Arrays.asList(approvedDates.split(",")));
        }
    }
    
    /**
     * @param approvedDates the approvedDates to set
     */
    public void setApprovedDates(List<String> approvedDates) {
        this.approvedDates = approvedDates;
    }
    
    public void addRejectedDates(String dates) {
         rejectedDates.add(dates);
    }

    /**
     * @return the rejectedDates
     */
    public List<String> getRejectedDates() {
        return rejectedDates;
    }
    
    public void setRejectedDates(String rejectedDates) {
        if(!Util.isEmpty(rejectedDates)) {          
          this.rejectedDates = new ArrayList<String>(Arrays.asList(rejectedDates.split(",")));
        }
    }

    /**
     * @param rejectedDates the rejectedDates to set
     */
    public void setRejectedDates(List<String> rejectedDates) {
        this.rejectedDates = rejectedDates;
    }

    public void addNotFilledDates(String dates) {
         notFilledDates.add(dates);
    }
    
    /**
     * @return the notFilledDates
     */
    public List<String> getNotFilledDates() {
        return notFilledDates;
    }
    
    public void setNotFilledDates(String notFilledDates) {
        if(!Util.isEmpty(notFilledDates)) {          
          this.notFilledDates = new ArrayList<String>(Arrays.asList(notFilledDates.split(",")));
        }
    }

    /**
     * @param notFilledDates the notFilledDates to set
     */
    public void setNotFilledDates(List<String> notFilledDates) {
        this.notFilledDates = notFilledDates;
    }

    /**
     * @return the empId
     */
    public String getEmpId() {
        return empId;
    }

    /**
     * @param empId the empId to set
     */
    public void setEmpId(String empId) {
        this.empId = empId;
    }
    
}
