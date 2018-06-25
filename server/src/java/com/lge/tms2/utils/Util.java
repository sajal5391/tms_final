/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lge.tms2.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lge.tms2.wrapper.*;
import java.lang.reflect.Type;
import java.util.List;

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

    public static EmpInfo getInfo(String value){
        Gson g = new Gson();
        return (EmpInfo) g.fromJson(value, EmpInfo.class);
    }
    
    public static SkillSet getSkillSet(String value){
        Gson g = new Gson();
        return (SkillSet) g.fromJson(value, SkillSet.class);
    }
    
    public static Tasks getTasks(String value){
        Gson g = new Gson();
        return (Tasks) g.fromJson(value, Tasks.class);
    }
    
    public static LogEffort getLogEffort(String value){
        Gson g = new Gson();
        return (LogEffort) g.fromJson(value, LogEffort.class);
    }
    
    public static ProjectDetails getProjectDetails(String value){
        Gson g = new Gson();
        return (ProjectDetails) g.fromJson(value, ProjectDetails.class);
    }

    public static List<EmpInfo> getAllInfo(String value)  {      
        Gson g = new Gson();
        Type listType = new TypeToken<List<EmpInfo>>() { }.getType();
        return (List<EmpInfo>) g.fromJson(value, listType);      
    }  
    
    public static List<JoinEmpSkills> getAllEmpSkills(String value)  {      
        Gson g = new Gson();
        Type listType = new TypeToken<List<JoinEmpSkills>>() { }.getType();
        return (List<JoinEmpSkills>) g.fromJson(value, listType);      
    }  
  
    
    public static List<SkillSet> getAllSkillSet(String value)  {      
        Gson g = new Gson();
        Type listType = new TypeToken<List<SkillSet>>() { }.getType();
        return (List<SkillSet>) g.fromJson(value, listType);      
    }   
    
    public static List<Tasks> getAllTasks(String value)  {      
        Gson g = new Gson();
        Type listType = new TypeToken<List<Tasks>>() { }.getType();
        return (List<Tasks>) g.fromJson(value, listType);      
    }
    
    public static List<LogEffort> getAllLogEffort(String value)  {      
        Gson g = new Gson();
        Type listType = new TypeToken<List<LogEffort>>() { }.getType();
        return (List<LogEffort>) g.fromJson(value, listType);      
    }     
    
    public static List<ProjectDetails> getAllProjectDetails(String value)  {      
        Gson g = new Gson();
        Type listType = new TypeToken<List<ProjectDetails>>() { }.getType();
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
        return  g.toJson(r, Result.class);
    }
    
    public static String toJson(String status, String data, String message) {
        Gson g = new Gson();
        Result r = new Result(status, data, message);
        return  g.toJson(r, Result.class);
    }
}
