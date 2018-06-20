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
public class EmpInfo implements Serializable {  
    private static final long serialVersionUID = 1L; 
    private String emp_id;
    private String emp_name;
    private String emp_email;
    private String emp_image;    
    private String emp_dsgn;
    private int emp_number;    
    private String emp_group;
    private String emp_domain;
    private String app_level1;
    private String app_level2;

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
     * @return the emp_email
     */
    public String getEmp_email() {
        return emp_email;
    }

    /**
     * @param emp_email the emp_email to set
     */
    public void setEmp_email(String emp_email) {
        this.emp_email = emp_email;
    }

    /**
     * @return the emp_dsgn
     */
    public String getEmp_dsgn() {
        return emp_dsgn;
    }

    /**
     * @param emp_dsgn the emp_dsgn to set
     */
    public void setEmp_dsgn(String emp_dsgn) {
        this.emp_dsgn = emp_dsgn;
    }

    /**
     * @return the emp_domain
     */
    public String getEmp_domain() {
        return emp_domain;
    }

    /**
     * @param emp_domain the emp_domain to set
     */
    public void setEmp_domain(String emp_domain) {
        this.emp_domain = emp_domain;
    }

    /**
     * @return the emp_group
     */
    public String getEmp_group() {
        return emp_group;
    }

    /**
     * @param emp_group the emp_group to set
     */
    public void setEmp_group(String emp_group) {
        this.emp_group = emp_group;
    }

    /**
     * @return the app_level1
     */
    public String getApp_level1() {
        return app_level1;
    }

    /**
     * @param app_level1 the app_level1 to set
     */
    public void setApp_level1(String app_level1) {
        this.app_level1 = app_level1;
    }

    /**
     * @return the app_level2
     */
    public String getApp_level2() {
        return app_level2;
    }

    /**
     * @param app_level2 the app_level2 to set
     */
    public void setApp_level2(String app_level2) {
        this.app_level2 = app_level2;
    }

    @Override
    public String toString() {
        return "Emp Name: " + emp_email; //To change body of generated methods, choose Tools | Templates.
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

    /**
     * @return the emp_image
     */
    public String getEmp_image() {
        return emp_image;
    }

    /**
     * @param emp_image the emp_image to set
     */
    public void setEmp_image(String emp_image) {
        this.emp_image = emp_image;
    }

    /**
     * @return the emp_number
     */
    public int getEmp_number() {
        return emp_number;
    }

    /**
     * @param emp_number the emp_number to set
     */
    public void setEmp_number(int emp_number) {
        this.emp_number = emp_number;
    }
    
    
}
