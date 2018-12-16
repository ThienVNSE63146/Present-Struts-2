<%-- 
    Document   : error
    Created on : Jul 3, 2018, 7:27:42 PM
    Author     : thien
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <font color="red"><s:property value="%{#request.ERROR}"/></font>
     
    </body>
</html>
