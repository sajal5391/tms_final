/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lge.tms2.db;


import com.lge.tms2.utils.Util;
import com.lge.tms2.wrapper.SkillSet;
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
public class SkillSetDAOImpl extends DataBase implements SkillSetDAO{

    public SkillSetDAOImpl() {
        tableName = TABLE_EMP_SKILLS;
    }
    
    @Override
    public List<SkillSet> getAllSkillSet() {
        return getAllSkillSet("");
    }
    
    public List<SkillSet> getAllSkillSet(String queryString) {
        Connection con = null;
        ArrayList<SkillSet> list = new ArrayList<SkillSet>();
        Util.Log("getAllSkillSet -> : query" + queryString);
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
                SkillSet skillInfo = new SkillSet();
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
    public SkillSet getSkillSet(String query) {
            return getSkillSet(new SkillSet(), query);
    }
     
    public SkillSet getSkillSet(SkillSet skillSet) {
        return getSkillSet(skillSet , "emp_id = '" + skillSet.getEmp_id() + "'");
    }
    
    public SkillSet getSkillSet(SkillSet skillSet , String query) {
        Connection con = null;        
        Util.Log("getSkillSet -> : query" + query);
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
                readFromResultSet(skillSet, result);
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
        return skillSet;
    }

    @Override
    public int insertSkillSet(SkillSet employee) {
         int status = -1;
        if (employee == null || Util.isEmpty(employee.getEmp_id())) {
            return status;
        }
        Connection con = GetCon.getConnection();

        if (con == null) {
            return status;
        }

        PreparedStatement ps = null;
        try {            
            ps = con.prepareStatement("INSERT INTO `" + tableName + "` (`emp_id`,`primary_skill_one`,`primary_skill_two`,`primary_skill_three`,`secondary_skill_one`,`secondary_skill_two`, `secondary_skill_three`,`language_one`, `language_one_level`, `language_two`, `language_two_level`,`os`, `os_level`) VALUES (?, ?, ?, ?, ?, ?, ?, ? ,? , ?, ?, ?, ?)");
            ps.setString(1, employee.getEmp_id());
            ps.setString(2, employee.getPrimary_skill_one());
            ps.setString(3, employee.getPrimary_skill_two());
            ps.setString(4, employee.getPrimary_skill_three());
            ps.setString(5, employee.getSecondary_skill_one());
            ps.setString(6, employee.getSecondary_skill_two());
            ps.setString(7, employee.getSecondary_skill_three());
            ps.setString(8, employee.getLanguage_one());
            ps.setString(9, employee.getLanguage_one_level());
            ps.setString(10, employee.getLanguage_two());
            ps.setString(11, employee.getLanguage_two_level());
            ps.setString(12, employee.getOs());
            ps.setString(13, employee.getOs_level());
            
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
    public int updateSkillSet(SkillSet skillSet) {
        if (skillSet == null) {
            return -1;
        }

        

        Connection con = GetCon.getConnection();

        if (con == null) {
            return -1;
        }

        PreparedStatement ps = null;
        int status = -1;

        try {

            ps = con.prepareStatement("UPDATE `" + tableName + "` SET `primary_skill_one` = ?,`primary_skill_two` = ?,`primary_skill_three` = ?,`secondary_skill_one` = ?,`secondary_skill_two` = ?, `secondary_skill_three` = ?,`language_one` = ?, `language_one_level` = ?, `language_two` = ?, `language_two_level` = ?,`os` = ?, `os_level` = ? WHERE `emp_id`= ?");

            ps.setString(1, skillSet.getPrimary_skill_one());
            ps.setString(2, skillSet.getPrimary_skill_two());
            ps.setString(3, skillSet.getPrimary_skill_three());
            ps.setString(4, skillSet.getSecondary_skill_one());
            ps.setString(5, skillSet.getSecondary_skill_two());
            ps.setString(6, skillSet.getSecondary_skill_three());
            ps.setString(7, skillSet.getLanguage_one());
            ps.setString(8, skillSet.getLanguage_one_level());
            ps.setString(9, skillSet.getLanguage_two());
            ps.setString(10, skillSet.getLanguage_two_level());
            ps.setString(11, skillSet.getOs());
            ps.setString(12, skillSet.getOs_level());
            ps.setString(13,skillSet.getEmp_id());
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
    public int updateSkillSet(String key, String value, String employeeId) {
       return updateTableKeyValue(key, value, employeeId);
    }

    @Override
    public boolean deleteSkillSet(String employeeId) {
        return deleteTableByEmpId(employeeId);
    }
    
}
