/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lge.tms2.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lge.tms2.db.EmployeeDAOImpl;
import com.lge.tms2.wrapper.EmpInfo;
import java.lang.reflect.Type;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ramesh.nagarajan
 */

@Path("employee")
public class RestAPI {

    //test method
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String dummy() {
        System.out.println("Emp name null:");
        return "Available RestAPI are:\n"
                + "GET -> allempinfo -> List all the Employee Information\n"
                + "POST -> addemplist -> Add Multiple Employee information into table\n"
                + "POST -> empinfo -> Show an Employee Information based on empID\n"
                + "POST -> addempinfo -> Add Employee information into table\n"
                + "POST -> delempinfo -> Delete Employee information from table\n"
                + "POST -> updateempinfo-> Update Employee information from table\n"
                ;
                
    }

    @Path("allempinfo")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllEmpInfo() {
        String message = "";
        try {
            List<EmpInfo> list = new EmployeeDAOImpl().getAllEmpInfo();
            if (list != null) {
                if (!list.isEmpty()) {
                    Gson g = new Gson();
                    Type listType = new TypeToken<List<EmpInfo>>() {
                    }.getType();
                    return g.toJson(list, listType);
                } else {
                    message = Util.toJson("Success", "List is Empty");
                }
            } else {
                message = Util.toJson("Success", "Something wrong with DB Connection");
            }
        } catch (Exception e) {
            message = Util.toJson("Failure", e.getMessage());
        }
        return message;
    }
    
    
    @Path("addemplist")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String addAllEmpInfo(String input) {
        String message = "";
        if (input != null) {
            List<EmpInfo> infoList = null;
            try {
                infoList = Util.getAllInfo(input);
            } catch (Exception e) {
                message = e.getMessage();
            }
            if (infoList != null) {
                EmployeeDAOImpl dao = new EmployeeDAOImpl();
                for (EmpInfo info : infoList) {
                    dao.insertEmpInfo(info);
                }

            } else {
                return message;
            }
        }
        return message;
    }

    @Path("empinfo")
    @POST // Get the Information
    @Produces(MediaType.TEXT_PLAIN)
    public String getEmpInfo(String input) {
        String message = "";
        if (input != null) {
            EmpInfo info = null;
            try {
                info = Util.getInfo(input);
                if (info != null) {
                    info = new EmployeeDAOImpl().getEmpInfo(info);
                    if (info != null) {
                        message = Util.toJson(info);
                    } else {
                        message = Util.toJson("Success", "No Result Found");
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

    @Path("addempinfo")
    @POST //Add the informaiton
    @Produces(MediaType.TEXT_PLAIN)
    public String addEmpInfo(String input) {
        String message = "";
        if (input != null) {
            EmpInfo info = null;
            try {
                info = Util.getInfo(input);
                if (info != null && !info.getEmp_id().isEmpty()) {
                    int status = new EmployeeDAOImpl().insertEmpInfo(info);
                    System.out.println("status: " +status);
                    if (status > 0) {
                        message = Util.toJson("Success", "Employee Added");
                    } else {
                        message = Util.toJson("Failure", "Insert Failed");
                    }

                } else {
                    message = Util.toJson("Failure", "Employee information not available");
                }
            } catch (Exception e) {
                message = Util.toJson("Failure", e.getMessage());
            }

        }
        return message;
    }

    
    
    @Path("delempinfo")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteEmpInfo(String input) {
         String message = "";
        if (input != null) {
            EmpInfo info = null;
            try {
                info = Util.getInfo(input);
                if (info != null) {
                    boolean status = new EmployeeDAOImpl().deleteEmpInfo(info.getEmp_id());
                    if(status) {
                        message = Util.toJson("Success", "Employee Info deleted emp_id:" + info.getEmp_id());
                    } else {
                        message = Util.toJson("Failure", "Failed to delete emp_id:" + info.getEmp_id());
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
    
    
    @Path("updateempinfo")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String updateEmpInfo(String input) {
         String message = "";
        if (input != null) {
            EmpInfo info = null;
            try {
                info = Util.getInfo(input);
                if (info != null) {
                    int status = new EmployeeDAOImpl().updateEmpInfo(info);
                    if(status > 0) {
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
