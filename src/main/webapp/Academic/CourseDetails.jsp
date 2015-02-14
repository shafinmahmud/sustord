<%-- 
    Document   : CourseDetails
    Created on : Jan 27, 2015, 6:44:12 PM
    Author     : SHAFIN
--%>

<%@page import="me.shafin.sustord.bean.SyllabusPOJO"%>
<%@page import="java.util.List"%>
<%@page import="me.shafin.sustord.entity.StudentInfo"%>
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

            <link href='http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,700italic,400,600,700' rel='stylesheet' type='text/css'>
            <link rel="stylesheet" href="../page_files/css/font-awesome/font-awesome.css">
            <link rel="stylesheet" href="../page_files/css/font-awesome/button-style.css" />

            <link href='http://fonts.googleapis.com/css?family=Bitter:400,700' rel='stylesheet' type='text/css'>
            <script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
            <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css" rel="stylesheet" type="text/css"/>
            <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>

            <script src="../page_files/scripts/course-details-script.js"></script>
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
                                    <select class="form-control pull-left" id="semester-dropdown" name="reg-semester-dropdown" style="width: 250px">

                                        <%                                            StudentService studentService = (StudentService) session.getAttribute("studentService");
                                            String regNo = (String) request.getSession().getAttribute("regNo");
                                            int numofsem = studentService.getStudentTotalSemester(regNo);
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
                                        <option value="0">Optional Courses</option>
                                    </select>
                                </div>

                            </div>
                            <br>

                            <div id="details-div">

                            </div>
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
