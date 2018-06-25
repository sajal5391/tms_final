/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lge.tms2.db;

import com.lge.tms2.utils.Util;
import com.lge.tms2.wrapper.ProjectDetails;
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
public class ProjectDetailsDAOImpl extends DataBase implements ProjectDetailsDAO{

    public ProjectDetailsDAOImpl() {
         tableName = TABLE_PROJECT_DETAILS;
    }    
    
    @Override
    public List<ProjectDetails> getAllProjectDetails() {
        return getAllProjectDetails("");
    }
    
    public List<ProjectDetails> getAllProjectDetails(String query) {
        Connection con = null;
        ArrayList<ProjectDetails> list = new ArrayList<ProjectDetails>();
        Util.Log("getAllProjectDetails -> : query " + query);
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
                ProjectDetails pd = new ProjectDetails();
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
    public ProjectDetails getProjectDetails(ProjectDetails pd) {
       return getProjectDetails(pd , "emp_id = '" + pd.getEmp_id() + "'");
    }
    
    

    @Override
    public ProjectDetails getProjectDetails(String query) {
          return getProjectDetails(new ProjectDetails(), query);
    }
     
    public ProjectDetails getProjectDetails(ProjectDetails pd,String query) {
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
                readFromResultSet(pd, result);
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
        return pd;
    }

    @Override
    public int insertProjectDetails(ProjectDetails pd) {
        int status = -1;
        if (pd == null || Util.isEmpty(pd.getEmp_id())) {
            return status;
        }
        Connection con = GetCon.getConnection();

        if (con == null) {
            return status;
        }

        PreparedStatement ps = null;
        try {            
            ps = con.prepareStatement("INSERT INTO `" + tableName + "` (`emp_id`,`project_name`,`project_code`,`project_type`,`project_category`,`mc_category`,`start_date`,`end_date`,`project_region`,`project_country`,`project_suffix`) VALUES (?, ?, ?, ?, ?, ?, ?, ? ,? , ?, ?)");
            ps.setString(1, pd.getEmp_id());
            ps.setString(2, pd.getProject_name());
            ps.setString(3, pd.getProject_code());
            ps.setString(4, pd.getProject_type());
            ps.setString(5, pd.getProject_cty());
            ps.setString(6, pd.getMc_cty());
            ps.setString(7, pd.getStart_date());
            ps.setString(8, pd.getEnd_date());
            ps.setString(9, pd.getProject_region());
            ps.setString(10, pd.getProject_country());
            ps.setString(11, pd.getProject_suffix());
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
    public int updateProjectDetails(ProjectDetails pd) {
        if (pd == null) {
            return -1;
        }

        

        Connection con = GetCon.getConnection();

        if (con == null) {
            return -1;
        }

        PreparedStatement ps = null;
        int status = -1;

        try {

            ps = con.prepareStatement("UPDATE `" + tableName + "` SET `project_name`=?,`project_code`=?,`project_type`=?,`project_category`=?,`mc_category`=?,`start_date`=?,`end_date`=?,`project_region`=?,`project_country`=?,`project_suffix`=? WHERE `emp_id`= ?");

            ps.setString(1, pd.getProject_name());
            ps.setString(2, pd.getProject_code());
            ps.setString(3, pd.getProject_type());
            ps.setString(4, pd.getProject_cty());
            ps.setString(5, pd.getMc_cty());
            ps.setString(6, pd.getStart_date());
            ps.setString(7, pd.getEnd_date());
            ps.setString(8, pd.getProject_region());
            ps.setString(9, pd.getProject_country());
            ps.setString(10, pd.getProject_suffix());
            ps.setString(11, pd.getEmp_id());
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
    public int updateProjectDetails(String key, String value, String employeeId) {
        return updateTableKeyValue(key, value, employeeId);
    }

    @Override
    public boolean deleteProjectDetails(String employeeId) {
        return deleteTableByEmpId(employeeId);
    }
    
}
