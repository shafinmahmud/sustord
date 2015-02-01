<%-- 
    Document   : GradesByCurriculum
    Created on : Jan 27, 2015, 2:51:58 AM
    Author     : SHAFIN
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="me.shafin.sustord.bean.SyllabusPOJO"%>
<%@page import="java.util.List"%>
<%@page import="me.shafin.sustord.service.FormatService"%>
<%@page import="me.shafin.sustord.entity.StudentInfo"%>
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
                            <%                                StudentService studentService;
                                studentService = (StudentService) session.getAttribute("studentService");
                                StudentInfo studentInfo = studentService.getStudentInfo();
                                String name = studentInfo.getPersonalInfo().getName();
                                String regNo = studentInfo.getRegistrationNo();
                                String sessionName = studentService.getStudentSessiontName();
                                String dept = studentService.getStudentDepartmentName();
                                String program = studentService.getStudentProgramName();
                                String school = studentService.getStudentSchoolName();


                            %>  
                            <legend>Grade Report by Curriculum        
                                <a href="" class="btn btn-danger btn-xs pull-right">Print</a>
                            </legend>

                            <div class="grade-report">
                                <table class="table table-bordered">
                                    <tbody><tr>
                                            <td>Registration No</td>
                                            <td>: </td>
                                            <td><%=regNo%></td>
                                            <td>School</td>
                                            <td>: </td>
                                            <td><%=school%></td>
                                        </tr>
                                        <tr>

                                            <td>Student Name</td>
                                            <td>: </td>
                                            <td><%=name%></td>
                                            <td>Department</td>
                                            <td>: </td>
                                            <td><%=dept%></td>
                                        </tr>
                                        <tr>

                                            <td>Credit(s) Completed</td>
                                            <td>:</td>
                                            <td></td>
                                            <td>Program</td>
                                            <td>:</td>
                                            <td><%=program%></td>
                                        </tr>
                                        <tr>

                                            <td>Course(s) Completed</td>
                                            <td>:</td>
                                            <td></td>
                                            <td>Session</td>
                                            <td>:</td>
                                            <td><%=sessionName%></td>
                                        </tr>
                                        <tr>
                                            <td>Total Credits</td>
                                            <td>:</td>
                                            <td></td>
                                            <td>CGPA</td>
                                            <td>:</td>
                                    <div><td class="badge pull-left"></td></div>

                                    </tr>

                                    </tbody></table>
                                <div class="text-center"><h4><label>Academic Semesters</label></h4> </div>         

                                <div>


                                </div>
                                <div>  
                                    <%
                                        int currentSemester = studentService.getStudentCurrentSemester();
                                        for (int i = 0; i < currentSemester; i++) {
                                            String semesterName = FormatService.formatSemesterName(i + 1);
                                            
                                    %>
                                    <table class="table table-bordered">
                                        <label Style="font-size: 13px"><%=semesterName%></label>
                                        <thead>
                                            <tr style="font-size: 12px">
                                                <th style="width: 15%">Course Code</th>
                                                <th style="width: 40%">Title</th>
                                                <th style="width: 10%" class="text-center">Credit</th>
                                                <th style="width: 13%" class="text-center">Passed on</th>
                                                <th style="width: 10%" class="text-center">Grade</th>
                                                <th style="width: 12%" class="text-center">Grade Point</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                            List<SyllabusPOJO> courses = studentService.getStudentSyllabusAsEntity(i+1);
                                            double totalCredit = 0;
                                            double completedCredit =0;
                                            
                                                for(SyllabusPOJO s: courses) {
                                                    String courseCode = s.getCourseCode();
                                                    String title = s.getTitle();
                                                    double credit = s.getCredit();
                                                    String grade = s.getGrade();
                                                    String point = s.getPoint();
                                                    String passedOn = s.getPassedOn();
                                                    totalCredit += credit;
                                                    if(!point.equals("-") && !point.equals("0.00"))
                                                    completedCredit += credit;
                                            %>
                                            <tr>
                                                <td class="text-center"><%=courseCode%></td>
                                                <td><%=title%></td>
                                                <td  class="text-center"><%=credit%></td>
                                                <td  class="text-center"><%=passedOn%></td>
                                                <td  class="text-center"><%=grade%></td>
                                                <td  class="text-center"><%=point%></td>
                                            </tr>                                      
                                            <%
                                                }
                                            %>
                                            <tr>
                                                <td colspan="2" class="text-center" style="font-size: 12px"><label>Credit(s) Complete </label></td>  
                                                <td colspan="4" class="text-center" style="font-size: 12px"><label class="badge bg-success"><%=completedCredit%> out of <%=totalCredit%></label></td>
                                            </tr>

                                        </tbody></table>

                                    <%
                                        }
                                    %>
                                </div>
                            </div>
                        </fieldset>
                    </div>
                </div>
            </div>
        </div>
        <%@include  file="../WEB-INF/jspf/Footer.jspf"%>
    </body>
</html>
