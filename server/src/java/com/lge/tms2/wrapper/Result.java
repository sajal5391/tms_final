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
public class Result implements Serializable {  
    private static final long serialVersionUID = 1L; 
    
    private String status;
    private String message;

    public Result(String Status, String message) {
        this.status = Status;
        this.message = message;
    }

    public Result() {
    }
    
    /**
     * @return the Status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param Status the Status to set
     */
    public void setStatus(String Status) {
        this.status = Status;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
}
