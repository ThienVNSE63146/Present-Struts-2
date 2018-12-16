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
                    <s:form action="updateStatus" method="post" theme="simple">
                        <tr>
                            <s:hidden name="MissionID" value="%{ID}"/>
                            <td><s:property value="%{#counter.count}"/></td>
                            <td> <s:property value="%{#dto.armorname}"/></td> 
                            <s:if test="%{#dto.armorname==#session.NAME}">
                                <td><s:combobox name="status" list="%{#request.COMBOBOX2}" value="%{#dto.status}" />
                                    <s:set var="STT" value="%{#dto.status}"/>
                                </td>
                                <td>
                                    <s:submit value="Update"/>
                                </td>
                                <td><s:if test="%{#dto.status=='Done'}">
                                    <s:url var="returnLink" value="returnArmor">
                                        <s:param name="missionID" value="%{ID}"/>
                                    </s:url>
                                    <s:a href="%{returnLink}">ReturnArmor</s:a>
                                    </s:if></td>
                            </s:if>
                            <s:else>
                                <td><s:property value="%{#dto.status}"/></td>
                            </s:else>
                                
                        </tr>
                    </s:form>
                </s:iterator>
            </tbody>
        </table>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>ArmorName</th>
                </tr>
            </thead>
            <tbody>

                <s:if test="%{#session.CLASS=='NORMAL'}">
                    <s:if test="%{!listArmor.isEmpty()}">
                        <s:iterator var="armor" value="%{listArmor}" status="counter">
                            <tr>
                                <td></td>
                                <td><s:property value="%{#armor}"/></td>
                            </tr>
                        </s:iterator>
                    </s:if>
                    <s:elseif test="%{listArmor.isEmpty()}">
                        <s:form action="addArmorToMission"  method="post" theme="simple">
                            <tr>
                                <td></td>
                                <td>
                                    <s:combobox name="armor" list="%{#request.COMBOBOX3}" headerKey="-1" headerValue="---select---"/>  
                                    <s:hidden name="MissionID" value="%{ID}"/>
                                </td>
                            </tr>
                            <s:submit value="addArmor"/>
                        </s:form>
                    </s:elseif>
                </s:if>
                <s:elseif test="%{#session.CLASS=='VIP'}">
                    <s:iterator var="armor" value="%{listArmor}" status="counter">
                        <tr>
                            <td></td>
                            <td><s:property value="%{#armor}"/></td>
                        </tr>
                    </s:iterator>
                    <s:form action="addArmorToMission"  method="post" theme="simple">
                        <tr>
                            <s:combobox name="armor" list="%{#request.COMBOBOX3}"/>  
                            <s:hidden name="MissionID" value="%{ID}"/>
                            <s:hidden name="stt" value="%{#STT}"/>
                            <s:property value="thien"/>
                        </tr>
                        <s:submit value="addArmor"/>
                    </s:form>
                </s:elseif>
            </tbody>
        </table>

        <s:a href="closeMissionDetail">X</s:a>
    </body>
</html>
