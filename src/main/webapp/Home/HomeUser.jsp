<%-- 
    Document   : homepage
    Created on : Oct 29, 2014, 3:55:52 PM
    Author     : SHAFIN
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="me.shafin.sustord.bean.ClassRoutinePOJO"%>
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
                        String regNo = (String) request.getSession().getAttribute("regNo");
                        
                        StudentInfo studentInfo = studentService.getStudentInfoObjectFromRegNo(regNo);
                        String name = studentInfo.getPersonalInfo().getName();
                        
                        int semx = studentService.getStudentCurrentSemester(regNo);
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

                                <table class="table table-striped">

                                    <tbody>
                                        <%

                                            for (int i = 0; i < 7; i++) {
                                                CalenderService calenderService = new CalenderService();
                                                String date = calenderService.getDateLabel(i);
                                                String day = calenderService.getDayLabel(i);
                                                //String routines = studentService.getStudentRoutine(day);
                                                List<ClassRoutinePOJO> routines = studentService.getStudentRoutine(regNo,day);
                                                
                                                if(i==0)
                                                    day = "TODAY";
                                                else if(i==1)
                                                    day = "TOMORROW";


                                        %>
                                        <tr>
                                            <td>
                                                <p style="font-size: 12px"><b><%=day%></b><br></p>
                                                <p><%=date%></p>            
                                            </td>
                                            <td>
                                                <%
                                                    if (!routines.isEmpty()) {
                                                        for (ClassRoutinePOJO r : routines) {
                                                %>
                                                <div>
                                                    <small>Time: <label><%=r.getStart()%> - <%=r.getEnd()%></label></small>
                                                </div>
                                                    <div><p style="font-size: 13px"><%=r.getCourseCode() + "  " + r.getTitle()%></p>       
                                                </div>
                                                <%}
                                                    }else{
                                                        %>
                                                        <div><p style="font-size: 13px"><i>No Classes on this day</i></p>       
                                                </div>
                                                        <%
                                                    }
                                                %>

                                            </td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                    </tbody></table>

                            </fieldset>
                            <br>
                            <br>
                            <fieldset>
                                <legend><%=semesterName%></legend>
                                <div id="divCourseList"><table class="table table-condensed">
                                        <thead><tr>
                                                <th>Course Code</th>
                                                <th>Title</th>
                                                <th>Credit</th>
                                                <th>Hours/Week</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                List<SyllabusPOJO> courseList;
                                                courseList = studentService.getStudentRegisteredCoursesAsEntity(regNo,semx);
                                                for (SyllabusPOJO s: courseList) {
                                                    String courseCode = s.getCourseCode();
                                                    String title = s.getTitle();
                                                    double credit = s.getCredit();
                                                    double hour = s.getHrsWeek();
                                                    
                                                    
                                                    String hrsWeekString = "";
                                                    if (s.isTheoryOrLab()) {
                                                        hrsWeekString = String.valueOf(hour) + " + 0";
                                                    } else { 
                                                        hrsWeekString = "0 + " + String.valueOf(hour);
                                                    }
                                            %>
                                            <tr>
                                                <td><%=courseCode%></td> 
                                                <td><%=title%></td> 
                                                <td><%=credit%></td> 
                                                <td> <div> 
                                                        <p><%=hrsWeekString%></p>
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
