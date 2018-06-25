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
    private String emp_designation;
    private String emp_number;
    private String emp_group;
    private String emp_domain;
    private String approver_level_one;
    private String approver_level_two;

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
     * @return the emp_designation
     */
    public String getEmp_designation() {
        return emp_designation;
    }

    /**
     * @param emp_dsgn the emp_designation to set
     */
    public void setEmp_designation(String emp_dsgn) {
        this.emp_designation = emp_dsgn;
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
     * @return the approver_level_one
     */
    public String getApprover_level_one() {
        return approver_level_one;
    }

    /**
     * @param approver_level_one the approver_level_one to set
     */
    public void setApprover_level_one(String approver_level_one) {
        this.approver_level_one = approver_level_one;
    }

    /**
     * @return the approver_level_two
     */
    public String getApprover_level_two() {
        return approver_level_two;
    }

    /**
     * @param approver_level_two the approver_level_two to set
     */
    public void setApprover_level_two(String approver_level_two) {
        this.approver_level_two = approver_level_two;
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
     * @param empName the emp_name to set
     */
    public void setEmp_name(String empName) {
        this.emp_name = empName;
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
    public String getEmp_number() {
        return emp_number;
    }

    /**
     * @param emp_number the emp_number to set
     */
    public void setEmp_number(String emp_number) {
        this.emp_number = emp_number;
    }
    
    
}
