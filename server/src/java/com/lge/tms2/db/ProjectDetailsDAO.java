/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lge.tms2.db;

import com.lge.tms2.wrapper.ProjectDetails;
import com.lge.tms2.wrapper.SkillSet;
import java.util.List;

/**
 *
 * @author ramesh.nagarajan
 */
public interface ProjectDetailsDAO {
    String TABLE_PROJECT_DETAILS = "project_details";
    public List<ProjectDetails> getAllProjectDetails();

    public ProjectDetails getProjectDetails(ProjectDetails pd);
    
    public ProjectDetails getProjectDetails(String employeeId);
    
    public int insertProjectDetails(ProjectDetails employee);
    
    public int updateProjectDetails(ProjectDetails employee);
    
    public int updateProjectDetails(String key, String value, String employeeId);

    public boolean deleteProjectDetails(String employeeId);
}
