/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lge.tms2.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lge.tms2.db.SkillSetDAOImpl;
import com.lge.tms2.wrapper.SkillSet;
import java.lang.reflect.Type;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ramesh.nagarajan
 */

@Path("skills")
public class SkillSetRestAPI {

    //test method
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String dummy() {
        System.out.println("Skill Sets:");
        return "Available RestAPI are:\n"
                + "GET -> allskillset -> List all the Skillset Information\n"
                + "POST -> addskillsetlist -> Add Multiple Skillset information into table\n"
                + "POST -> skillset -> Show an Skillset Information based on empID\n"
                + "POST -> addskillset -> Add Skillset information into table\n"
                + "POST -> delskillset -> Delete Skillset information from table\n"
                + "POST -> updateskillset-> Update Skillset information from table\n"
                ;
                
    }

    @Path("allskillset")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllSkillSet() {
        String message = "";
        try {
            List<SkillSet> list = new SkillSetDAOImpl().getAllSkillSet();
            if (list != null) {
                if (!list.isEmpty()) {
                    Gson g = new Gson();
                    Type listType = new TypeToken<List<SkillSet>>() {
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
    
    
    @Path("addskillsetlist")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String addAllSkillSet(String input) {
        String message = "";
        if (input != null) {
            List<SkillSet> infoList = null;
            try {
                infoList = Util.getAllSkillSet(input);
            } catch (Exception e) {
                message = e.getMessage();
            }
            if (infoList != null) {
                SkillSetDAOImpl dao = new SkillSetDAOImpl();
                for (SkillSet info : infoList) {
                    dao.insertSkillSet(info);
                }

            } else {
                return message;
            }
        }
        return message;
    }

    @Path("skillset")
    @POST // Get the Information
    @Produces(MediaType.TEXT_PLAIN)
    public String getSkillSet(String input) {
        String message = "";
        if (input != null) {
            SkillSet info = null;
            try {
                info = Util.getSkillSet(input);
                if (info != null) {
                    info = new SkillSetDAOImpl().getSkillSet(info);
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

    @Path("addskillset")
    @POST //Add the informaiton
    @Produces(MediaType.TEXT_PLAIN)
    public String addSkillSet(String input) {
        String message = "";
        if (input != null) {
            SkillSet info = null;
            try {
                info = Util.getSkillSet(input);
                if (info != null && !info.getEmp_id().isEmpty()) {
                    int status = new SkillSetDAOImpl().insertSkillSet(info);
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

    
    
    @Path("delskillset")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteSkillSet(String input) {
         String message = "";
        if (input != null) {
            SkillSet info = null;
            try {
                info = Util.getSkillSet(input);
                if (info != null) {
                    boolean status = new SkillSetDAOImpl().deleteSkillSet(info.getEmp_id());
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
    
    
    @Path("updateskillset")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String updateSkillSet(String input) {
         String message = "";
        if (input != null) {
            SkillSet info = null;
            try {
                info = Util.getSkillSet(input);
                if (info != null) {
                    int status = new SkillSetDAOImpl().updateSkillSet(info);
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
