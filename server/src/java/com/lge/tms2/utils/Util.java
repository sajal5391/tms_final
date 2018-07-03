/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lge.tms2.utils;

import com.lge.tms2.wrapper.json.JoinEmpSkills;
import com.lge.tms2.wrapper.json.EffortInputJson;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lge.tms2.wrapper.*;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ramesh.nagarajan
 */
public class Util {

    private static String sServletPath;

    public static void setServletPath(String servletPath) {
        sServletPath = servletPath;
    }

    public static String getServletPath() {
        return sServletPath;
    }

    public static boolean isEmpty(String value) {
        return (value == null || value.trim().equals(""));
    }

    public static EmpInfo getInfo(String value) {
        Gson g = new Gson();
        return (EmpInfo) g.fromJson(value, EmpInfo.class);
    }

    public static SkillSet getSkillSet(String value) {
        Gson g = new Gson();
        return (SkillSet) g.fromJson(value, SkillSet.class);
    }

    public static Tasks getTasks(String value) {
        Gson g = new Gson();
        return (Tasks) g.fromJson(value, Tasks.class);
    }

    public static LogEffort getLogEffort(String value) {
        Gson g = new Gson();
        return (LogEffort) g.fromJson(value, LogEffort.class);
    }

    public static EffortInputJson getEffortJson(String value) {
        Gson g = new Gson();
        return (EffortInputJson) g.fromJson(value, EffortInputJson.class);
    }

    public static ProjectDetails getProjectDetails(String value) {
        Gson g = new Gson();
        return (ProjectDetails) g.fromJson(value, ProjectDetails.class);
    }

    public static List<EmpInfo> getAllInfo(String value) {
        Gson g = new Gson();
        Type listType = new TypeToken<List<EmpInfo>>() {
        }.getType();
        return (List<EmpInfo>) g.fromJson(value, listType);
    }

    public static List<JoinEmpSkills> getAllEmpSkills(String value) {
        Gson g = new Gson();
        Type listType = new TypeToken<List<JoinEmpSkills>>() {
        }.getType();
        return (List<JoinEmpSkills>) g.fromJson(value, listType);
    }

    public static List<SkillSet> getAllSkillSet(String value) {
        Gson g = new Gson();
        Type listType = new TypeToken<List<SkillSet>>() {
        }.getType();
        return (List<SkillSet>) g.fromJson(value, listType);
    }

    public static List<Tasks> getAllTasks(String value) {
        Gson g = new Gson();
        Type listType = new TypeToken<List<Tasks>>() {
        }.getType();
        return (List<Tasks>) g.fromJson(value, listType);
    }

    public static List<LogEffort> getAllLogEffort(String value) {
        Gson g = new Gson();
        Type listType = new TypeToken<List<LogEffort>>() {
        }.getType();
        return (List<LogEffort>) g.fromJson(value, listType);
    }

    public static List<ProjectDetails> getAllProjectDetails(String value) {
        Gson g = new Gson();
        Type listType = new TypeToken<List<ProjectDetails>>() {
        }.getType();
        return (List<ProjectDetails>) g.fromJson(value, listType);
    }

    public static String toJson(EmpInfo info) throws Exception {
        Gson g = new Gson();
        return g.toJson(info, EmpInfo.class);
    }

    public static String toJson(Tasks info) throws Exception {
        Gson g = new Gson();
        return g.toJson(info, Tasks.class);
    }

    public static String toJson(SkillSet info) throws Exception {
        Gson g = new Gson();
        return g.toJson(info, SkillSet.class);
    }

    public static String toJson(LogEffort info) throws Exception {
        Gson g = new Gson();
        return g.toJson(info, LogEffort.class);
    }

    public static String toJson(ProjectDetails info) throws Exception {
        Gson g = new Gson();
        return g.toJson(info, ProjectDetails.class);
    }

    public static String toJson(JoinEmpSkills info) throws Exception {
        Gson g = new Gson();
        return g.toJson(info, JoinEmpSkills.class);
    }

