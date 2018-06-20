/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lge.tms2.db;

import com.lge.tms2.wrapper.SkillSet;
import java.util.List;

/**
 *
 * @author ramesh.nagarajan
 */
public interface SkillSetDAO{
    String TABLE_EMP_SKILLS = "emp_skillset";
    public List<SkillSet> getAllSkillSet();

    public int insertSkillSet(SkillSet employee);

    public SkillSet getSkillSet(String employeeId);

    public int updateSkillSet(SkillSet employee);
    
    public int updateSkillSet(String key, String value, String employeeId);

    public boolean deleteSkillSet(String employeeId);
}
