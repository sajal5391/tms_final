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
public class ProjectDetails implements Serializable {  
    private static final long serialVersionUID = 1L; 
    private String sl_no;
    private String project_name;
    private String project_code;
    private String project_type;
    private String project_category;
    private String mc_category;
    private String start_date;   
    private String end_date;
   /* private String project_region;
    private String project_country;
    private String project_suffix;  */

    @Override
    public String toString() {
        return "emp_id: " + sl_no+ ",project_name: " +project_name; //To change body of generated methods, choose Tools | Templates.
    }

    
    
    /**
     * @return the sl_no
     */
    public String getSl_no() {
        return sl_no;
    }

    /**
     * @param sl_no the sl_no to set
     */
    public void setSl_no(String sl_no) {
        this.sl_no = sl_no;
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
     * @return the project_code
     */
    public String getProject_code() {
        return project_code;
    }

    /**
     * @param project_code the project_code to set
     */
    public void setProject_code(String project_code) {
        this.project_code = project_code;
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
     * @return the project_category
     */
    public String getProject_category() {
        return project_category;
    }

    /**
     * @param project_category the project_category to set
     */
    public void setProject_category(String project_category) {
        this.project_category = project_category;
    }

    /**
     * @return the mc_category
     */
    public String getMc_category() {
        return mc_category;
    }

    /**
     * @param mc_category the mc_category to set
     */
    public void setMc_category(String mc_category) {
        this.mc_category = mc_category;
    }

    /**
     * @return the start_date
     */
    public String getStart_date() {
        return start_date;
    }

    /**
     * @param start_date the start_date to set
     */
    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    /**
     * @return the end_date
     */
    public String getEnd_date() {
        return end_date;
    }

    /**
     * @param end_date the end_date to set
     */
    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

   /*
    public String getProject_region() {
        return project_region;
    }

   
    public void setProject_region(String project_region) {
        this.project_region = project_region;
    }

    
    public String getProject_country() {
        return project_country;
    }

    
    public void setProject_country(String project_country) {
        this.project_country = project_country;
    }

    
    public String getProject_suffix() {
        return project_suffix;
    }

   
    public void setProject_suffix(String project_suffix) {
        this.project_suffix = project_suffix;
    }
    */
}
