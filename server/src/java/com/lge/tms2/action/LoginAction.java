/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lge.tms2.action;

import com.lge.tms2.action.beans.LoginForm;
import com.lge.tms2.utils.ExecutePythonFromJava;
import com.lge.tms2.utils.Util;
import com.lge.tms2.wrapper.EmpInfo;
import java.io.File;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author ramesh.nagarajan
 */
public class LoginAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";

    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        LoginForm loginForm = (LoginForm) form;
        
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();
        
        System.out.println("Executed");
       
        if(!Util.isEmpty(password) && !Util.isEmpty(username)) {
            //Check for LDAP authorization
            
            HttpSession ses = request.getSession(true);
            EmpInfo info = new EmpInfo();
            info.setEmp_email(username);
            boolean result = ExecutePythonFromJava.isLogin(username, password);
            ses.setAttribute("empInfo", info);
            return mapping.findForward(SUCCESS);
        } else {
            return mapping.findForward(FAILURE);
        }
    }
}
