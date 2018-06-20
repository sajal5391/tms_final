<%@page import="com.lge.tms2.wrapper.EmpInfo"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:html lang="true">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><bean:message key="welcome.title"/></title>
        <html:base/>
    </head>
    <body style="background-color: white">
        
        <logic:notPresent name="org.apache.struts.action.MESSAGE" scope="application">
            <div  style="color: red">
                ERROR:  Application resources not loaded -- check servlet container
                logs for error messages.
            </div>
        </logic:notPresent>
<%  EmpInfo empInfo = (EmpInfo) session.getAttribute("empInfo");            
    if (empInfo == null) { %>
        <jsp:forward page="login.jsp"/>
    <%} else {%>
    <html:link page="/logout.do" ><img src="img/logout.png" name="Image11"  height="30" border="0"></html:link>
        <h3><bean:message key="welcome.heading"/></h3>
        <p><bean:message key="welcome.message"/></p>
        <%= empInfo.getEmp_email()%>
    <%}%>
        
        
    </body>
</html:html>
