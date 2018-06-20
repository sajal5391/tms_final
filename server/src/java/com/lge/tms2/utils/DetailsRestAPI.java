/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lge.tms2.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lge.tms2.db.EmployeeDAOImpl;
import com.lge.tms2.db.SkillSetDAOImpl;
import com.lge.tms2.wrapper.EmpInfo;
import com.lge.tms2.wrapper.JoinEmpSkills;
import com.lge.tms2.wrapper.Login;
import com.lge.tms2.wrapper.SkillSet;
import java.lang.reflect.Type;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ramesh.nagarajan
 */
@Path("login")
public class DetailsRestAPI {

    //test method
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String dummy() {
        System.out.println("DetailsRestAPI details:");
        return "Available RestAPI are:\n"
                + "GET -> all -> List all Employee details & skills \n"
                + "GET -> /{empid} -> List all details & skills of an Employee\n"
                + "POST -> addemplist -> Add Multiple Employee information into table\n"
                + "POST -> empinfo -> Show an Employee Information based on empID\n"
                + "POST -> addempinfo -> Add Employee information into table\n"
                + "POST -> delempinfo -> Delete Employee information from table\n"
                + "POST -> updateempinfo-> Update Employee information from table\n";

    }

    @Path("all")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllJoinEmpSkills() {
        String message = "";
        try {
            List<JoinEmpSkills> list = new EmployeeDAOImpl().getLeftJoin();
            if (list != null) {
                if (!list.isEmpty()) {
                    Gson g = new Gson();
                    Type listType = new TypeToken<List<JoinEmpSkills>>() {
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
    public String getEmpSkills(@PathParam("empid") int empid) {
        String message = "";
        try {
            List<JoinEmpSkills> list = new EmployeeDAOImpl().getLeftJoin(Integer.toString(empid));
            if (list != null) {
                if (!list.isEmpty()) {
                    Gson g = new Gson();
                    Type listType = new TypeToken<List<JoinEmpSkills>>() {
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

    @Path("emp")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String getJoinEmpSkills(String empDetails) {
        String message = "";
        System.out.println("empDetails: " + empDetails);
        try {
            Gson g = new Gson();
            Login log = g.fromJson(empDetails, Login.class);
            System.out.println(log);
            if (log != null) {
                boolean result = ExecutePythonFromJava.isLogin(log.getUsername(), log.getPassword());
                System.out.println("Login: " + result);
                if (result) {
                    List<JoinEmpSkills> list = new EmployeeDAOImpl().getLeftJoin("emp_details.emp_email like '%" + log.getUsername() + "%'");
                    if (list != null) {
                        if (!list.isEmpty()) {
                            Type listType = new TypeToken<List<JoinEmpSkills>>() {
                            }.getType();
                            return g.toJson(list, listType);
                        } else {
                            message = Util.toJson("Success", "List is Empty");
                        }
                    } else {
                        message = Util.toJson("Failure", "Something wrong with DB Connection");
                    }

                } else {
                    message = Util.toJson("Failure", "Login Failed, Invalid Credential");
                }
            } else {
                message = Util.toJson("Failure", "Gson Failed to load Object");             
            }

        } catch (Exception e) {
            message = Util.toJson("Failure", e.getMessage());
        }
        return message;
    }

    @Path("addemplist")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String addAllJoinEmpSkills(String input) {
        String message = "";
        if (input != null) {
            List<JoinEmpSkills> infoList = null;
            try {
                infoList = Util.getAllEmpSkills(input);
            } catch (Exception e) {
                message = e.getMessage();
            }
            if (infoList != null) {
                EmployeeDAOImpl dao = new EmployeeDAOImpl();
                for (JoinEmpSkills info : infoList) {
                    dao.insertEmpInfo(info.getEmpinfo());
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
            JoinEmpSkills join = null;

            try {
                EmpInfo info = Util.getInfo(input);
                SkillSet skill = null;
                if (info != null) {

                    info = new EmployeeDAOImpl().getEmpInfo(info);
                    skill = new SkillSetDAOImpl().getSkillSet("emp_id = '" + info.getEmp_id() + "'");
                    join = new JoinEmpSkills(info, skill);
                    if (info != null) {
                        message = Util.toJson(join);
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
                    System.out.println("status: " + status);
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
                    if (status) {
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
