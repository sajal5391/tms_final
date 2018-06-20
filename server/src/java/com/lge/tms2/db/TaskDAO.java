/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lge.tms2.db;

import com.lge.tms2.wrapper.Tasks;
import java.util.List;

/**
 *
 * @author ramesh.nagarajan
 */
public interface TaskDAO {
    String TABLE_TASK_TABLE = "task_table";

    public List<Tasks> getAllTasks();

    public Tasks getTasks(String employeeId);
    
    public Tasks getTasks(Tasks log);
    
    public int insertTasks(Tasks log);    

    public int updateTasks(Tasks log);
    
    public int updateTasks(String key, String value, String employeeId);

    public boolean deleteTasks(String projectType, String task);
}
