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
public class CommonTask implements Serializable {  
    private static final long serialVersionUID = 1L; 
    private String value;

    public CommonTask() {
    }

    public CommonTask(String value) {
        this.value = value;
    }
    
    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }
}
