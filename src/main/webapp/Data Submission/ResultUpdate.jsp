<%-- 
    Document   : resultedit
    Created on : Jan 15, 2015, 3:11:10 PM
    Author     : SHAFIN
--%>

<%@page import="me.shafin.sustord.service.FormatService"%>
<%@page import="me.shafin.sustord.service.StudentService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css"></style>
        <meta name="viewport" content="width=device-width">
        <title>Result Edit</title>
        <link href="../page_files/css/style.css" rel="stylesheet">

        <script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
        <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>

        <script src="../page_files/scripts/result-update-script.js"></script>




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
                            <legend>Result Update</legend>
                            <div>
                                <h2 style="font-size: 18px">Courses as Registration
                                    <select class="form-control pull-right" id="result-semester-dropdown" name="reg-semester-dropdown" style="width: 250px">
                                        <option value="0" style="display:none">Choose Semester</option>
                                        <%
                                            StudentService service = (StudentService) session.getAttribute("studentService");

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
                                </h2>
                                <div >
                                    <table id="table-registered-courses" class="table table-condensed col-lg-10">

                                        <thead>
                                            <tr>
                                                <th style="width: 15%">Course Code</th>
                                                <th style="width: 60%">Title</th>
                                                <th style="width: 10%">Credit</th>
                                                <th style="width: 15%">Grade</th>
                                                <th style="width: 25%">Change</th>
                                            </tr>
                                        </thead>
                                        <tbody>


                                            <tr class="total-row">
                                                <th colspan="2" style="text-align: center">
                                                    <b>Total credit taken</b>
                                                </th>
                                                <th id="taken_credit" colspan="3">
                                                    <b>0</b>
                                                </th>
                                            </tr>


                                        </tbody></table>
                                </div>

                            </div>

                        </fieldset>
                        <div>
                            <table class="table-condensed">
                                <tr>
                                    <td style="padding-left: 120px;">

                                    </td>
                                    <td >
                                        <button  type="submit" class="btn btn-danger" id="confirm-result-update">Save</button>
                                    </td>
                                    <td>
                                        <img  id="saving-anim" src="" height="25" alt="">
                                    </td>
                                    <td id="status-message">

                                    </td>
                                </tr>
                            </table>
                        </div>

                    </div>
                </div>
            </div>

        </div>

        <%@include  file="../WEB-INF/jspf/Footer.jspf"%>


    </body>

</html>



