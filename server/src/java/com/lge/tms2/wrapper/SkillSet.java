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
public class SkillSet implements Serializable {  
    private static final long serialVersionUID = 1L; 
    private String emp_id;
    private String primary_skill_one;
    private String primary_skill_two;
    private String primary_skill_three;
    private String secondary_skill_one;
    private String secondary_skill_two;
    private String secondary_skill_three;
    private String language_one;
    private String language_one_level;
    private String language_two;
    private String language_two_level;
    private String os;
    private String os_level;

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
     * @return the primary_skill_one
     */
    public String getPrimary_skill_one() {
        return primary_skill_one;
    }

    /**
     * @param psk_level1 the primary_skill_one to set
     */
    public void setPrimary_skill_one(String psk_level1) {
        this.primary_skill_one = psk_level1;
    }

    /**
     * @return the primary_skill_two
     */
    public String getPrimary_skill_two() {
        return primary_skill_two;
    }

    /**
     * @param primary_skill_two the primary_skill_two to set
     */
    public void setPrimary_skill_two(String primary_skill_two) {
        this.primary_skill_two = primary_skill_two;
    }

    /**
     * @return the primary_skill_three
     */
    public String getPrimary_skill_three() {
        return primary_skill_three;
    }

    /**
     * @param primary_skill_three the primary_skill_three to set
     */
    public void setPrimary_skill_three(String primary_skill_three) {
        this.primary_skill_three = primary_skill_three;
    }

    /**
     * @return the secondary_skill_one
     */
    public String getSecondary_skill_one() {
        return secondary_skill_one;
    }

    /**
     * @param secondary_skill_one the secondary_skill_one to set
     */
    public void setSecondary_skill_one(String secondary_skill_one) {
        this.secondary_skill_one = secondary_skill_one;
    }

    /**
     * @return the secondary_skill_two
     */
    public String getSecondary_skill_two() {
        return secondary_skill_two;
    }

    /**
     * @param secondary_skill_two the secondary_skill_two to set
     */
    public void setSecondary_skill_two(String secondary_skill_two) {
        this.secondary_skill_two = secondary_skill_two;
    }

    /**
     * @return the secondary_skill_three
     */
    public String getSecondary_skill_three() {
        return secondary_skill_three;
    }

    /**
     * @param secondary_skill_three the secondary_skill_three to set
     */
    public void setSecondary_skill_three(String secondary_skill_three) {
        this.secondary_skill_three = secondary_skill_three;
    }

    /**
     * @return the language_one
     */
    public String getLanguage_one() {
        return language_one;
    }

    /**
     * @param language_one the language_one to set
     */
    public void setLanguage_one(String language_one) {
        this.language_one = language_one;
    }

    /**
     * @return the language_one_level
     */
    public String getLanguage_one_level() {
        return language_one_level;
    }

    /**
     * @param language_one_level the language_one_level to set
     */
    public void setLanguage_one_level(String language_one_level) {
        this.language_one_level = language_one_level;
    }

    /**
     * @return the language_two
     */
    public String getLanguage_two() {
        return language_two;
    }

    /**
     * @param language_two the language_two to set
     */
    public void setLanguage_two(String language_two) {
        this.language_two = language_two;
    }

    /**
     * @return the language_two_level
     */
    public String getLanguage_two_level() {
        return language_two_level;
    }

    /**
     * @param language_two_level the language_two_level to set
     */
    public void setLanguage_two_level(String language_two_level) {
        this.language_two_level = language_two_level;
    }

    /**
     * @return the os
     */
    public String getOs() {
        return os;
    }

    /**
     * @param os the os to set
     */
    public void setOs(String os) {
        this.os = os;
    }

    /**
     * @return the os_level
     */
    public String getOs_level() {
        return os_level;
    }

    /**
     * @param os_level the os_level to set
     */
    public void setOs_level(String os_level) {
        this.os_level = os_level;
    }
}
