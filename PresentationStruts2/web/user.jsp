<%-- 
    Document   : admin
    Created on : Jul 3, 2018, 4:47:01 PM
    Author     : thien
--%>
<%@page import="thienvn.dto.MissionDTO"%>
<%@page import="thienvn.dto.AvengerDTO"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <s:head/>
        <title>JSP Page</title>
    </head>
    <body>
        <style>
            div{float: left;font-family: arial;}
            a{text-decoration: none}
            #menu{width: 5%;height: 660px;border: 1px solid lightGray;overflow: hidden;background-image: url(WEB-Content/Images/white.png);position: fixed;z-index: 1;left: 0px;top: 0px;color: rgba(0,0,0,0.7)}
            #javis{width: 100%;height: 50px;border: 1px solid lightGray;padding-bottom: 10px}

            #javis {font-size: 20px;font-weight:bold;}
            #choose{width: 100%}
            #table{width: 100%;margin-top: 100px}
            .material-icons{padding-left: 10px;margin: 18px}
            .menu{width: 70%;margin-bottom: 20px}
            #menu:hover{
                width: 20%;z-index: 2;transition: 0.5s
            }
            #header{width: 98%;height: 40px;border: 1px solid lightGray;position: fixed;z-index: 0;text-align: right;padding-right: 10px;font-size: 20px;background-image: url(WEB-Content/Images/white.png);padding-top: 20px;top: 0px}
            .logOut{position: relative;bottom: 20px;width: 90%;}
            #main{width: 94.5%;position: absolute;z-index: -1;left: 70px;top: 60px;height: 590px;background-color: white}
            #detail{width: 90%;height: 500px;border: 1px solid lightgray;position: relative;left: 70px;top: 50px;background-image: url(WEB-Content/Images/white.png)}
            #detail1{width: 100%;height:500px;}

            #detail1 table {border: 1px solid black;color: black;position: relative;top:100px;left: 50px;width: 80%;text-align: center}
            .img{width: 50px;position: relative;}
            #usertable tr td {padding-left: 10px}
            #action{
                width: 100%;float: left;height: 300px
            }

            .img:hover {transform: scale(1.05);transition: 0.5s}
            #detailImage{width: 100px;height: 100px;background-size: cover;border: 1px solid black;position: relative;
                         left: 120px;top: 50px}
            #user-img{width: 100px;height: 100px}
            #insert{width: 100%;background-color: rgba(0,0,0,0.7);height:600px;position: relative;
                    z-index: 5;bottom: 500px;visibility: hidden }
            #detailInfo{width: 100%;float: left}
            .detailInfo{margin: 50px}
            #missionInfo{overflow: scroll;height: 50px}
            #detailInfo table{border-spacing: 20px;position: relative;top: 70px}
            #loadImage{position: relative;top: 60px;left: 70px}
            #insertDetail{position: relative;left:100px;top: 70px;color: whitesmoke;border: 1px solid lightslategray;border-radius: 3%;font-size: 25px;padding: 20px;width: 70%}
            #insertDetail table{border-spacing: 20px;position: relative;left: 100px;}
            #search{width: 70%;height: 20px}
            #close:hover{transform: scale(1.5)}
            #insertFile{position: relative;top:0px;left: 20px;height: 380px;top: 50px;width: 300px;}
            .name{position: relative;top:50px}
            #insertFileImage{width: 100%;height: 350px;border: 1px solid wheat;border-radius: 3%;overflow: hidden;}
        </style>
        <script>
            function setVisible() {
                var x = document.getElementById('insert');
                if (x.style.visibility === 'hidden') {
                    x.style.visibility = 'visible';
                } else {
                    x.style.visibility = 'hidden';
                }
            }
            function showDetail(image, name, role, mission) {
                document.getElementById("nameInfo").setAttribute("value", name);
                document.getElementById("roleInfo").setAttribute("value", role);
                document.getElementById("detailImage").style.backgroundImage = "url(" + image + ")";
                document.getElementById("missionInfo").innerHTML = mission;
                document.getElementById("user-img").style.visibility = 'hidden';
            }
            function showImage() {
                document.getElementById("user-img").style.visibility = 'visible';
            }
        </script>

    <body>

        <div id="menu">
            <div id="javis">
                <table>
                    <tr>
                        <td class="material-icons">donut_large</td>
                        <td><s:property value="%{#session.NAME}"/></td>
                    </tr>
                </table>
            </div>
            <div id="choose">
                <table  id="table">
                    <tr id="topI">

                    <tr>
                        <td class="material-icons"style="color:orange" >
                            assignment
                        </td>
                        <td class="menu">
                            <a href="loadUserMission">Mission</a>
                        </td>
                    </tr>
                    <tr>
                        <td class="material-icons" style="color:brown">
                            person_outline
                        </td>
                        <td class="menu">
                            <a href="loadProfile">Profile</a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div id="header">
            <form action="searchUser" method="post">
                <s:iterator var="row" value="%{listMission}" status="status">
                    <s:hidden name="list[%{#status.index}]" value="%{#row.missionName}"/>
                </s:iterator>
                <table class="logOut">
                    <tr >
                        <td class="material-icons" style="width: 5%;position: relative;right: 150px">search</td>
                        <td id="search" style="position: relative;bottom: 100px;right: 200px;" ><s:textfield name="searchValue" style="width: 80%;border: 1px solid graytext;border-radius: 2%;position: relative;bottom: 50px;right:300px" /></td>
                        <td  style="position: relative;bottom: 100px"><s:submit value="Search" style="position: relative;bottom: 80px;left:900px"/></td>
                        <td class="material-icons" style="position: relative;left: 860px;bottom: 130px">power_settings_new</td>
                        <td  style="position: relative;bottom: 100px"><a href="index.jsp"style="color: black;position: relative;left:80px;bottom: 30px">Logout</a></td>
                    </tr>
                </table>
            </form>
        </div>
        <div id="main" >

            <div id="detail">
                <div id="detail1">
                    <s:if test="%{!listMission.isEmpty}">   
                        <table border="1" id="usertable">
                            <thead>

                                <tr>
                                    <th>No</th>
                                    <th>Mission ID</th>
                                    <th>Mission Name</th>
                                    <th>Place</th>
                                    <th>Action</th>
                                </tr>
                            </thead> 
                            <tbody>
                                <tr>
                                    <s:iterator var="dto" value="%{listMission}" status="counter">
                                        <s:form action="updateUserMission" method="post" theme="simple">
                                            <s:hidden name="ID" value="%{#dto.missionID}"/>
                                            <td class="img" ><s:property value="%{#counter.count}"/></td>
                                            <td><s:property value="%{#dto.missionID}"/></td>
                                            <td><s:property value="%{#dto.missionName}"/></td>
                                            <td><s:property value="%{#dto.place}"/></td>
                                            <td><s:submit value="Update"/></td>
                                        </tr>
                                    </s:form>
                                </s:iterator>
                            </tbody>
                        </table>
                    </s:if>
                    <s:else>
                        <font color="red">No Record Found</font>
                    </s:else>
                    
                </div>
            </div>     
        </div>
    </body>
</html>
