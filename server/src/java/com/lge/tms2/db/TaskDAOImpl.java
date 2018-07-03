/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lge.tms2.db;

import com.lge.tms2.utils.Util;
import com.lge.tms2.wrapper.Tasks;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ramesh.nagarajan
 */
public class TaskDAOImpl extends DataBase implements TaskDAO{

    public TaskDAOImpl() {
         tableName = TABLE_TASK_TABLE;
    }
  
    
    @Override
    public List<Tasks> getAllTasks() {
        return getAllTasks("");
    }
    
    public List<Tasks> getAllTasks(String queryString) {
        Connection con = null;
        ArrayList<Tasks> list = new ArrayList<Tasks>();
        Util.Log("getAllTasks -> : query" + queryString);
        con = GetCon.getConnection();

        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            if (queryString == null || queryString.isEmpty()) {
                ps = con.prepareStatement("Select * from `" + tableName + "`");
                Util.Log("Select * from `" + tableName + "`");
            } else {
                ps = con.prepareStatement("Select * from `" + tableName + "` WHERE " + queryString);
                Util.Log("Select * from `" + tableName + "` WHERE " + queryString);
            }
            result = ps.executeQuery();
            while (result.next()) {
                Tasks skillInfo = new Tasks();
                readFromResultSet(skillInfo, result);
                list.add(skillInfo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            list = null;
        } finally {            
                closeAll(result, ps);
           
        }
        return list;
    }

    @Override
    public Tasks getTasks(String query) {
        return getTasks(new Tasks(), query);
    }

    @Override
    public Tasks getTasks(Tasks log) {
       return getTasks(log, " `project_type` = '" + log.getProject_task() + "'");
    }
    
    public Tasks getTasks(Tasks task , String query) {
        Connection con = null;        
        Util.Log("getTasks -> : query" + query);
        con = GetCon.getConnection();

        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            if (query == null || query.isEmpty()) {
                ps = con.prepareStatement("Select * from `" + tableName + "`");
                Util.Log("Select * from `" + tableName + "`");
            } else {
                ps = con.prepareStatement("Select * from `" + tableName + "` WHERE " + query);
                Util.Log("Select * from `" + tableName + "` WHERE " + query);
            }
            result = ps.executeQuery();
            if (result.next()) {
                readFromResultSet(task, result);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }

                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {

            }
        }
        return task;
    }
    

    @Override
    public int insertTasks(Tasks task) {
         int status = -1;
        if (task == null || Util.isEmpty(task.getProject_task())) {
            return status;
        }
        Connection con = GetCon.getConnection();

        if (con == null) {
            return status;
        }

        PreparedStatement ps = null;
        try {            
            ps = con.prepareStatement("INSERT INTO `" + tableName + "` (`project_type`,`tasks`) VALUES (?, ?)");
            ps.setString(1, task.getProject_task());
            ps.setString(2, task.getCommon_task());
            
            status = ps.executeUpdate();
            if (status == 0) {
                status = -1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {

            }
        }
        return status;
    }

    @Override
    public int updateTasks(Tasks task) {
        if (task == null) {
            return -1;
        }

        

        Connection con = GetCon.getConnection();

        if (con == null) {
            return -1;
        }

        PreparedStatement ps = null;
        int status = -1;

        try {

            ps = con.prepareStatement("UPDATE `" + tableName + "` SET `tasks` = ? WHERE `project_type`= ?");
            ps.setString(1, task.getCommon_task());           
            ps.setString(2, task.getProject_task());
            status = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {

            }
        }
        return status;
    }

    @Override
    public int updateTasks(String key, String value, String employeeId) {
        return updateTableKeyValue(key, value,  "`emp_id`= " +employeeId);
    }

    @Override
    public boolean deleteTasks(String projectType, String task) {
        boolean result = false;
        Connection con = GetCon.getConnection();

        if (con == null || Util.isEmpty(projectType) || Util.isEmpty(task)) {
            return result;
        }

        PreparedStatement ps = null;
        try {
            Util.Log("DELETE from `" + tableName + "` WHERE project_type = '" + projectType + "' AND tasks = '" + task + "'");
            ps = con.prepareStatement("DELETE from `" + tableName + "` WHERE project_type = '" + projectType + "' AND tasks = '" + task + "'");
            result = ps.executeUpdate() != 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    
}
