<%-- 
    Document   : missionDetail
    Created on : Jul 4, 2018, 10:22:28 AM
    Author     : thien
--%>
<%@page import="thienvn.dto.MissionDetailDTO"%>
<%@page import="java.util.Set"%>
<%@taglib  uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <table border="1">
            <thead>
                <tr> 
                    <th>No</th>
                    <th>UserName</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>

            <tbody>
                <s:iterator var="dto" value="%{list}" status="counter">
                    <s:form action="updateMission" method="post" theme="simple">
                        <tr>
                            <s:hidden name="MissionID" value="%{ID}"/>
                            <s:hidden name="userName" value="%{#dto.username}"/>
                            <td><s:property value="%{#counter.count}"/></td>
                            <td> <s:combobox name="txtUserName" list="%{#request.COMBOBOX}" value="%{#dto.username}"/></td>  
                            <td><s:property value="%{#dto.status}"/></td>
                            <td>
                                <s:submit value="Update"/>
                            </td>
                            <td>
                                <s:url  var="deleteLink" value="deleteMissionDetail">
                                    <s:param name="missionID" value="%{ID}"/>    
                                    <s:param name="userName" value="%{#dto.username}"/>    
                                </s:url>
                                <s:a href="%{deleteLink}">Delete</s:a>
                            </td>
                        </tr>
                    </s:form>
                </s:iterator>
                <s:form action="addAUser" method="post" >
                    <s:hidden name="MissionID" value="%{ID}"/>
                    <s:combobox name="username" list="%{#request.COMBOBOX2}" headerKey="-1" headerValue="---select---"/>
                    <s:submit value="AddUser"/>
                </s:form>
            </tbody>
        </table>
        <s:a href="closeMissionDetail">X</s:a>
    </body>
</html>
