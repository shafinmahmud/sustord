<%-- 
    Document   : homepage
    Created on : Oct 29, 2014, 3:55:52 PM
    Author     : SHAFIN
--%>

<%@page import="me.shafin.sustord.bean.SyllabusPOJO"%>
<%@page import="java.util.List"%>
<%@page import="me.shafin.sustord.entity.CourseRegistration"%>
<%@page import="me.shafin.sustord.service.FormatService"%>
<%@page import="me.shafin.sustord.service.CalenderService"%>
<%@page import="me.shafin.sustord.entity.StudentInfo"%>
<%@page import="me.shafin.sustord.service.StudentService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Home</title>
        <style type="text/css">
            [ng\:cloak],[ng-cloak],[data-ng-cloak],[x-ng-cloak],.ng-cloak,.x-ng-cloak,.ng-hide
            {display:none !important;
            }ng\:form{display:block;}.ng-animate-block-transitions{transition:0s all!important;
                                                                   -webkit-transition:0s all!important;}
            </style>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <meta name="viewport" content="width=device-width">
            <link href="../page_files/css/style.css" rel="stylesheet">
            <script src=""></script>
        </head>
        <body>
            <%@include  file="../WEB-INF/jspf/AccessValidation.jspf"%>
            <%@include  file="../WEB-INF/jspf/NavBar.jspf"%>

            <div class="portal-body">
            <div class="row">
                <%@include  file="../WEB-INF/jspf/SideBar.jspf"%>
                <div id="main-content" class="col-md-10 column800">
                    <%                        StudentService studentService;
                        studentService = (StudentService) session.getAttribute("studentService");
                        StudentInfo studentInfo = studentService.getStudentInfo();
                        String name = studentInfo.getPersonalInfo().getName();
                        String regNo = studentInfo.getRegistrationNo();
                        int semx = studentService.getStudentCurrentSemester();
                        String semesterName = FormatService.formatSemesterName(semx);
                    %>
                    <div class="row">
                        <div class="col-sm-12 col-xs-12 col-md-3 column200-quick">
                            <br>
                            <div class="text-center home-profile-title">
                                <a href="../Home/ProfileUser.jsp">
                                    <div style="font-size: 16px"><%=regNo%></div>
                                    <div><%=name%></div>
                                </a>
                            </div>
                            <br>
                            <hr>
                            <br>
                        </div>
                        <div class="col-sm-12 col-xs-12 col-md-9 column600">


                            <fieldset>
                                <legend>Class Schedule</legend>
                                <style type="text/css">    

                                    .scheduleTable {
                                        width: 100%;
                                    }

                                    .scheduleTable td {
                                        padding: 2px;
                                        border-top: 1px solid #f5f1f1;
                                        font-size: 12px;
                                    }

                                    .scheduleTable tr:first-child > td {
                                        border-top: none;
                                    }
                                </style>

                                <table class="scheduleTable">

                                    <tbody>
                                        <%

                                            for (int i = 0; i < 7; i++) {
                                                CalenderService calenderService = new CalenderService();
                                                String date = calenderService.getDateLabel(i);
                                                String day = calenderService.getDayLabel(i);

                                        %>
                                        <tr>
                                            <td>
                                                <p style="font-size: 13px"><b><%=day%></b><br></p>
                                                <p><%=date%></p>            
                                            </td>
                                            <td>
                                                <div>

                                                    <p><i>Time:</i><br><label></label>
                                                        <br></p>

                                                </div>
                                            </td>
                                        </tr>
                                        <%           }
                                        %>
                                    </tbody></table>

                            </fieldset>
                            <br>
                            <br>
                            <fieldset>
                                <legend><%=semesterName%></legend>
                                <div id="divCourseList"><table class="table">
                                        <thead><tr>
                                                <th>Course Code</th>
                                                <th>Title</th>
                                                <th>Credit</th>
                                                <th>Remarks</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                            List<SyllabusPOJO> courseList;
                                            courseList = studentService.getStudentRegisteredCoursesAsEntity(semx);
                                                for (int j = 0; j < courseList.size(); j++) {
                                                    String courseCode = courseList.get(j).getCourseCode();
                                                    String title = courseList.get(j).getTitle();
                                                    double credit = courseList.get(j).getCredit();
                                            %>
                                            <tr>
                                                <td><%=courseCode%></td> 
                                                <td><%=title%></td> 
                                                <td><%=credit%></td> 
                                                <td> <div> 
                                                        <label class="label label-success">Current</label>
                                                    </div>
                                                </td>
                                            </tr>
                                            <%
                                                }
                                            %>

                                        </tbody></table>
                                </div>
                            </fieldset>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include  file="../WEB-INF/jspf/Footer.jspf"%>

    </body></html>
