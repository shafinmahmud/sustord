<%-- 
    Document   : CourseDetails
    Created on : Jan 27, 2015, 6:44:12 PM
    Author     : SHAFIN
--%>

<%@page import="me.shafin.sustord.controller.CourseDetailsController"%>
<%@page import="me.shafin.sustord.bean.SyllabusPOJO"%>
<%@page import="java.util.List"%>
<%@page import="me.shafin.sustord.model.StudentInfo"%>
<%@page import="me.shafin.sustord.utility.FormatService"%>
<%@page import="me.shafin.sustord.service.StudentService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width">
        <title>Courses in details</title>

        <!-- Including Bootstrap-->
        <%@include  file="../WEB-INF/jspf/BootstrapInclude.jspf"%>
        
        <!-- Including jquery library-->
        <script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
        
        <!-- style sheet -->
        <link href="../page_files/css/style.css" rel="stylesheet">

        <!-- Script for this page-->
        <script src="course-details-script.js"></script>
    </head>
    <body>
        <!-- validating access to this page-->
        <%@include  file="../WEB-INF/jspf/AccessValidation.jspf"%>
        <!-- nav bar portion -->
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
                                        <td><legend>Course Details</legend></td>
                                <td>
                                    <select class="form-control pull-right" id="semester-dropdown" name="reg-semester-dropdown" style="width: 250px">
                                        <%                                            
                                            String regNo = (String) request.getSession().getAttribute("regNo");
                                            
                                            CourseDetailsController detailsController = new CourseDetailsController(regNo);
                                            int numofsem = detailsController.getTotalAcademicSemester();
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
         <!-- Footer section -->
        <%@include  file="../WEB-INF/jspf/Footer.jspf"%>
    </body>
</html>
