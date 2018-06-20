/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lge.tms2.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author ramesh.nagarajan
 */
public class ExecutePythonFromJava {

    public static boolean isLogin(String username, String password) throws IOException {
        username = (username != null && !username.endsWith("@lge.com")) ? username + "@lge.com" : username;
        Process p = Runtime.getRuntime().exec("python " + Util.getServletPath() + File.separator + "ldap_autho.py " + username + " " + password);
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String content = in.readLine();
        in.close();

        return (!Util.isEmpty(content) && !content.contains("password"));
    }
}
