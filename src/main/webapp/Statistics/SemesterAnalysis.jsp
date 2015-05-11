<%-- 
    Document   : CourseAnalysis
    Created on : Feb 14, 2015, 12:03:34 PM
    Author     : SHAFIN
--%>

<%@page import="java.util.List"%>
<%@page import="me.shafin.sustord.pojo.StudentGradeRankedPojo"%>
<%@page import="me.shafin.sustord.controller.SemesterAnalysisController"%>
<%@page import="me.shafin.sustord.utility.FormatService"%>
<%@page import="me.shafin.sustord.service.StudentService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width">
        <title>Course Analysis</title>
        <%@include  file="../WEB-INF/jspf/BootstrapInclude.jspf"%>

        <script src="http://code.highcharts.com/highcharts.js"></script>

        <link href="../page_files/css/style.css" rel="stylesheet">

        <script src="./semester-analysis-script.js"></script>
    </head>
    <body>
        <%@include  file="../WEB-INF/jspf/AccessValidation.jspf"%>
        <%@include  file="../WEB-INF/jspf/NavBar.jspf"%>
        <div class="portal-body">
            <div class="row">
                <%@include  file="../WEB-INF/jspf/SideBar.jspf"%>
                <div id="main-content" class="col-md-10 column800">

                    <div class="margin5">
                        <%                            
                            SemesterAnalysisController controller = new SemesterAnalysisController();
                        %>
                        <fieldset>

                            <table class="table">
                                <tbody>
                                    <tr style="background-color: #D8E2F3;">
                                        <td>
                                <legend>Semester Analysis</legend>
                                </td>
                                <td>
                                    <select class="form-control pull-right" id="semester-dropdown" name="reg-semester-dropdown" style="width: 250px">

                                        <%
                                            int numofsem = controller.getTotalAcademicSemester();
                                            for (int i = 1; i <= numofsem; i++) {
                                        %>                                       
                                        <option value="<%=i%>"><%=FormatService.formatSemesterName(i)%></option>
                                        <%
                                            }
                                        %>
                                    </select>

                                </td>
                                </tr>
                                </tbody>
                            </table>

                            <br>
                            <div id="bar-chart-div">

                            </div>
                            <br>
                            <br>
                            <div>
                                <ul class="nav nav-tabs">
                                    <li class="active"><a data-toggle="tab" href="#sectionA">Course Statistics</a></li>
                                    <li><a data-toggle="tab" href="#sectionB">Semester GPA Ranking</a></li>

                                </ul>
                                <div class="tab-content">
                                    <div id="sectionA" class="tab-pane fade in active">
                                        <table id="pie-chart-table" class="table table-condensed">
                                        </table>
                                    </div>
                                    <div id="sectionB" class="tab-pane fade">
                                        <table id="semester-rank-table" class="table table-condensed">
                                            <thead>
                                                <tr>
                                                    <th style="width: 5%">#Rank</th>
                                                    <th style="width: 40%">Name</th>
                                                    <th style="width: 15%">Registration No</th>
                                                    <th style="width: 20%; text-align: center">Completed Credits</th>
                                                    <th style="width: 20%; text-align: center">CGPA</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                               
                                                
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>   

                            <br>
                            <br>
                        </fieldset>
                    </div>

                </div>
            </div>
        </div>
        <%@include  file="../WEB-INF/jspf/Footer.jspf"%>
    </body>
</html>
