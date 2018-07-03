/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lge.tms2.db;

import com.lge.tms2.utils.Util;
import com.lge.tms2.wrapper.CalendarDate;
import com.lge.tms2.wrapper.EmpInfo;
import com.lge.tms2.wrapper.json.EffortInputJson;
import com.lge.tms2.wrapper.LogEffort;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ramesh.nagarajan
 */
public class LogEffortDAOImpl extends DataBase implements LogEffortDAO {

    public LogEffortDAOImpl() {
        tableName = TABLE_LOG_EFFORT;
    }

    @Override
    public List<LogEffort> getAllLogEffort() {
        return getAllLogEffort("");
    }

    public List<LogEffort> getAllLogEffort(String query) {
        Connection con = null;
        ArrayList<LogEffort> list = new ArrayList<LogEffort>();
        Util.Log("getAllLogEffort -> : query: " + query);
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

    public List<EffortInputJson> getEffortInputJson(EmpInfo info, StringBuilder date) {

        List<EffortInputJson> outputJson = new ArrayList<EffortInputJson>();
        Util.Log("getEffortJson -> : date is " + date);
        //Date not provided will read "Not Filled, Saved and Rejected"
        Date[] landingDate = getDates(date.toString(), info.getEmp_id());   
        
        if(landingDate != null) {
            if(landingDate[0] == null || landingDate[1] == null) {
                Date curDate = new Date();
                landingDate = getDates(Util.getDateFormat(curDate), info.getEmp_id());
                if(landingDate[0] == null) {
                    landingDate = getDates(Util.getDateFormat(curDate) , null);
                }
                landingDate[1] = Util.getDateFormatDDMMYYYY(curDate);
            }
            
            if(date.length() == 0) {
                date.append(Util.getDateFormat(landingDate[1]));
            }
            for (int i = 0; i < 7; i++) {                
                Date d = Util.addNoofDaysToDate(landingDate[0], i);                
                EffortInputJson input = new EffortInputJson();
                List<LogEffort> list = getAllLogEffort("`emp_id` like '%" + info.getEmp_id() + "%' and `iris_date` = '" + Util.getDateFormat(d) + "'");            
                input.setIris_date(Util.getDateFormatEMMMdd(d));
                input.setEffort(list);              
                input.setIsActive(landingDate[1].equals(d));
                input.populateFields(info);
                outputJson.add(input);
            }
        }

        return outputJson;
    }

    public CalendarDate getAllCalEffort(String query) {
        Connection con = null;
        CalendarDate calDate = new CalendarDate();
        Util.Log("getAllCalEffort -> : query" + query);
        con = GetCon.getConnection();

        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            if (query == null || query.isEmpty()) {
                ps = con.prepareStatement("Select DISTINCT `iris_date` , filled_state from `" + tableName + "` ORDER BY STR_TO_DATE(`iris_date`,'%d-%m-%Y')");
                Util.Log("Select * from `" + tableName + "`");
            } else {
                ps = con.prepareStatement("Select DISTINCT `iris_date` , filled_state from `" + tableName + "` WHERE " + query + " ORDER BY STR_TO_DATE(`iris_date`,'%d-%m-%Y')");
                Util.Log("Select * from `" + tableName + "` WHERE " + query);
            }
            result = ps.executeQuery();
            while (result.next()) {
                calDate.addStatusValue(result.getInt("filled_state"), result.getString("iris_date"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            calDate = null;
        } finally {
            closeAll(result, ps);

        }
        return calDate;
    }

    @Override
    public LogEffort getLogEffort(LogEffort log) {
        return getLogEffort(log, "emp_id = '" + log.getEmp_id() + "'");
    }

    @Override
    public LogEffort getLogEffort(String query) {
        return getLogEffort(new LogEffort(), query);
    }

    public LogEffort getLogEffort(LogEffort log, String query) {
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

    public int insertLogEffort(EffortInputJson effortJson) {
        int status = -1;
        if (effortJson != null) {
            if (effortJson.getEffort() != null) {
                for (LogEffort log : effortJson.getEffort()) {
                    log.setEmp_id(effortJson.getEmp_id());
                    log.setIris_date(effortJson.getIris_date());
                    log.setFilled_state(effortJson.getFilled_state());
                    status = insertLogEffort(log);
                }
            }
        }
        return status;
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
            ps = con.prepareStatement("INSERT INTO `" + tableName + "` (`emp_id`,`project_name`,`skill_set`,`task_name`,`iris_time`,`hours`,`mins`,`filled_state`,`reason`,`iris_date`) VALUES (?, ?, ?, ?, ?, ?, ?, ? ,? , ?)");
            ps.setString(1, log.getEmp_id());
            ps.setString(2, log.getProject_name());
            ps.setString(3, log.getSkill_set());
            ps.setString(4, log.getTask_name());
            ps.setString(5, log.getIris_time());
            ps.setString(6, log.getHours());
            ps.setString(7, log.getMins());
            ps.setString(8, log.getFilled_state());
            ps.setString(9, log.getReason());
            ps.setString(10, log.getIris_date());
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

    public int updateLogEffort(EffortInputJson effortJson) {
        int status = -1;
        if (effortJson != null) {
            if (effortJson.getEffort() != null) {
                for (LogEffort log : effortJson.getEffort()) {
                    log.setEmp_id(effortJson.getEmp_id());
                    log.setIris_date(effortJson.getIris_date());
                    log.setFilled_state(effortJson.getFilled_state());
                    status = updateLogEffort(log);
                }
            }
        }
        return status;
    }

    public int updateLogEffort(EffortInputJson effortJson, List<LogEffort> all) {
        int status = -1;
        if (effortJson != null) {
            if (effortJson.getEffort() != null) {
                int i = 0;
                //Records count is same, so only update fields based on S_no                
                if (effortJson.getEffort().size() == all.size()) {
                    Util.Log("Records count is same (" + all.size() + "), so only update fields based on S_no");
                    for (; i < all.size(); i++) {
                        LogEffort log = effortJson.getEffort().get(i);
                        log.setS_no(all.get(i).getS_no());
                        log.setEmp_id(effortJson.getEmp_id());
                        log.setIris_date(effortJson.getIris_date());
                        log.setFilled_state(effortJson.getFilled_state());
                        status = updateLogEffort(log);
                    }
                    //User Input is more than existing Rows in DB, so update old records and insert the new record
                } else if (effortJson.getEffort().size() > all.size()) {
                    Util.Log("User Input (" + effortJson.getEffort().size() + ") is more than existing Rows in DB(" + all.size() + "), so update old records and insert the new record");
                    for (; i < all.size(); i++) {
                        LogEffort log = effortJson.getEffort().get(i);
                        log.setS_no(all.get(i).getS_no());
                        log.setEmp_id(effortJson.getEmp_id());
                        log.setIris_date(effortJson.getIris_date());
                        log.setFilled_state(effortJson.getFilled_state());
                        status = updateLogEffort(log);
                    }
                    //continue the value of 'i' e.g all.size is 5, then getEffort.size is 7, then i starts from 5 to insert from getEffor
                    Util.Log("continue the value of 'i' e.g all.size is (" + all.size() + "), then getEffort.size is (" + effortJson.getEffort().size() + "), then i starts from " + i + " to insert from getEffor");
                    for (; i < effortJson.getEffort().size(); i++) {
                        LogEffort log = effortJson.getEffort().get(i);
                        log.setEmp_id(effortJson.getEmp_id());
                        log.setIris_date(effortJson.getIris_date());
                        log.setFilled_state(effortJson.getFilled_state());
                        status = insertLogEffort(log);
                    }
                    //User Input is less than existing Rows in DB, so update and delete unwanted Rows
                } else {
                    Util.Log("User Input (" + effortJson.getEffort().size() + ") is less than existing Rows in DB (" + all.size() + "), so update and delete unwanted Rows");
                    for (; i < effortJson.getEffort().size(); i++) {
                        LogEffort log = effortJson.getEffort().get(i);
                        log.setS_no(all.get(i).getS_no());
                        log.setEmp_id(effortJson.getEmp_id());
                        log.setIris_date(effortJson.getIris_date());
                        log.setFilled_state(effortJson.getFilled_state());
                        status = updateLogEffort(log);
                    }
                    //continue the value of 'i' e.g all.size is 7, then getEffort.size is 5, then i starts from 5 to delete from all
                    Util.Log("continue the value of 'i' e.g all.size is (" + all.size() + "), then getEffort.size is (" + effortJson.getEffort().size() + "), then i starts from " + i + " to delete from all");
                    for (; i < all.size(); i++) {
                        LogEffort log = all.get(i);
                        deleteLogEffort("`s_no`= '" + log.getS_no() + "'");
                    }
                }
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

            ps = con.prepareStatement("UPDATE `" + tableName + "` SET `emp_id`=?,`project_name`=?,`skill_set`=?,`task_name`=?,`iris_time`=?,`hours`=?,`mins`=?,`filled_state`=?,`reason`=?,`iris_date`=? WHERE `s_no`= ?");

            ps.setString(1, log.getEmp_id());
            ps.setString(2, log.getProject_name());
            ps.setString(5, log.getIris_time());
            ps.setString(3, log.getSkill_set());
            ps.setString(4, log.getTask_name());
            ps.setString(6, log.getHours());
            ps.setString(7, log.getMins());
            ps.setString(8, log.getFilled_state());
            ps.setString(9, log.getReason());
            ps.setString(10, log.getIris_date());
            ps.setString(11, log.getS_no());

            System.out.println(ps);
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
    public int updateLogEffort(String key, String value, String employeeId , String date) {
        return updateTableKeyValue(key, value,  "`emp_id` like '%" +employeeId + "%' and `iris_date` = '" + date+"'");
    }
    
   
    public int updateLogEffort(String keyValue, String query) {
        return updateTableKeyValues(keyValue, query );
    }

    @Override
    public boolean deleteLogEffort(String query) {
        return deleteTableByQuery(query);
    }

    public Date[] getDates(String iris_date , String emp_id) {
        Date[] date = new Date[2];
        Connection con = GetCon.getConnection();

        if (con == null) {
            return null;
        }

        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            if(!Util.isEmpty(emp_id)) {
                if (iris_date != null && !iris_date.isEmpty()) {
                    ps = con.prepareStatement("SELECT iris_date ,DATE_SUB(str_to_date('" + iris_date + "','%d-%m-%Y'),INTERVAL (DAYOFWEEK(str_to_date('" + iris_date + "','%d-%m-%Y')) - 1) DAY) as start_date FROM log_effort_details where emp_id like '%"+emp_id+"%' ORDER BY STR_TO_DATE(`iris_date`,'%d-%m-%Y') ASC  limit 1 ;");
                } else {
                    ps = con.prepareStatement("SELECT iris_date, DATE_SUB(str_to_date(iris_date,'%d-%m-%Y'),INTERVAL (DAYOFWEEK(str_to_date(iris_date,'%d-%m-%Y')) - 1) DAY) as start_date FROM log_effort_details where emp_id like '%"+emp_id+"%' and (filled_state < 2 or filled_state = 4)  ORDER BY STR_TO_DATE(`iris_date`,'%d-%m-%Y') ASC  limit 1 ;");
                }
            }else {
                ps = con.prepareStatement("SELECT DATE_SUB(str_to_date('" + iris_date + "','%d-%m-%Y'),INTERVAL (DAYOFWEEK(str_to_date('" + iris_date + "','%d-%m-%Y')) - 1) DAY) as start_date");                
            }
            System.out.println(ps.toString());
            result = ps.executeQuery();

            if (result.next()) {
                date[0] = new Date(result.getDate("start_date").getTime());
                if(!Util.isEmpty(emp_id)) {
                    date[1] = new Date(Util.getDateFromSQL(result.getString("iris_date")).getTime());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            date = null;
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
        return date;
    }

}
