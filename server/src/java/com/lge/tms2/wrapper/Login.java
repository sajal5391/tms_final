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
public class Login implements Serializable {  
    private static final long serialVersionUID = 1L; 
    private String username;
    private String password;

    /**
     * @return the username
     */
    public String getUsername() {
        if(username != null && username.endsWith("@lge.com")) {
            username = username.substring(0, username.length() - "@lge.com".length());
        }
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "username: "+username+", password: " +password; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
