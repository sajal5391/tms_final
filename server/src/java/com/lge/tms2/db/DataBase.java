/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lge.tms2.db;

import com.lge.tms2.utils.Util;
import com.lge.tms2.wrapper.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ramesh.nagarajan
 */
public class DataBase {

    protected String tableName;

    void closeAll(ResultSet result, Statement ps) {
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

    void readFromResultSet(EmpInfo empInfo, ResultSet result) throws SQLException {
        empInfo.setEmp_id(result.getString("emp_id"));
        empInfo.setEmp_name(result.getString("emp_name"));
        empInfo.setEmp_email(result.getString("emp_email"));
        empInfo.setEmp_image(result.getString("emp_image"));
        empInfo.setEmp_group(result.getString("emp_group"));
        empInfo.setEmp_number(result.getInt("emp_number"));
        empInfo.setEmp_dsgn(result.getString("emp_dsgn"));
        empInfo.setEmp_domain(result.getString("emp_domain"));
        empInfo.setApp_level1(result.getString("app_level1"));
        empInfo.setApp_level2(result.getString("app_level2"));
    }

    void readFromResultSet(SkillSet empInfo, ResultSet result) throws SQLException {
        empInfo.setEmp_id(result.getString("emp_id"));
        empInfo.setLng1(result.getString("lng1"));
        empInfo.setLng1_level(result.getString("lng1_level"));
        empInfo.setLng2(result.getString("lng2"));
        empInfo.setLng2_level(result.getString("lng2_level"));
        empInfo.setOs(result.getString("os"));
        empInfo.setOs_level(result.getString("os_level"));
        empInfo.setPsk_level1(result.getString("psk_level1"));
        empInfo.setPsk_level2(result.getString("psk_level2"));
        empInfo.setPsk_level3(result.getString("psk_level3"));
        empInfo.setSsk_level1(result.getString("ssk_level1"));
        empInfo.setSsk_level2(result.getString("ssk_level2"));
        empInfo.setSsk_level3(result.getString("ssk_level3"));
    }

    void readFromResultSet(ProjectDetails pd, ResultSet result) throws SQLException {
        pd.setEmp_id(result.getString("emp_id"));
        pd.setProject_name(result.getString("project_name"));
        pd.setProject_code(result.getString("project_code"));
        pd.setProject_type(result.getString("project_type"));
        pd.setProject_cty(result.getString("project_cty"));
        pd.setMc_cty(result.getString("mc_cty"));
        pd.setStart_date(result.getString("start_date"));
        pd.setEnd_date(result.getString("end_date"));
        pd.setProject_region(result.getString("project_region"));
        pd.setProject_country(result.getString("project_country"));
        pd.setProject_suffix(result.getString("project_suffix"));
    }

    void readFromResultSet(LogEffort log, ResultSet result) throws SQLException {
        log.setEmp_id(result.getString("emp_id"));
        log.setProject_name(result.getString("project_name"));
        log.setProject_type(result.getString("project_type"));
        log.setPrimary_skill(result.getString("primary_skill"));
        log.setSecondary_skill(result.getString("secondary_skill"));
        log.setTasks(result.getString("tasks"));
        log.setTotal_time(result.getString("total_time"));
        log.setTime_spent(result.getString("time_spent"));
        log.setStatus(result.getString("status"));
        log.setReason(result.getString("reason"));
    }

    void readFromResultSet(Tasks task, ResultSet result) throws SQLException {
        task.setProject_type(result.getString("project_type"));
        task.setTasks(result.getString("tasks"));
    }

    int updateTableKeyValue(String key, String value, String employeeId) {
        if (Util.isEmpty(key) || Util.isEmpty(value)) {
            return -1;
        }
        Connection con = GetCon.getConnection();

        if (con == null) {
            return -1;
        }

        PreparedStatement ps = null;
        int status = -1;

        try {
            ps = con.prepareStatement("UPDATE `" + tableName + "` SET `" + key + "`=? WHERE `emp_id`= " + employeeId);
            ps.setString(1, value);
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

    boolean deleteTableByEmpId(String empId) {
        boolean result = false;
        Connection con = GetCon.getConnection();

        if (con == null || Util.isEmpty(empId)) {
            return result;
        }

        PreparedStatement ps = null;
        try {
            Util.Log("DELETE from `" + tableName + "` WHERE emp_id = '" + empId + "'");
            ps = con.prepareStatement("DELETE from `" + tableName + "` WHERE emp_id = '" + empId + "'");
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

    public List<JoinEmpSkills> getLeftJoin() {
        return getLeftJoin("");
    }

    public List<JoinEmpSkills> getLeftJoin(String emp_id) {
        List<JoinEmpSkills> list = new ArrayList<JoinEmpSkills>();
        Connection con = GetCon.getConnection();

        if (con == null) {
            return null;
        }

        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            if (emp_id != null && !emp_id.isEmpty()) {
                ps = con.prepareStatement("SELECT * FROM emp_details LEFT JOIN emp_skillset ON emp_details.emp_id = emp_skillset.emp_id where " + emp_id + "");
            } else {
                ps = con.prepareStatement("SELECT * FROM emp_details LEFT JOIN emp_skillset ON emp_details.emp_id = emp_skillset.emp_id");
            }
            System.out.println(ps.toString());
            result = ps.executeQuery();
            while (result.next()) {
                JoinEmpSkills join = new JoinEmpSkills(new EmpInfo(), new SkillSet());
                readFromResultSet(join.getEmpinfo(), result);
                readFromResultSet(join.getSkill(), result);
                list.add(join);
            }

        } catch (SQLException e) {
            list = null;
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
        return list;
    }

}
