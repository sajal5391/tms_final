/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lge.tms2.wrapper;

import java.io.Serializable;

/**
 *
 * @author ramesh.nagarajan
 */
public class JoinEmpSkills implements Serializable {  
    private static final long serialVersionUID = 1L; 
    private EmpInfo empinfo;
    private SkillSet skill;

    public JoinEmpSkills(EmpInfo info, SkillSet skill) {
        this.empinfo = info;
        this.skill = skill;
    }

    public JoinEmpSkills() {
    }

    /**
     * @return the empinfo
     */
    public EmpInfo getEmpinfo() {
        return empinfo;
    }

    /**
     * @param empinfo the empinfo to set
     */
    public void setEmpinfo(EmpInfo empinfo) {
        this.empinfo = empinfo;
    }

    /**
     * @return the skill
     */
    public SkillSet getSkill() {
        return skill;
    }

    /**
     * @param skill the skill to set
     */
    public void setSkill(SkillSet skill) {
        this.skill = skill;
    }
}
