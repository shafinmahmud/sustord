<%@page import="java.util.List"%>
<%@page import="sm.sustord.model.Syllabus"%>
<%@page import="sm.sustord.model.StudentInfo"%>
<%@page import="sm.sustord.service.StudentService"%>
<%@page import="sm.sustord.utility.FormatService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css"></style>
        <meta name="viewport" content="width=device-width">
        <title>Course Registration</title>
        
        <%@include  file="../WEB-INF/jspf/BootstrapInclude.jspf"%>
        <link href="../page_files/css/style.css" rel="stylesheet">
        
        <link href='http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,700italic,400,600,700' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="../page_files/css/font-awesome/font-awesome.css">
        <link rel="stylesheet" href="../page_files/css/font-awesome/button-style.css" />

        <link href='http://fonts.googleapis.com/css?family=Bitter:400,700' rel='stylesheet' type='text/css'>
        <script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
        <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>

        <script src="./course-reg-script.js"></script>

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
                             <table class="table">
                                <tbody>
                                    <tr style="background-color: #D8E2F3;">
                                        <td>
                                           <legend>Course Registration</legend>
                                        </td>
                                        <td>
                                            <select class="form-control pull-right" id="reg-semester-dropdown" name="reg-semester-dropdown" style="width: 250px">
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
                                               
                                            </select>

                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <br>
                            <h4 id="sem-title"><br></h4>
                            <br>
                            <div >
                                <div id="div_main_table">
                                    <table id="course-table-main" class="table table-condensed col-lg-10">
                                        <thead >
                                            <tr>
                                                <th style="width: 15%">Course Code</th>
                                                <th style="width: 50%">Title</th>
                                                <th style="width: 10%; text-align: center">Credit</th>
                                                <th style="width: 10%; text-align: center">Status</th>
                                                <th style="width: 15%; text-align: center">Add</th>
                                            </tr>
                                        </thead>
                                        <tbody> 

                                        </tbody>
                                        <tfoot >
                                            <tr class="total-row">
                                                <th colspan="2" style="text-align: center">
                                                    <b>Taken credit</b>
                                                </th>
                                                <th style="text-align: center; font-size: 16px" id="total_credit" colspan="1">
                                                    <b>0</b>
                                                </th>
                                            </tr>
                                        </tfoot>
                                    </table>
                                </div>

                            </div>

                        </fieldset>

                        <fieldset>

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
                                <br>
                            </div>
                        </fieldset>

                    </div>
                </div>
            </div>

        </div>

        <%@include  file="../WEB-INF/jspf/Footer.jspf"%>
    </body>


</html>


