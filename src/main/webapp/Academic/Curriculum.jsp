<%-- 
    Document   : Curriculum
    Created on : Jan 27, 2015, 6:43:49 PM
    Author     : SHAFIN
--%>

<%@page import="sm.sustord.bean.OptionalCoursePojo"%>
<%@page import="sm.sustord.bean.SyllabusCoursePojo"%>
<%@page import="sm.sustord.controller.CurriculumController"%>
<%@page import="java.util.List"%>
<%@page import="sm.sustord.bean.SyllabusPOJO"%>
<%@page import="sm.sustord.utility.FormatService"%>
<%@page import="sm.sustord.model.StudentInfo"%>
<%@page import="sm.sustord.service.StudentService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Curriculum</title>    
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width">

        <!-- Including Bootstrap-->
        <%@include  file="../WEB-INF/jspf/BootstrapInclude.jspf"%>
        <!-- style sheet for this page-->
        <link href="../page_files/css/style.css" rel="stylesheet">
    </head>
    <body>
        <!-- validating access to this page-->
        <%@include  file="../WEB-INF/jspf/AccessValidation.jspf"%>
        <!-- nav bar portion -->
        <%@include  file="../WEB-INF/jspf/NavBar.jspf"%>

        <div class="portal-body">
            <div class="row">
                <!-- Side bar portion -->
                <%@include  file="../WEB-INF/jspf/SideBar.jspf"%>

                <div id="main-content" class="col-md-10 column800">
                    <div class="margin5">
                        <fieldset>
                            <%                                String regNo = (String) request.getSession().getAttribute("regNo");

                                CurriculumController curriculumController = new CurriculumController(regNo);
                                String deptName = curriculumController.getStudentDepartmentName();
                                String sessionName = curriculumController.getStudentAcademicSession();
                                String program = curriculumController.getStudentProgramName();
                                int totalSemester = curriculumController.getTotalAcademicSemester();
                            %>
                            <legend style="font-size: 22px">Syllabus</legend>
                            <h4 style="text-transform: uppercase;">DEPARTMENT OF <%=deptName%></h4>
                            <h4><%=program%></h4>
                            <h4>Session: <%=sessionName%></h4>

                            <%
                                for (int i = 0; i < totalSemester; i++) {
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
                                                List<SyllabusCoursePojo> courseList;
                                                courseList = curriculumController.getAcademicSyllabus(i + 1);

                                                for (SyllabusCoursePojo course : courseList) {
                                                    String courseCode = course.getCourseCode();
                                                    String title = course.getCourseTitle();
                                                    String dept = course.getOfferingDeptCode();
                                                    double credit = course.getCredit();
                                                    totalCredit += credit;
                                                    String prereq = course.getPrerequisite();
                                                    int hrsWeek = course.getCourseHoursPerWeek().intValue();

                                                    String hrsWeekString = "";
                                                    if (course.isTheoryOrLab()) {
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
                                List<OptionalCoursePojo> optionalList = curriculumController.getOptionalCourses();
                                if (optionalList.size() > 0) {

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
                                            <%                                                for (OptionalCoursePojo optional : optionalList) {
                                                    String courseCode = optional.getCourseCode();
                                                    String title = optional.getCourseTitle();
                                                    String dept = optional.getOfferingDeptCode();
                                                    double credit = optional.getCredit();
                                                    String prereq = optional.getPrerequisite();
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
        <!-- Footer section -->
        <%@include  file="../WEB-INF/jspf/Footer.jspf"%>
    </body>
</html>
