/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lge.tms2.wrapper.json;

import com.lge.tms2.utils.Util;
import com.lge.tms2.wrapper.CommonTask;
import com.lge.tms2.wrapper.ProjectDetails;
import com.lge.tms2.wrapper.Tasks;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ramesh.nagarajan
 */
public class ProjectJson implements Serializable {  
    private static final long serialVersionUID = 1L;
    private List<CommonTask> projects;
    private List<CommonTask> projectTasks;
    private List<CommonTask> commonTasks;

    public ProjectJson() {
        projects = new ArrayList<>();
        projectTasks = new ArrayList<>();
        commonTasks = new ArrayList<>();
    }
    
    /**
     * @return the projects
     */
    public List<CommonTask> getProjects() {
        return projects;
    }

    /**
     * @param projects the projects to set
     */
    public void setProjects(List<CommonTask> projects) {
        this.projects = projects;
    }
    
    
    /**
     * @param the value
     */
    public void addProjects(String value) {
        if(!Util.isEmpty(value)) {
            projects.add(new CommonTask(value));
        }
    }

    /**
     * @return the projectTasks
     */
    public List<CommonTask> getProjectTasks() {
        return projectTasks;
    }

    /**
     * @param projectTasks the projectTasks to set
     */
    public void setProjectTasks(List<CommonTask> projectTasks) {
        this.projectTasks = projectTasks;
    }
    
     /**
     * @param the value
     */
    public void addProjectTasks(String value) {
        if(!Util.isEmpty(value)) {
            projectTasks.add(new CommonTask(value));
        }
    }

    /**
     * @return the commonTasks
     */
    public List<CommonTask> getCommonTasks() {
        return commonTasks;
    }

    /**
     * @param commonTasks the commonTasks to set
     */
    public void setCommonTasks(List<CommonTask> commonTasks) {
        this.commonTasks = commonTasks;
    }
    
     /**
     * @param the value
     */
    public void addCommonTasks(String value) {
        if(!Util.isEmpty(value)) {
            commonTasks.add(new CommonTask(value));
        }
    }
}
