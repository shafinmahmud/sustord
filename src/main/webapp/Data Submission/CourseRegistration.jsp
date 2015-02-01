<%-- 
    Document   : coursereg
    Created on : Jan 15, 2015, 3:00:46 PM
    Author     : SHAFIN
--%>

<%@page import="java.util.List"%>
<%@page import="me.shafin.sustord.entity.Syllabus"%>
<%@page import="me.shafin.sustord.entity.StudentInfo"%>
<%@page import="me.shafin.sustord.service.StudentService"%>
<%@page import="me.shafin.sustord.service.FormatService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css"></style>
        <meta name="viewport" content="width=device-width">
        <title>Course Registration</title>
        <link href="../page_files/css/style.css" rel="stylesheet">

        <link href='http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,700italic,400,600,700' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="../page_files/css/font-awesome/font-awesome.css">
        <link rel="stylesheet" href="../page_files/css/font-awesome/button-style.css" />

        <link href='http://fonts.googleapis.com/css?family=Bitter:400,700' rel='stylesheet' type='text/css'>
        <script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
        <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>

        <script src="../page_files/scripts/course-reg-script.js"></script>

    </head>
    <body>
        <%@include  file="../WEB-INF/jspf/AccessValidation.jspf"%>
        <%@include  file="../WEB-INF/jspf/NavBar.jspf"%>
        <div class="portal-body">
            <div class="row">
                <%@include  file="../WEB-INF/jspf/SideBar.jspf"%>

                <div id="main-content" class="col-md-12 column800">

                    <div class="margin5">

                        <fieldset>
                            <legend>Registration
                                <select class="form-control pull-right" id="reg-semester-dropdown" name="reg-semester-dropdown" style="width: 250px">
                                    <option value="0" style="display:none">Choose Semester</option>
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
                            </legend>
                            <div >
                                <div id="div_main_table">
                                    <table id="course-table-main" class="table table-condensed col-lg-10">
                                        <thead >
                                            <tr>
                                                <th style="width: 15%">Course Code</th>
                                                <th style="width: 50%">Title</th>
                                                <th style="width: 10%">Credit</th>
                                                <th style="width: 15%">Status</th>
                                                <th style="width: 10%">Add</th>
                                            </tr>
                                        </thead>
                                        <tbody> 
  
                                        </tbody>
                                    </table>
                                </div>
                                
                            </div>

                        </fieldset>

                        <fieldset>

                            <legend id="sem-title">
                                <br>
                                Semester</legend>
                            <table id="course-table-choosed" class="table table-condensed">
                                <thead>
                                    <tr>
                                        <th style="width: 15%">Course Code</th>
                                        <th style="width: 50%">Title</th>
                                        <th style="width: 10%">Credit</th>
                                        <th style="width: 15%">Status</th>
                                        <th style="width: 10%">Action</th>
                                    </tr>
                                </thead>
                                <tbody>


                                </tbody>
                                <tfoot >
                                    <tr class="total-row">
                                        <th colspan="2">
                                            <b>Total credit</b>
                                        </th>
                                        <th id="total_credit" colspan="2">
                                            <b>0</b>
                                        </th>
                                    </tr>
                                </tfoot>


                            </table>

                            <div>
                                <table class="table-condensed">
                                    <tr>
                                        <td style="padding-left: 120px;">

                                        </td>
                                        <td >
                                            <button  type="submit" class="btn btn-danger" id="confirm-course-registration">Save</button>
                                        </td>
                                        <td>
                                            <img  id="saving-anim" src="" height="25" alt="">
                                        </td>
                                        <td id="status-message">

                                        </td>
                                    </tr>

                                </table>
                            </div>
                        </fieldset>

                    </div>
                </div>
            </div>

        </div>

        <%@include  file="../WEB-INF/jspf/Footer.jspf"%>
    </body>


</html>

