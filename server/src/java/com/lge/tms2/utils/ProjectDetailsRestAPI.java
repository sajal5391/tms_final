/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lge.tms2.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lge.tms2.db.ProjectDetailsDAOImpl;
import com.lge.tms2.db.TaskDAOImpl;
import com.lge.tms2.wrapper.ProjectDetails;
import com.lge.tms2.wrapper.ProjectJson;
import com.lge.tms2.wrapper.Tasks;
import java.lang.reflect.Type;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ramesh.nagarajan
 */

@Path("project")
public class ProjectDetailsRestAPI {

    //test method
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String dummy() {
        System.out.println("Project Details:");
        return "Available RestAPI are:\n"
                + "GET -> all -> List all Employee Projects\n"
                + "GET -> /{empid} -> List all projects of an Employee\n"
                + "POST -> addprojectslist -> Add Multiple Employee information into table\n"
                + "POST -> projects -> Show an Employee Information based on empID\n"
                + "POST -> addprojects -> Add Employee information into table\n"
                + "POST -> delprojects -> Delete Employee information from table\n"
                + "POST -> updateprojects-> Update Employee information from table\n"
                ;
                
    }
    
    
    @Path("all")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllProjectDetails() {
        String message = "";
        try {
            List<ProjectDetails> list = new ProjectDetailsDAOImpl().getAllProjectDetails();
            if (list != null) {
                if (!list.isEmpty()) {
                    Gson g = new Gson();
                    Type listType = new TypeToken<List<ProjectDetails>>() {
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
    public String getEmpProjectDetails(@PathParam("empid") int empid) {
        String message = "";
        ProjectJson proJson = new ProjectJson();
        try {
            proJson.setProjects(new ProjectDetailsDAOImpl().getAllProjectDetails("emp_id like '%"+ empid+"%'"));
            proJson.setProjectTasks(new TaskDAOImpl().getAllTasks());//"emp_id like '%"+ empid+"%'")
            if (proJson.getProjectTasks() != null &&  proJson.getProjects() != null) {                
                Gson g = new Gson();
               /* Type listType = new TypeToken<List<ProjectJson>>() {
                }.getType();*/
                return g.toJson(proJson, ProjectJson.class);                
            } else {
                message = Util.toJson("Success", "Something wrong with DB Connection");
            }
        } catch (Exception e) {
            message = Util.toJson("Failure", e.getMessage());
        }
        return message;
    }
    
    
    @Path("addprojectslist")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String addAllProjectDetails(String input) {
        String message = "";
        if (input != null) {
            List<ProjectDetails> infoList = null;
            try {
                infoList = Util.getAllProjectDetails(input);
            } catch (Exception e) {
                message = e.getMessage();
            }
            if (infoList != null) {
                ProjectDetailsDAOImpl dao = new ProjectDetailsDAOImpl();
                for (ProjectDetails info : infoList) {
                    dao.insertProjectDetails(info);
                }

            } else {
                return message;
            }
        }
        return message;
    }

    @Path("projects")
    @POST // Get the Information
    @Produces(MediaType.TEXT_PLAIN)
    public String getProjectDetails(String input) {
        String message = "";
        if (input != null) {
            ProjectDetails info = null;
            try {
                info = Util.getProjectDetails(input);
                if (info != null) {
                    info = new ProjectDetailsDAOImpl().getProjectDetails(info);
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

    @Path("addprojects")
    @POST //Add the informaiton
    @Produces(MediaType.TEXT_PLAIN)
    public String addProjectDetails(String input) {
        String message = "";
        if (input != null) {
            ProjectDetails info = null;
            try {
                info = Util.getProjectDetails(input);
                if (info != null && !info.getEmp_id().isEmpty()) {
                    int status = new ProjectDetailsDAOImpl().insertProjectDetails(info);
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

    
    
    @Path("delprojects")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteProjectDetails(String input) {
         String message = "";
        if (input != null) {
            ProjectDetails info = null;
            try {
                info = Util.getProjectDetails(input);
                if (info != null) {
                    boolean status = new ProjectDetailsDAOImpl().deleteProjectDetails(info.getEmp_id());
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
    
    
    @Path("updateprojects")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String updateProjectDetails(String input) {
         String message = "";
        if (input != null) {
            ProjectDetails info = null;
            try {
                info = Util.getProjectDetails(input);
                if (info != null) {
                    int status = new ProjectDetailsDAOImpl().updateProjectDetails(info);
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
