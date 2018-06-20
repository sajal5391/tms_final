/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lge.tms2.db;

import com.lge.tms2.wrapper.EmpInfo;
import java.util.List;

/**
 *
 * @author ramesh.nagarajan
 */
public interface EmployeeDAO {
    String TABLE_EMPLYOEE = "emp_details";

    public List<EmpInfo> getAllEmpInfo();

    public int insertEmpInfo(EmpInfo employee);

    public EmpInfo getEmpInfo(String employeeId);

    public int updateEmpInfo(EmpInfo employee);
    
    public int updateEmpInfo(String key, String value, String employeeId);

    public boolean deleteEmpInfo(String employeeId);
}
