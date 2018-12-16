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
                    <th>N0</th>
                    <th>ArmorName</th>
                    <th>Action</th>
                </tr>
            </thead>

            <tbody>
                <s:iterator var="dto" value="%{list}" status="counter">
                    <s:form theme="simple">
                        <tr>
                           
                            <td><s:property value="%{#counter.count}"/></td> 
                            <td><s:property value="%{#dto.armorname}"/></td>
                            <td>
                                <s:url  var="deleteLink" value="deleteWeaponseDetail">
                                    <s:param name="weaponseID" value="%{weaponseID}"/>    
                                    <s:param name="armorname" value="%{#dto.armorname}"/>    
                                </s:url>
                                <s:a href="%{deleteLink}">Delete</s:a>
                            </td>
                        </tr>
                        </s:form>
                </s:iterator>
                <s:form action="addAArmor" method="post" >
                    <s:hidden name="weaponseID" value="%{weaponseID}"/>
                    <s:combobox name="username" list="%{#request.COMBOBOX2}" headerKey="-1" headerValue="---select---"/>
                    <s:submit value="AddArmor"/>
                </s:form>
            </tbody>
        </table>
        <s:a href="closeWeaponseDetail">X</s:a>
    </body>
</html>
