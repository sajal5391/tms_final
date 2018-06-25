/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lge.tms2.db;

import com.lge.tms2.utils.Util;
import com.lge.tms2.wrapper.EmpInfo;
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
public class EmployeeDAOImpl extends DataBase implements EmployeeDAO {   

    public EmployeeDAOImpl() {        
        tableName = TABLE_EMPLYOEE;
    }
        
    @Override
    public List<EmpInfo> getAllEmpInfo() {
        return getAllEmpInfo("");
    }

    public List<EmpInfo> getAllEmpInfo(String queryString) {
        Connection con = null;
        ArrayList<EmpInfo> list = new ArrayList<EmpInfo>();
        Util.Log("getEmpInfoList -> : query" + queryString);
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
                EmpInfo empInfo = new EmpInfo();
                readFromResultSet(empInfo, result);
                list.add(empInfo);
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

    @Override
    public EmpInfo getEmpInfo(String query) {
        return getEmpInfo(new EmpInfo(), query);
    }
     
    public EmpInfo getEmpInfo(EmpInfo info) {
        return getEmpInfo(info , "emp_id like '%" + info.getEmp_id() + "%' OR emp_email = '" + info.getEmp_email() + "'");
    }
    
    public EmpInfo getEmpInfo(EmpInfo info , String query) {
        Connection con = null;        
        Util.Log("getEmpInfoList -> : empId" + query);
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
                readFromResultSet(info, result);
            } else {
                return null;
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
        return info;
    }
    
    
    
    @Override
    public int insertEmpInfo(EmpInfo info) {
        int status = -1;
        if (info == null || Util.isEmpty(info.getEmp_email())) {
            return status;
        }
        Connection con = GetCon.getConnection();

        if (con == null) {
            return status;
        }

        PreparedStatement ps = null;
        try {
            System.out.println("INSERT INTO `" + tableName + "` (`design` = " + info.getEmp_designation() + ", `domain` = " + info.getEmp_domain() + ", `email`=" + info.getEmp_email() + ", `emp_id`=" + info.getEmp_id() + ",  `grpname`=" + info.getEmp_group() + ", `empskil1`=" + info.getApprover_level_one() + ", `empskil2`=" + info.getApprover_level_two() + ")");
            ps = con.prepareStatement("INSERT INTO `" + tableName + "` (`emp_id`,`emp_name`,`emp_email`,`emp_image`,`emp_designation`,`emp_number`, `emp_group`,`emp_domain`, `approver_level_one`, `approver_level_two`) VALUES (?, ?, ?, ?, ?, ?, ?, ? ,? , ?)");
            ps.setString(1, info.getEmp_id());
            ps.setString(2, info.getEmp_name());
            ps.setString(3, info.getEmp_email());
            ps.setString(4, info.getEmp_image());
            ps.setString(5, info.getEmp_designation());
            ps.setString(6, info.getEmp_number());
            ps.setString(7, info.getEmp_group());
            ps.setString(8, info.getEmp_domain());
            ps.setString(9, info.getApprover_level_one());
            ps.setString(10, info.getApprover_level_two());
            
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
    public int updateEmpInfo(EmpInfo info) {
        if (info == null) {
            return -1;
        }

        String empID = String.valueOf(info.getEmp_id());

        Connection con = GetCon.getConnection();

        if (con == null) {
            return -1;
        }

        PreparedStatement ps = null;
        int status = -1;

        try {

            ps = con.prepareStatement("UPDATE `" + tableName + "` SET `emp_designation` = ?, `emp_domain`= ?, `emp_email`= ?, `emp_group`= ?, `approver_level_one`= ?, `approver_level_two` = ? WHERE `emp_id`= '" + empID + "'");

            ps.setString(1, info.getEmp_designation());
            ps.setString(2, info.getEmp_domain());
            ps.setString(3, info.getEmp_email());
            ps.setString(4, info.getEmp_group());
            ps.setString(5, info.getApprover_level_one());
            ps.setString(6, info.getApprover_level_two());
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
    public int updateEmpInfo(String key, String value, String empId) {
       return updateTableKeyValue(key, value, empId);
    }

    @Override
    public boolean deleteEmpInfo(String empId) {        
        return deleteTableByEmpId(empId);
    }

}
