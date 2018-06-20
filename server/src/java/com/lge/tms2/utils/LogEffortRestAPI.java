/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lge.tms2.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lge.tms2.db.LogEffortDAOImpl;
import com.lge.tms2.wrapper.LogEffort;
import java.lang.reflect.Type;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ramesh.nagarajan
 */

@Path("effort")
public class LogEffortRestAPI {

    //test method
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String dummy() {
        System.out.println("Emp LogEffort:");
        return "Available RestAPI are:\n"
                + "GET -> all -> List all the Employee Information\n"
                + "POST -> addloglist -> Add Multiple Employee information into table\n"
                + "POST -> logeffort -> Show an Employee Information based on empID\n"
                + "POST -> addlogeffort -> Add Employee information into table\n"
                + "POST -> dellogeffort -> Delete Employee information from table\n"
                + "POST -> updatelogeffort-> Update Employee information from table\n"
                ;
                
    }
    
    
    @Path("all")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllLogEffort() {
        String message = "";
        try {
            List<LogEffort> list = new LogEffortDAOImpl().getAllLogEffort();
            if (list != null) {
                if (!list.isEmpty()) {
                    Gson g = new Gson();
                    Type listType = new TypeToken<List<LogEffort>>() {
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
    

    @Path("/{empid}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getEmpLogEffort(@PathParam("empid") int empid) {
        String message = "";
        try {
            List<LogEffort> list = new LogEffortDAOImpl().getAllLogEffort("emp_id like '%"+empid+"%'");
            if (list != null) {
                if (!list.isEmpty()) {
                    Gson g = new Gson();
                    Type listType = new TypeToken<List<LogEffort>>() {
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
    
    
    @Path("addloglist")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String addAllLogEffort(String input) {
        String message = "";
        if (input != null) {
            List<LogEffort> infoList = null;
            try {
                infoList = Util.getAllLogEffort(input);
            } catch (Exception e) {
                message = e.getMessage();
            }
            if (infoList != null) {
                LogEffortDAOImpl dao = new LogEffortDAOImpl();
                for (LogEffort info : infoList) {
                    dao.insertLogEffort(info);
                }

            } else {
                return message;
            }
        }
        return message;
    }

    @Path("logeffort")
    @POST // Get the Information
    @Produces(MediaType.TEXT_PLAIN)
    public String getLogEffort(String input) {
        String message = "";
        if (input != null) {
            LogEffort info = null;
            try {
                info = Util.getLogEffort(input);
                if (info != null) {
                    info = new LogEffortDAOImpl().getLogEffort(info);
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

    @Path("addlogeffort")
    @POST //Add the informaiton
    @Produces(MediaType.TEXT_PLAIN)
    public String addLogEffort(String input) {
        String message = "";
        if (input != null) {
            LogEffort info = null;
            try {
                info = Util.getLogEffort(input);
                if (info != null && !info.getEmp_id().isEmpty()) {
                    int status = new LogEffortDAOImpl().insertLogEffort(info);
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

    
    
    @Path("dellogeffort")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteLogEffort(String input) {
         String message = "";
        if (input != null) {
            LogEffort info = null;
            try {
                info = Util.getLogEffort(input);
                if (info != null) {
                    boolean status = new LogEffortDAOImpl().deleteLogEffort(info.getEmp_id());
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
    
    
    @Path("updatelogeffort")
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
