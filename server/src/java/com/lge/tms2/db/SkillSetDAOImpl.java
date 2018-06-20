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
            ps = con.prepareStatement("INSERT INTO `" + tableName + "` (`emp_id`,`psk_level1`,`psk_level2`,`psk_level3`,`ssk_level1`,`ssk_level2`, `ssk_level3`,`lng1`, `lng1_level`, `lng2`, `lng2_level`,`os`, `os_level`) VALUES (?, ?, ?, ?, ?, ?, ?, ? ,? , ?, ?, ?, ?)");
            ps.setString(1, employee.getEmp_id());
            ps.setString(2, employee.getPsk_level1());
            ps.setString(3, employee.getPsk_level2());
            ps.setString(4, employee.getPsk_level3());
            ps.setString(5, employee.getSsk_level1());
            ps.setString(6, employee.getSsk_level2());
            ps.setString(7, employee.getSsk_level3());
            ps.setString(8, employee.getLng1());
            ps.setString(9, employee.getLng1_level());
            ps.setString(10, employee.getLng2());
            ps.setString(11, employee.getLng2_level());
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

            ps = con.prepareStatement("UPDATE `" + tableName + "` SET `psk_level1` = ?,`psk_level2` = ?,`psk_level3` = ?,`ssk_level1` = ?,`ssk_level2` = ?, `ssk_level3` = ?,`lng1` = ?, `lng1_level` = ?, `lng2` = ?, `lng2_level` = ?,`os` = ?, `os_level` = ? WHERE `emp_id`= ?");

            ps.setString(1, skillSet.getPsk_level1());
            ps.setString(2, skillSet.getPsk_level2());
            ps.setString(3, skillSet.getPsk_level3());
            ps.setString(4, skillSet.getSsk_level1());
            ps.setString(5, skillSet.getSsk_level2());
            ps.setString(6, skillSet.getSsk_level3());
            ps.setString(7, skillSet.getLng1());
            ps.setString(8, skillSet.getLng1_level());
            ps.setString(9, skillSet.getLng2());
            ps.setString(10, skillSet.getLng2_level());
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
