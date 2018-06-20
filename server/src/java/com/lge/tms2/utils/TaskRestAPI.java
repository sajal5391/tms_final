/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lge.tms2.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lge.tms2.db.TaskDAOImpl;
import com.lge.tms2.wrapper.Tasks;
import java.lang.reflect.Type;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ramesh.nagarajan
 */

@Path("task")
public class TaskRestAPI {

    //test method
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String dummy() {
        System.out.println("Task name:");
        return "Available RestAPI are:\n"
                + "GET -> alltask -> List all the Employee Information\n"
                + "POST -> addtasklist -> Add Multiple Employee information into table\n"
                + "POST -> taskInfo -> Show an Employee Information based on empID\n"
                + "POST -> addtask -> Add Employee information into table\n"
                + "POST -> deletask -> Delete Employee information from table\n"
                + "POST -> updatetask-> Update Employee information from table\n"
                ;
                
    }

    @Path("alltask")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllTask() {
        String message = "";
        try {
            List<Tasks> list = new TaskDAOImpl().getAllTasks();
            if (list != null) {
                if (!list.isEmpty()) {
                    Gson g = new Gson();
                    Type listType = new TypeToken<List<Tasks>>() {
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
    
    
    @Path("addtasklist")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String addAllTasks(String input) {
        String message = "";
        if (input != null) {
            List<Tasks> infoList = null;
            try {
                infoList = Util.getAllTasks(input);
            } catch (Exception e) {
                message = e.getMessage();
            }
            if (infoList != null) {
                TaskDAOImpl dao = new TaskDAOImpl();
                for (Tasks info : infoList) {
                    dao.insertTasks(info);
                }

            } else {
                return message;
            }
        }
        return message;
    }

    @Path("taskInfo")
    @POST // Get the Information
    @Produces(MediaType.TEXT_PLAIN)
    public String getTasks(String input) {
        String message = "";
        if (input != null) {
            Tasks info = null;
            try {
                info = Util.getTasks(input);
                if (info != null) {
                    info = new TaskDAOImpl().getTasks(info);
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

    @Path("addtask")
    @POST //Add the informaiton
    @Produces(MediaType.TEXT_PLAIN)
    public String addTasks(String input) {
        String message = "";
        if (input != null) {
            Tasks info = null;
            try {
                info = Util.getTasks(input);
                if (info != null && info.getProject_type() != null && info.getTasks() != null) {
                    int status = new TaskDAOImpl().insertTasks(info);
                    System.out.println("status: " +status);
                    if (status > 0) {
                        message = Util.toJson("Success", "Employee Added");
                    } else {
                        message = Util.toJson("Failure", "Insert Failed");
                    }

                } else {
                    message = Util.toJson("Failure", "Task information not available");
                }
            } catch (Exception e) {
                message = Util.toJson("Failure", e.getMessage());
            }

        }
        return message;
    }

    
    
    @Path("deletask")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteTasks(String input) {
         String message = "";
        if (input != null) {
            Tasks info = null;
            try {
                info = Util.getTasks(input);
                if (info != null) {
                    boolean status = new TaskDAOImpl().deleteTasks(info.getProject_type() , info.getTasks());
                    if(status) {
                        message = Util.toJson("Success", "Employee Info deleted projecType:" + info.getProject_type());
                    } else {
                        message = Util.toJson("Failure", "Failed to delete projecType:" + info.getProject_type());
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
    
    
    @Path("updatetask")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String updateTasks(String input) {
         String message = "";
        if (input != null) {
            Tasks info = null;
            try {
                info = Util.getTasks(input);
                if (info != null) {
                    int status = new TaskDAOImpl().updateTasks(info);
                    if(status > 0) {
                        message = Util.toJson("Success", "Employee Info Updated getProject_type:" + info.getProject_type());
                    } else {
                        message = Util.toJson("Failure", "Failed to update getProject_type:" + info.getProject_type());
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
