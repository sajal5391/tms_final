/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lge.tms2.db;

import com.lge.tms2.wrapper.LogEffort;
import java.util.List;

/**
 *
 * @author ramesh.nagarajan
 */
public interface LogEffortDAO {
    String TABLE_LOG_EFFORT = "log_effort_details";

    public List<LogEffort> getAllLogEffort();

    public LogEffort getLogEffort(String employeeId);
    
    public LogEffort getLogEffort(LogEffort log);
    
    public int insertLogEffort(LogEffort log);    

    public int updateLogEffort(LogEffort log);
    
    public int updateLogEffort(String key, String value, String employeeId, String date);

    public boolean deleteLogEffort(String employeeId );
}
