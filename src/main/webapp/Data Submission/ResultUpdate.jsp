
<%@page import="shafin.sustord.util.FormatService"%>
<%@page import="shafin.sustord.service.StudentService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css"></style>
        <meta name="viewport" content="width=device-width">
        <title>Result Edit</title>
        <%@include  file="../WEB-INF/jspf/BootstrapInclude.jspf"%>
        <link href="../page_files/css/style.css" rel="stylesheet">

        <script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
        <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>

        <script src="./result-update-script.js"></script>




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
                                <legend>Result update</legend>
                                </td>
                                <td>
                                    <select class="form-control pull-right" id="result-semester-dropdown" name="reg-semester-dropdown" style="width: 250px">
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
                            <div>
                                <table id="table-registered-courses" class="table table-condensed col-lg-10">

                                    <thead>
                                        <tr>
                                            <th style="width: 15%">Course Code</th>
                                            <th style="width: 60%">Title</th>
                                            <th style="width: 10%; text-align: center">Credit</th>
                                            <th style="width: 15%; text-align: center">Grade</th>
                                            <th style="width: 25%">Change</th>
                                        </tr>
                                    </thead>
                                    <tbody>


                                        <tr class="total-row">
                                            <th colspan="2" style="text-align: center">
                                                <b>Total credit(s) taken</b>
                                            </th>
                                            <th style="text-align: center; font-size: 16px" id="taken_credit" colspan="1">
                                                <b>0</b>
                                            </th>
                                        </tr>


                                    </tbody></table>
                            </div>
                        </fieldset>

                        <fieldset>
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



