<%-- 
    Document   : insertWeaponse
    Created on : Jul 5, 2018, 12:00:10 PM
    Author     : thien
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <s:form action="addToWeaponse" method="post">
            <s:combobox name="armor" list="%{#session.COMBOBOX}" headerKey="-1" headerValue="---select---" />
            <s:submit value="AddArmor"/>
        </s:form>
        <form action="insertWeaponseDetail" enctype="multipart/form-data"  method="post">
                        <div id="insertFileImage" >
                            <img src="" id="user-img2" style="width:300px;border: 1px solid black"/>
                        </div>
                        <s:file name="fileUp" id="file2" value=""  />
                    </div>
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
                            <td class="name">WeaponseID</td>
                            <td> <s:textfield name="weaponseID"/></td>
                        </tr>
                        <tr>
                            <td class="name">WeaponseName</td>
                            <td> <s:textfield name="weaponseName"/></td>
                        </tr>
                        <tr>
                            <td class="name">AttackDame</td>
                            <td>  <s:password name="attackDame"/></td>
                        </tr>
                       
                    </table> 
                    <s:submit value="Insert"/>
                    <s:url var="closeLink" value="loadWeaponse">
                        
                    </s:url>
                    <s:a href="%{closeLink}">X</s:a>
                    </body>
                    </html>
