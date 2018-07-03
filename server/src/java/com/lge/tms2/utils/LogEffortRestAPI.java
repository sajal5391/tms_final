/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lge.tms2.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lge.tms2.db.LogEffortDAOImpl;
import com.lge.tms2.wrapper.CalendarDate;
import com.lge.tms2.wrapper.EmpInfo;
import com.lge.tms2.wrapper.LogEffort;
import com.lge.tms2.wrapper.json.EffortInputJson;
import com.lge.tms2.wrapper.json.EffortOutputJson;
import java.lang.reflect.Type;
import java.util.Date;
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
                + "POST -> updatelogeffort-> Update Employee information from table\n";

    }

    @Path("/{empid}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getEmpLogEffort(@PathParam("empid") String empid) {
        return getEmpLogEffortJson(empid, null);
    }

    @Path("/{empid}/{date}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getEmpLogEffort(@PathParam("empid") String empid, @PathParam("date") String date) {
        int dateWeekNo = Util.getWeekNo(date);
        int currentWeekNo = Util.getWeekNo(new Date());
        if(dateWeekNo <= currentWeekNo) {
            return getEmpLogEffortJson(empid, date);
        } else {
            return getEmpLogEffortJson(empid, Util.getDateFormat(new Date()));
        }
    }

    @Path("/{empid}/w/{week}/y/{year}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getEmpLogEffort(@PathParam("empid") String empid, @PathParam("year") int year, @PathParam("week") int week) {
        int currentWeekNo = Util.getWeekNo(new Date());
        if(week > currentWeekNo) {
            week = currentWeekNo;
        } 
        return getEmpLogEffortJson(empid, Util.getDateStringofWeek(year, week));
    }

    private String getEmpLogEffortJson(String emp_id, String date) {
        String message = "";
        StringBuilder builder = new StringBuilder();
        if (!Util.isEmpty(date)) {
            builder.append(date);
        } else {

        }
        EffortOutputJson output = new EffortOutputJson();
        try {
            EmpInfo info = new EmpInfo();
            info.setEmp_id(emp_id);
            List<EffortInputJson> list = new LogEffortDAOImpl().getEffortInputJson(info, builder);
            if (list != null && !list.isEmpty()) {
                output.setTime_sheet(list);
                output.setWeekNumber(builder.toString());
                output.setColor_dates(new LogEffortDAOImpl().getAllCalEffort("emp_id like '%" + emp_id + "%'"));
                message = Util.toJson("true", output, null);
            } else {
                message = Util.toJson("false", "Something wrong with DB Connection");
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = Util.toJson("false", e.getMessage());
        }
        return message;
    }

    @Path("/cal/{empid}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getCalEffort(@PathParam("empid") int empid) {
        String message = "";
        try {
            CalendarDate calDate = new LogEffortDAOImpl().getAllCalEffort("emp_id like '%" + empid + "%'");
            if (calDate != null) {
                message = Util.toJson("true", calDate, null);
            } else {
                message = Util.toJson("false", "Something wrong with DB Connection");
            }
        } catch (Exception e) {
            message = Util.toJson("false", e.getMessage());
        }
        return message;
    }

    @Path("/add/{type}")
    @POST //Add the informaiton
    @Produces(MediaType.TEXT_PLAIN)
    public String addLogEffort(String input, @PathParam("type") int type) {
        String message = "";
        System.out.println("type: " + type);
        System.out.println("input: " + input);
        if (input != null) {
            EffortInputJson info = null;
            try {
                if (type >= 0 && type <= 4) {
                    info = Util.getEffortJson(input);
                    if (info != null && !info.getEmp_id().isEmpty()) {
                        info.setFilled_state(Integer.toString(type));
                        LogEffortDAOImpl dao = new LogEffortDAOImpl();
                        List<LogEffort> listEffort = dao.getAllLogEffort("`emp_id` like '%" + info.getEmp_id() + "%' && `iris_date` = '" + info.getIris_date() + "'");

                        if (listEffort == null || listEffort.isEmpty()) {
                            type = 1;
                            Util.Log("Date not availbe for submit, so insert the data");
                        } else {
                            type = 2;
                            Util.Log("Date already availbe for save, so update the data");
                        }

                        if (type == 1 || type == 0) {
                            int status = dao.insertLogEffort(info);
                            if (status > 0) {
                                message = getEmpLogEffortJson(info.getEmp_id(), null);
                            } else {
                                message = Util.toJson("false", "Insert Failed");
                            }
                            Util.Log("message: \n----------------\n" + message + "\n--------------");
                        } else {
                            int status = dao.updateLogEffort(info, listEffort);
                            if (status > 0) {
                                message = getEmpLogEffortJson(info.getEmp_id(), null);
                            } else {
                                message = Util.toJson("false", "Update Failed");
                            }
                            Util.Log("message: \n----------------\n" + message + "\n--------------");
                        }

                    } else {
                        message = Util.toJson("false", "Employee information not available");
                    }
                } else {
                    message = Util.toJson("false", "Invalid Status value parsed");
                }
            } catch (Exception e) {
                message = Util.toJson("false", e.getMessage());
            }

        }
        return message;
    }

    @Path("deletelog")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteLogEffort(String input) {
        String message = "";
        if (input != null) {
            LogEffort info = null;
            try {
                info = Util.getLogEffort(input);
                if (info != null) {
                    boolean status = new LogEffortDAOImpl().deleteLogEffort("`emp_id` like '%" + info.getEmp_id() + "%' and `iris_date` ='" + info.getIris_date() + "'");
                    if (status) {
                        message = Util.toJson("true", "Employee Info deleted emp_id:" + info.getEmp_id() + " and date: " + info.getIris_date(), null);
                    } else {
                        message = Util.toJson("false", "Failed to delete emp_id:" + info.getEmp_id() + " and date: " + info.getIris_date());
                    }

                } else {
                    message = Util.toJson("false", "Something wrong with DB Connection");
                }
            } catch (Exception e) {
                message = Util.toJson("false", e.getMessage());
            }
        }
        return message;
    }

    @Path("/update")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String updateLogEffort(String input) {
        String message = "";
        LogEffort info = null;
        if (input != null) {
            try {
                info = Util.getLogEffort(input);
            } catch (Exception e) {
                message = Util.toJson("false", e.getMessage());
            }
        }
        return updateLogEffort(info);
    }

    @Path("/update/list")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String updateLogEffortList(String input) {
        String message = "";
        List<LogEffort> info = null;
        if (input != null) {

            try {
                Type listType = new TypeToken<List<LogEffort>>() {
                }.getType();
                info = new Gson().fromJson(input, listType);
                StringBuilder builder = new StringBuilder();
            for (LogEffort e : info) {
                String status = updateLogEffort(e);
                if (status.equals("Updated")) {
                    builder.append(status + "\n");
                }
            }
            if (builder.length() > 0) {
                message = Util.toJson("false", builder.toString());
            } else {
                message = Util.toJson("true", "Updated");
            }
            } catch (Exception e) {
                message = Util.toJson("false", e.getMessage());
            }
            

        } else {
            message = Util.toJson("false", "Gson parsing Error");
        }
        return message;

    }

    public String updateLogEffort(LogEffort info) {
        String message = "";
        if (info != null) {
            int status = new LogEffortDAOImpl().updateLogEffort("`filled_state`='" + info.getFilled_state() + "', `reason`='" + info.getComments() + "'", "`emp_id` like '%" + info.getEmp_id() + "%' and `iris_date` = '" + info.getIris_date() + "'");
            if (status > 0) {
                message = Util.toJson("true", "Updated");
            } else {
                message = Util.toJson("false", "Failed to update date:" + info.getIris_date() + " and Emp_id: " + info.getEmp_id());
            }
        } else {
            message = Util.toJson("false", "Something wrong with DB Connection");
        }
        return message;
    }

}