    public static void Log(String con_Called) {
        System.out.println(con_Called);
    }

    public static String toJson(String status, String message) {
        Gson g = new Gson();
        Result r = new Result(status, message);
        return g.toJson(r, Result.class);
    }

    public static String toJson(String status, String data, String message) {
        Gson g = new Gson();
        Result r = new Result(status, data, message);
        return g.toJson(r, Result.class);
    }

    public static String toJson(String status, Object data, String message) {
        Gson g = new Gson();
        Result r = new Result(status, data, message);
        return g.toJson(r, Result.class);
    }

    public static String removeUnwantedGerritStr(String str) {
        if (!isEmpty(str)) {
            try {
                str = str.replaceAll("\"", "");
                str = str.replaceAll("\\[", "");
                str = str.replaceAll("\\]", "");
            } catch (Exception e) {

            }
            str = str.trim();
        }
        return str;
    }

    public static String removeSquareBrStr(String str) {
        if (!isEmpty(str)) {
            try {
                str = str.replaceAll("\\[", "");
                str = str.replaceAll("\\]", "");
                str = str.replaceAll(",", "\n");
            } catch (Exception e) {

            }
            str = str.trim();
        }
        return str;
    }

    public static String addHoursMins(String time1, String time2) {
        String date3 = null;
        try {
            if (isEmpty(time1) && !isEmpty(time2)) {
                return time2;
            } else if (isEmpty(time2) && !isEmpty(time1)) {
                return time1;
            } else if (isEmpty(time2) && isEmpty(time1)) {
                return "";
            } else {
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

                Date date1 = timeFormat.parse(time1);
                Date date2 = timeFormat.parse(time2);

                long sum = date1.getTime() + date2.getTime();

                date3 = timeFormat.format(new Date(sum));
                System.out.println("The sum is " + date3);
            }

        } catch (ParseException ex) {

        }
        return date3;
    }

    public static Date getStringToHours(String date) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        try {
            return timeFormat.parse(date);
        } catch (ParseException ex) {
            return null;
        }
    }

    public static String getHoursToString(Date date) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        try {
            return timeFormat.format(date);
        } catch (Exception ex) {
            return null;
        }
    }

    public static Date addNoofDaysToDate(Date date, int days) {
        if (days > 0 && days < 7) {
            GregorianCalendar g = new GregorianCalendar();
            g.setTime(date);
            g.add(Calendar.DATE, days);
            return g.getTime();
        }
        return date;
    }

    public static String getDateFormat(Date date) {
        if (date != null) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            return format.format(date);
        } else {
            return null;
        }
    }

    public static String getDateFormatEMMMdd(Date date) {
        if (date != null) {
            SimpleDateFormat format = new SimpleDateFormat("E MMM dd yyyy");
            return format.format(date);
        } else {
            return null;
        }
    }

    public static String getStringFromEMMMddToDDMMYYYY(String date) {
        try {
            if (date != null) {
                if (date.length() > 10) {
                    SimpleDateFormat format = new SimpleDateFormat("E MMM dd yyyy");
                    return getDateFormat(format.parse(date));
                }
            }
        } catch (ParseException ex) {

        }
        return date;
    }

    public static Date getDateFormatDDMMYYYY(Date date) {
        if (date != null) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            try {
                return format.parse(format.format(date));
            } catch (ParseException ex) {
                return date;
            }
        } else {
            return date;
        }
    }

    public static Date getDateFromSQL(String date) {
        if (date != null) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            try {
                return format.parse(date);
            } catch (ParseException ex) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static String getDateStringofWeek(int year, int week) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, week);
        return getDateFormat(cal.getTime());
    }

    public static int getWeekNo(Date date) {
        return Integer.parseInt(new SimpleDateFormat("w").format(date));
    }
    
    public static int getWeekNo(String date) {
        return Integer.parseInt(new SimpleDateFormat("w").format(getDateFromSQL(date)));
    }
    
    
    public static boolean isWeekHigher(String date) {
        return (getWeekNo(date) <= getWeekNo(new Date()));        
    }
    
}
