<%-- 
    Document   : CourseAnalysis
    Created on : Feb 14, 2015, 12:03:34 PM
    Author     : SHAFIN
--%>

<%@page import="me.shafin.sustord.service.FormatService"%>
<%@page import="me.shafin.sustord.service.StudentService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width">
        <title>Course Analysis</title>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
        <script src="http://code.highcharts.com/highcharts.js"></script>
        <%@include  file="../WEB-INF/jspf/BootstrapInclude.jspf"%>
        <link href="../page_files/css/style.css" rel="stylesheet">

        <script src="./course-analysis-script.js"></script>
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

                            <table class="table">
                                <tbody>
                                    <tr style="background-color: #D8E2F3;">
                                        <td>
                                <legend>Semester Grade Analysis</legend>
                                </td>
                                <td>
                                    <select class="form-control pull-right" id="semester-dropdown" name="reg-semester-dropdown" style="width: 250px">
                                        <%                                            
                                            StudentService studentService = (StudentService) session.getAttribute("studentService");
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

                                </td>
                                </tr>
                                </tbody>
                            </table>

                            <br>

                            <div id="bar-chart-div"></div>

                            <br>
                            <br>
                            <p style="font-size: 20px; text-align: center">Course Grade Distribution</p>
                            <table id="pie-chart-table" class="table table-condensed">
                            </table>
                        </fieldset>
                    </div>

                </div>
            </div>
        </div>
        <%@include  file="../WEB-INF/jspf/Footer.jspf"%>
    </body>
</html>
