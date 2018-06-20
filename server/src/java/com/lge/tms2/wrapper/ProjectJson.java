/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lge.tms2.wrapper;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ramesh.nagarajan
 */
public class ProjectJson implements Serializable {  
    private static final long serialVersionUID = 1L;
    private List<ProjectDetails> projects;
    private List<Tasks> projectTasks;
    private List<String> commonTasks;

    /**
     * @return the projects
     */
    public List<ProjectDetails> getProjects() {
        return projects;
    }

    /**
     * @param projects the projects to set
     */
    public void setProjects(List<ProjectDetails> projects) {
        this.projects = projects;
    }

    /**
     * @return the projectTasks
     */
    public List<Tasks> getProjectTasks() {
        return projectTasks;
    }

    /**
     * @param projectTasks the projectTasks to set
     */
    public void setProjectTasks(List<Tasks> projectTasks) {
        this.projectTasks = projectTasks;
    }

    /**
     * @return the commonTasks
     */
    public List<String> getCommonTasks() {
        return commonTasks;
    }

    /**
     * @param commonTasks the commonTasks to set
     */
    public void setCommonTasks(List<String> commonTasks) {
        this.commonTasks = commonTasks;
    }
}
