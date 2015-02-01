<%-- 
    Document   : CourseDetails
    Created on : Jan 27, 2015, 6:44:12 PM
    Author     : SHAFIN
--%>

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
                                    <select class="form-control pull-left" id="reg-semester-dropdown" name="reg-semester-dropdown" style="width: 250px">
                                    
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
                                </div>
                                
                            </div>
                            <br>
                            <table class="table" style="background-color: #D8E2F3">
                                <tbody><tr>
                                        <td><label >INTRO. TO BEHAVIORAL SCIENCE [J]</label></td>
                                        <td><label class="pull-right" >Total:100; Pass:50; Contributes:100%; Mark:A(90)</label></td>
                                    </tr>
                                </tbody>
                            </table>
                            <div class="margin-l5 ">

                                <div class="row">
                                    <div class="col-md-7">
                                        <label class="label-height-30" >Midterm for 2012-2013, Spring</label>
                                    </div>
                                    <div class="col-md-5">
                                        <label class="pull-right" >Total:100; Pass:50; Contributes:40%; Mark:A+(94)</label>
                                    </div>
                                </div>

                                <div class="margin-l5">
                                    <div class="row">
                                        <div class="col-md-7">
                                            <label class="label-height-30 font-weight-normal" >Assignments</label>

                                        </div>
                                        <div class="col-md-5">
                                            <label class="pull-right font-weight-normal">Total:20; Pass:10; Contributes:20%; Mark:18</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="margin-l5">
                                    <div class="row">
                                        <div class="col-md-7">
                                            <label class="label-height-30 font-weight-normal" >Written Exams</label>

                                        </div>
                                        <div class="col-md-5">
                                            <label class="pull-right font-weight-normal">Total:50; Pass:25; Contributes:50%; Mark:46</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="margin-l5">
                                    <div class="row">
                                        <div class="col-md-7">
                                            <label class="label-height-30 font-weight-normal" >Class Tests</label>

                                        </div>
                                        <div class="col-md-5">
                                            <label class="pull-right font-weight-normal" >Total:20; Pass:10; Contributes:20%; Mark:18</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="margin-l5">
                                    <div class="row">
                                        <div class="col-md-7">
                                            <label class="label-height-30 font-weight-normal" >Attendances&amp;Performance</label>

                                        </div>
                                        <div class="col-md-5">
                                            <label class="pull-right font-weight-normal" >Total:10; Pass:5; Contributes:10%; Mark:10</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <br>
                            <div class="margin-l5 ">

                                <div class="row">
                                    <div class="col-md-7">
                                        <label class="label-height-30" >Finalterm for 2012-2013, Spring</label>
                                    </div>
                                    <div class="col-md-5">
                                        <label class="pull-right">Total:100; Pass:50; Contributes:60%; Mark:A-(87)</label>
                                    </div>
                                </div>

                                <div class="margin-l5">
                                    <div class="row">
                                        <div class="col-md-7">
                                            <label class="label-height-30 font-weight-normal" >Written Exams</label>

                                        </div>
                                        <div class="col-md-5">
                                            <label class="pull-right font-weight-normal" >Total:50; Pass:25; Contributes:50%; Mark:43</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="margin-l5">
                                    <div class="row">
                                        <div class="col-md-7">
                                            <label class="label-height-30 font-weight-normal" >Class Tests</label>

                                        </div>
                                        <div class="col-md-5">
                                            <label class="pull-right font-weight-normal" >Total:20; Pass:10; Contributes:20%; Mark:17</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="margin-l5">
                                    <div class="row">
                                        <div class="col-md-7">
                                            <label class="label-height-30 font-weight-normal" >Assignments</label>

                                        </div>
                                        <div class="col-md-5">
                                            <label class="pull-right font-weight-normal" >Total:20; Pass:10; Contributes:20%; Mark:17</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="margin-l5">
                                    <div class="row">
                                        <div class="col-md-7">
                                            <label class="label-height-30 font-weight-normal">Attendances&amp;Performance</label>

                                        </div>
                                        <div class="col-md-5">
                                            <label class="pull-right font-weight-normal" >Total:10; Pass:5; Contributes:10%; Mark:10</label>
                                        </div>
                                    </div>
                                </div>
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
