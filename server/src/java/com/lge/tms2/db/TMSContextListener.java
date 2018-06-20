/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lge.tms2.db;

import com.lge.tms2.utils.Util;
import java.io.File;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author ramesh.nagarajan
 */
public class TMSContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Context Initialized");
         GetCon.init();
         Util.setServletPath(sce.getServletContext().getRealPath("/") + File.separator + "util");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Context Destroyed");
        GetCon.closeConnection();
        Util.setServletPath(null);
    }
    
}
