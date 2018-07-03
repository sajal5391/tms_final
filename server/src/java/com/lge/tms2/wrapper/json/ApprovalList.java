/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lge.tms2.wrapper.json;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ramesh.nagarajan
 */
public class ApprovalList implements Serializable {  
    private static long serialVersionUID = 1L;
    private List<List<EffortInputJson>> empEfforts;

    public ApprovalList() {
        empEfforts = new ArrayList<List<EffortInputJson>>();
    }

    
    
    /**
     * @return the empEfforts
     */
    public List<List<EffortInputJson>> getEmpEfforts() {
        return empEfforts;
    }

    /**
     * @param empEfforts the empEfforts to set
     */
    public void setEmpEfforts(List<List<EffortInputJson>> empEfforts) {
        this.empEfforts = empEfforts;
    }
    
    public void adddEmpEfforts(List<EffortInputJson> efforts) {
        empEfforts.add(efforts);
    }
}
