<%-- 
    Document   : insertMission
    Created on : Jul 4, 2018, 3:56:53 PM
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
        <s:form action="addToMission" method="post">
            <s:combobox headerValue="---select---" headerKey="-1" list="%{#session.COMBOBOX}" name="heroesName"/>
            <s:submit value="addToMission"/>
        </s:form>
        <s:form action="insertMission" method="post">
            <s:textfield name="txtMissionID" label="MissionID"/>
            <s:textfield name="txtMissionName" label="MissionName"/>
            <s:textfield name="place" label="place"/>
            <s:hidden name="listUser" value="%{#session.USERNAME}"/>
            <s:submit value="Insert"/>
        </s:form>
        <s:url var="closeLink" value="loadMission"></s:url>
        <s:a href="%{closeLink}">X</s:a>
    </body>
</html>
