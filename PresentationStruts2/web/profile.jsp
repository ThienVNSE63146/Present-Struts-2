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
        <link rel="stylesheet" type="text/css" href="WEB-Content/css.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <s:head/>
        <title>JSP Page</title>
    </head>
    <body>

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

            <table class="logOut">
                <tr >
                    <td class="material-icons" style="width: 5%;position: relative;">search</td>
                    <td id="search" style="" ><input type="text" name="searchValue" style="width: 80%;border: 1px solid graytext;border-radius: 2%;position: relative"/></td>
                    <td  style=""><input type="button" value="Search" /></td>
                    <td class="material-icons" style="position: relative;">power_settings_new</td>
                    <td  style=""><a href="index.jsp"style="color: black;position: relative;">Logout</a></td>
                </tr>
            </table>

        </div>
        <div id="main" >

            <div id="detail">
                <s:form action="updateUser" method="post" theme="simple" enctype="multipart/form-data">
                    <div id="insertFileImage" >
                        <img src="<s:property value="%{user.image}"/>" id="user-img2" style="width:300px;"/>
                    </div>
                    <s:file name="fileUp" id="file2" value=""  />

                    <script>
                        function readURL(input) {
                            if (input.files && input.files[0]) {
                                var reader = new FileReader();

                                reader.onload = function (e) {
                                    $("#user-img2").attr("src", e.target.result);
                                }

                                reader.readAsDataURL(input.files[0]);
                            }
                        }

                        $("#file2").change(function () {
                            readURL(this);
                        });
                    </script>
                    <table>
                        <tr>
                            <td class="name">Name</td>
                            <td> <s:textfield name="txtName" value="%{user.name}"/></td>
                        </tr>
                        <tr>
                            <td class="name">Password</td>
                            <td>  <s:textfield name="txtPassword" value="%{user.password}"/></td>
                        </tr>
                        <tr>
                            <td class="name">Retype Password</td>
                            <td>  <s:password name="txtRetype"/></td>
                        </tr>
                    </table>    

                    <s:submit value="Update" style="position: relative;left: 400px;height: 50px;width: 100px;background-color: rgba(0,0,0,0.8);color: white;border: 1px solid white"/>
                </s:form>
            </div>
        </div>     
    </div>
</body>
</html>
