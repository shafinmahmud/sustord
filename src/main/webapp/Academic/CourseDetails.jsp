<%-- 
    Document   : CourseDetails
    Created on : Jan 27, 2015, 6:44:12 PM
    Author     : SHAFIN
--%>

<%@page import="me.shafin.sustord.service.FormatService"%>
<%@page import="me.shafin.sustord.service.StudentService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css">
            [ng\:cloak],[ng-cloak],[data-ng-cloak],[x-ng-cloak],.ng-cloak,.x-ng-cloak,.ng-hide
            {display:none !important;
            }ng\:form{display:block;}.ng-animate-block-transitions{transition:0s all!important;
                                                                   -webkit-transition:0s all!important;}
            </style>
            <meta name="viewport" content="width=device-width">
            <title>Curriculum</title>
            <link href="../page_files/css/style.css" rel="stylesheet">
        </head>
        <body>
            <%@include  file="../WEB-INF/jspf/AccessValidation.jspf"%>
            <%@include  file="../WEB-INF/jspf/NavBar.jspf"%>

            <div class="portal-body">
            <div class="row">
                <%@include  file="../WEB-INF/jspf/SideBar.jspf"%>
                <div id="main-content" class="col-md-10 column800">

                    <div class="margin5">
                        <fieldset>
                            <legend>Academic Course details
                            </legend>
                            <div class="row">
                                <div class="col-md-3 col-lg-3">
                                    <p class="label-height-30" style="text-align: right; font-size: 17px" >Choose Semester</p>
                                </div>
                                <div class="col-md-5 col-lg-5">
                                    <select class="form-control pull-left" id="reg-semester-dropdown" name="reg-semester-dropdown" style="width: 250px">

                                        <%                                        StudentService service = (StudentService) session.getAttribute("studentService");

                                            int numofsem = service.getStudentTotalSemester();
                                            int currentSemester = 2;

                                            for (int i = 1; i <= numofsem; i++) {
                                                if (i == currentSemester) {
                                        %>
                                        <option value="<%=i%>"><%=FormatService.formatSemesterName(i)%></option>
                                        <%
                                        } else {
                                        %>
                                        <option value="<%=i%>"><%=FormatService.formatSemesterName(i)%></option>
                                        <%
                                                }
                                            }
                                        %>

                                    </select>
                                </div>

                            </div>
                            <br>
                            <table class="table" style="background-color: #D8E2F3">
                                <tbody><tr>
                                        <td><label >CSE 100 :  PROJECT WORK</label></td>
                                        <td><p class="pull-right" ><i>2 Hours/Week, 1.0 Credit</i></p></td>
                                    </tr>
                                </tbody>
                            </table>
                            <div class="margin-l5 ">
                                <p style="font-size: 14px">Any project based on C language including implementation of Data Structure in acceptable.
                                Gaming project using graphics.h library in C is preferable. Teachers must have to ensure every project
                                is unique. Innovative project idea should get extra weight to prevent imitating old projects.</p>

                            </div>
                            <br>
                            <div class="margin-l5 ">


                            </div>
                            <br>
                        </fieldset>
                    </div>


                </div>
            </div>
        </div>
        <%@include  file="../WEB-INF/jspf/Footer.jspf"%>
    </body>
</html>
