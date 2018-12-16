<%-- 
    Document   : index
    Created on : Jul 3, 2018, 4:23:24 PM
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
        <form action="login" method="post">
            <s:textfield name="username" label="User Name"/>
            <s:password name="password" label="Password"/>
            <s:submit value="Login"/>
        </form>
            <font color="red"><s:property value="%{#request.ERROR}"/></font>
    </body>
</html>
