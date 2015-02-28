<%-- 
    Document   : Curriculum
    Created on : Jan 27, 2015, 6:43:49 PM
    Author     : SHAFIN
--%>

<%@page import="java.util.List"%>
<%@page import="me.shafin.sustord.bean.SyllabusPOJO"%>
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
            <%@include  file="../WEB-INF/jspf/BootstrapInclude.jspf"%>
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
                                String regNo = (String) request.getSession().getAttribute("regNo");
                                StudentInfo studentInfo = studentService.getStudentInfoObjectFromRegNo(regNo);
                                String deptName = studentService.getStudentDepartmentName(regNo);
                                String sessionName = studentService.getStudentSessiontName(regNo);
                                String program = studentService.getStudentProgramName(regNo);

                                int semx = studentInfo.getStudentBatchIdFk().getDegreeOfferedIdFk().getDegreeIdFk().getTotalSemester();

                            %>
                            <legend style="font-size: 22px">Syllabus</legend>
                            <h4 style="text-transform: uppercase;">DEPARTMENT OF <%=deptName%></h4>
                            <h4><%=program%></h4>
                            <h4>Session: <%=sessionName%></h4>

                            <%
                                for (int i = 0; i < semx; i++) {
                                    String semesterName = FormatService.formatSemesterName(i + 1);
                            %>
                            <div class="panel panel-default">
                                <div class="panel-heading" style="font-size: 14px">
                                    <b ><%=semesterName%></b>
                                </div>
                                <div class="panel-body padding-none">
                                    <table class="table table-bordered margin-none curriculum-info">
                                        <thead>
                                            <tr>
                                                <th style="width: 15%"><b>Course Code</b></th>
                                                <th style="width: 35%"><b>Course Title</b></th>
                                                <th style="width: 10%" class="text-center"><b>Dept.</b></th>
                                                <th style="width: 15%" class="text-center"><b>Hours/Week<br>Theory+Lab</b></th>
                                                <th style="width: 5%" class="text-center"><b>Credits</b></th>
                                                <th style="width: 20%" class="text-center"><b>Prerequisite</b></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                double totalCredit = 0;
                                                int theoryCredit = 0;
                                                int labCredit = 0;
                                                List<SyllabusPOJO> courseList;
                                                courseList = studentService.getStudentSyllabusAsEntity(regNo,i + 1);
                                                for (int j = 0; j < courseList.size(); j++) {
                                                    String courseCode = courseList.get(j).getCourseCode();
                                                    String title = courseList.get(j).getTitle();
                                                    String dept = courseList.get(j).getOfferringDept();
                                                    double credit = courseList.get(j).getCredit();
                                                    totalCredit += credit;
                                                    String prereq = courseList.get(j).getPrerequisite();
                                                    int hrsWeek = courseList.get(j).getHrsWeek().intValue();

                                                    String hrsWeekString = "";
                                                    if (courseList.get(j).isTheoryOrLab()) {
                                                        theoryCredit += hrsWeek;
                                                        hrsWeekString = String.valueOf(hrsWeek) + " + 0";
                                                    } else {
                                                        labCredit += hrsWeek;
                                                        hrsWeekString = "0 + " + String.valueOf(hrsWeek);
                                                    }
                                            %>                                            
                                            <tr >
                                                <td style="font-size: 11.5px"><%=courseCode%></td>   
                                                <td style="font-size: 11.5px"><%=title%></td>   
                                                <td class="text-center" style="font-size: 11.5px"><%=dept%></td> 
                                                <td class="text-center" style="font-size: 11.5px"><%=hrsWeekString%></td> 
                                                <td class="text-center" style="font-size: 11.5px"><%=credit%></td> 
                                                <td style="font-size: 11.5px"><%=prereq%></td>   
                                            </tr>
                                            <%
                                                }
                                            %>

                                        </tbody>
                                        <tr>
                                            <td colspan="3" class="text-center"><b>Total</b></td>
                                            <td class="text-center"><b><%=theoryCredit%> + <%=labCredit%></b></td>
                                            <td class="text-center" ><label class="badge bg-success"><b><%=totalCredit%></b></label></td>
                                            <td></td>
                                        </tr>

                                        </tbody>
                                    </table>
                                </div>
                            </div>

                            <%
                                }

                                if (studentService.isStudentSyllabusHasOptionalCourses(regNo)) {                                   
                            %>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <b>Optional Courses: <i>Options</i></b>
                                </div>
                                <div class="panel-body padding-none">
                                    <table class="table table-bordered margin-none curriculum-info">
                                        <thead>
                                        <tr>
                                            <th style="width: 15%"><b>Course Code</b></th>
                                            <th style="width: 45%"><b>Course Title</b>/th>
                                            <th style="width: 10%" class="text-center"><b>Dept.</b></th>
                                            <th style="width: 10%" class="text-center"><b>Credits</b></th>
                                            <th style="width: 20%"><b>Prerequisite</b></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                    List<SyllabusPOJO> courseListOp;
                                                courseListOp = studentService.getStudentSyllabusAsEntity(regNo,0);
                                                for (int j = 0; j < courseListOp.size(); j++) {
                                                    String courseCode = courseListOp.get(j).getCourseCode();
                                                    String title = courseListOp.get(j).getTitle();
                                                    String dept = courseListOp.get(j).getOfferringDept();
                                                    double credit = courseListOp.get(j).getCredit();
                                                    
                                                    String prereq = courseListOp.get(j).getPrerequisite();
                                    %>
                                            <tr>
                                                <td><%=courseCode%></td>   
                                                <td><%=title%></td>   
                                                <td class="text-center"><%=dept%></td>   
                                                <td class="text-center"><%=credit%></td> 
                                                <td><%=prereq%></td>   
                                            </tr>
                                            <%
                                                }
                                            %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                            <%                                
                                }
                            %> 
                        </fieldset>
                    </div>


                </div>
            </div>
        </div>
        <%@include  file="../WEB-INF/jspf/Footer.jspf"%>
    </body>
</html>
