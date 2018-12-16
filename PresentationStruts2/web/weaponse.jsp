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
        <link rel="stylesheet" type="text/css" href="WEB-Content/weaponse.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <s:head/>
        <title>JSP Page</title>
    </head>
    <body>
        <style>
           
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
                document.getElementById("attackInfo").setAttribute("value", mission);
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
                        <td class="material-icons" style="color:slategray">
                            account_circle
                        </td>
                        <td class="menu">
                            <a href="load">User</a>
                        </td>
                    </tr>
                    <tr>
                        <td class="material-icons"style="color:orange" >
                            assignment
                        </td>
                        <td class="menu">
                            <a href="loadMission">Mission</a>
                        </td>
                    </tr>
                    <tr>
                        <td class="material-icons" style="color: blue" >
                            security
                        </td>
                        <td class="menu">
                            <a href="loadArmor">Armor</a>
                        </td>
                    </tr>
                    <tr>
                        <td class="material-icons" style="color:red">
                            whatshot
                        </td>
                        <td class="menu">
                            <a href="loadWeaponse"> Weaponse</a>
                        </td>
                    </tr>
                    <tr>
                        <td class="material-icons" style="color:brown">
                            person_outline
                        </td>
                        <td class="menu">
                            Profile
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div id="header">
            <form action="searchWeaponse" method="post">
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
                    <s:if test="%{!list.isEmpty}">   
                        <table border="1" id="usertable" >
                            <thead>

                                <tr>
                                    <th>No</th>
                                    <th>WeaponseID</th>
                                    <th>weaponseName</th>
                                    <th>dame</th>
                                    <th>Action</th>
                                </tr>
                            </thead> 
                            <tbody>

                                <s:iterator var="dto" value="%{list}" status="counter">
                                <td onclick="showDetail('<s:property value="%{#dto.image}"/>', '<s:property value="%{#dto.weaponseID}"/>', '<s:property value="%{#dto.weaponseName}"/>', '<s:property value="%{#dto.dame}"/>')" class="img"><s:property value="%{#counter.count}"/></td>
                                 <s:form action="showWeaponseDetail" method="get" theme="simple">
                                        <s:hidden name="weaponseID" value="%{#dto.WeaponseID}"/>
                                        <td><s:submit value="%{#dto.WeaponseID}"/></td>
                                    </s:form>
                                <td><s:property value="%{#dto.weaponseName}"/></td>
                                <td><s:property value="%{#dto.dame}"/></td>
                                <td>
                                    <s:url var="deletelink" value="deleteWeaponse">

                                        <s:param name="weaponseID" value="%{#dto.weaponseID}"/>
                                    </s:url>
                                    <s:a href="%{deletelink}">delete</s:a>
                                    </td>
                                    </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                    </s:if>
                    <s:else>
                        <font color="red">No Record Found</font>
                    </s:else>
                    <p style="position: relative; top: 300px;left: 400px"> <s:a href="loadComboboxArmor" value="Insert"  class="button">Insert</s:a></p>
                        </form>
                        <div id="demo"></div>
                    </div>
                    <div id="detail2">

                        <div id="detailImage">
                            <img src="" id="user-img"/>
                        </div>
                        <form action="updateWeaponse" enctype="multipart/form-data" method="post">
                            <div id="loadImage">
                            <s:file name="fileUp" id="file" value="" onclick="showImage()"/> 
                            <script>
                                function readURL(input) {
                                    if (input.files && input.files[0]) {
                                        var reader = new FileReader();

                                        reader.onload = function (e) {
                                            $("#user-img").attr("src", e.target.result);
                                        }

                                        reader.readAsDataURL(input.files[0]);
                                    }
                                }

                                $("#file").change(function () {
                                    readURL(this);
                                });
                            </script>
                        </div>
                        <div id="detailInfo">
                            <table>
                                <tr  class="detailInfo">
                                    <td>WeaponseID</td>
                                    <td ><s:textfield name="txtWeaponseID" id="nameInfo" value="" readonly="true" /></td>
                                </tr>
                                <tr  class="detailInfo">
                                    <td>WeaponseName</td>
                                    <td  ><s:textfield name="txtWeaponseName" id="roleInfo" value="" /></td>
                                </tr>
                                <tr  class="detailInfo" >
                                    <td >AttackDame</td>
                                    <td><s:textfield name="txtAttackDame" id="attackInfo" value="" /> </td>
                                </tr>

                            </table>
                        </div>

                        <s:submit value="Update" class="button" style="position: relative;top:50px;left: 130px;margin: 10px;float: left"/>

                </div>
            </div>


        </div>

    </div>
</div>
</body>
</html>
