/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lge.tms2.db;

import com.lge.tms2.utils.Util;
import com.lge.tms2.wrapper.LogEffort;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ramesh.nagarajan
 */
public class LogEffortDAOImpl extends DataBase implements LogEffortDAO{

    public LogEffortDAOImpl() {        
        tableName =  TABLE_LOG_EFFORT;
    }
    
    @Override
    public List<LogEffort> getAllLogEffort() {
        return getAllLogEffort("");
    }
    
    public List<LogEffort> getAllLogEffort(String query) {
        Connection con = null;
        ArrayList<LogEffort> list = new ArrayList<LogEffort>();
        Util.Log("getAllLogEffort -> : query" + query);
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
            while (result.next()) {
                LogEffort pd = new LogEffort();
                readFromResultSet(pd, result);
                list.add(pd);
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
    public LogEffort getLogEffort(LogEffort log) {
       return getLogEffort(log , "emp_id = '" + log.getEmp_id() + "'");
    }    

    @Override
    public LogEffort getLogEffort(String query) {
          return getLogEffort(new LogEffort(), query);
    }
     
    public LogEffort getLogEffort(LogEffort log,String query) {
        Connection con = null;        
        Util.Log("getLogEffort -> : query" + query);
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
                readFromResultSet(log, result);
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
        return log;
    }

    @Override
    public int insertLogEffort(LogEffort log) {
        int status = -1;
        if (log == null || Util.isEmpty(log.getEmp_id())) {
            return status;
        }
        Connection con = GetCon.getConnection();

        if (con == null) {
            return status;
        }

        PreparedStatement ps = null;
        try {            
            ps = con.prepareStatement("INSERT INTO `" + tableName + "` (`emp_id`,`project_name`,`project_type`,`primary_skill`,`secondary_skill`,`tasks`,`total_time`,`time_spent`,`status`,`reason`) VALUES (?, ?, ?, ?, ?, ?, ?, ? ,? , ?)");
            ps.setString(1, log.getEmp_id());
            ps.setString(2, log.getProject_name());
            ps.setString(3, log.getProject_type());            
            ps.setString(4, log.getPrimary_skill());
            ps.setString(5, log.getSecondary_skill());
            ps.setString(6, log.getTasks());
            ps.setString(7, log.getTotal_time());
            ps.setString(8, log.getTime_spent());
            ps.setString(9, log.getStatus());
            ps.setString(10, log.getReason());
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
    public int updateLogEffort(LogEffort log) {
        if (log == null) {
            return -1;
        }

        Connection con = GetCon.getConnection();

        if (con == null) {
            return -1;
        }

        PreparedStatement ps = null;
        int status = -1;

        try {

            ps = con.prepareStatement("UPDATE `" + tableName + "` SET `emp_id`=?,`project_name`=?,`project_type`=?,`primary_skill`=?,`secondary_skill`=?,`tasks`=?,`total_time`=?,`time_spent`=?,`status`=?,`reason`=? WHERE `emp_id`= ?");

             ps.setString(1, log.getProject_name());
            ps.setString(2, log.getProject_type());            
            ps.setString(3, log.getPrimary_skill());
            ps.setString(4, log.getSecondary_skill());
            ps.setString(5, log.getTasks());
            ps.setString(6, log.getTotal_time());
            ps.setString(7, log.getTime_spent());
            ps.setString(8, log.getStatus());
            ps.setString(9, log.getReason());
            ps.setString(10, log.getEmp_id());
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
    public int updateLogEffort(String key, String value, String employeeId) {
        return updateTableKeyValue(key, value, employeeId);
    }

    @Override
    public boolean deleteLogEffort(String employeeId) {
        return deleteTableByEmpId(employeeId);
    }
    
}
