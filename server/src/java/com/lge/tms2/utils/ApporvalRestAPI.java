/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lge.tms2.utils;

import com.lge.tms2.db.EmployeeDAOImpl;
import com.lge.tms2.db.LogEffortDAOImpl;
import com.lge.tms2.wrapper.EmpInfo;
import com.lge.tms2.wrapper.LogEffort;
import com.lge.tms2.wrapper.json.ApprovalList;
import com.lge.tms2.wrapper.json.EffortInputJson;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ramesh.nagarajan
 */
@Path("approval")
public class ApporvalRestAPI {

    //test method
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String dummy() {
        System.out.println("approval");
        return "Available RestAPI are:\n"
                + "GET -> {app_email} -> List all the Employee Information\n"                
                + "POST -> update-> Update Employee information from table\n";

    }

    @Path("/{approvalEmail}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getApprovalList(@PathParam("approvalEmail") String approvalEmail) {
         EmployeeDAOImpl dao = new EmployeeDAOImpl();
         List<EmpInfo> empList = dao.getAllEmpInfo("approver_level_one = '"+approvalEmail+"' OR approver_level_two = '"+approvalEmail+"'");
         ApprovalList approval = new ApprovalList();
         for(EmpInfo info : empList) {
             List<EffortInputJson> list = new LogEffortDAOImpl().getEffortInputJson(info, new StringBuilder());
             approval.adddEmpEfforts(list);
         }
        return  Util.toJson("true", approval, null);
    }   
    

    @Path("update")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String updateLogEffort(String input) {
        String message = "";
        if (input != null) {
            LogEffort info = null;
            try {
                info = Util.getLogEffort(input);
                if (info != null) {
                    int status = new LogEffortDAOImpl().updateLogEffort(info);
                    if (status > 0) {
                        message = Util.toJson("Success", "Employee Info Updated emp_id:" + info.getEmp_id());
                    } else {
                        message = Util.toJson("Failure", "Failed to update emp_id:" + info.getEmp_id());
                    }

                } else {
                    message = Util.toJson("Failure", "Something wrong with DB Connection");
                }
            } catch (Exception e) {
                message = Util.toJson("Failure", e.getMessage());
            }
        }
        return message;
    }

}
