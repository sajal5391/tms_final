/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lge.tms2.action;

import com.lge.tms2.action.beans.LoginForm;
import com.lge.tms2.db.DBIntializer;
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

         ActionForward frw = null;
        try {
            String username = loginForm.getUsername();
        String password = loginForm.getPassword();

        System.out.println("Executed");
       
        System.out.println("loginForm" + loginForm);
        if (!Util.isEmpty(password) && !Util.isEmpty(username) ) {
            if("admin".equals(password) && "admin".equals(username)) {
                String msg = request.getParameter("toggle");
                System.out.println("debug: " + msg);
                if(!Util.isEmpty(msg)) {
                    DBIntializer.toggle();
                    frw = mapping.findForward(SUCCESS);
                } else {
                HttpSession ses = request.getSession(true);
                EmpInfo empInfo = new EmpInfo();
                empInfo.setEmp_name(username);
                ses.setAttribute("admin", empInfo);
                frw = mapping.findForward(SUCCESS);
                }
            } else {              
                     frw = mapping.findForward(FAILURE);
                         
            }
        } else {
            String msg = request.getParameter("toggle");
                System.out.println("debug: " + msg);
                if(!Util.isEmpty(msg)) {
                    DBIntializer.toggle();
                    frw = mapping.findForward(SUCCESS);
                } else {
                    System.out.println("FAILURE");
                     frw = mapping.findForward(FAILURE);
                }     
            
            
        }
        
        }catch(Exception e) {
            e.printStackTrace();
            
        }
    return frw;
    }
}
