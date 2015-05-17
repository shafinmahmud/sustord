<%-- 
    Document   : GradesBySemester
    Created on : Jan 27, 2015, 7:04:04 PM
    Author     : SHAFIN
--%>

<%@page import="me.shafin.sustord.controller.GradeBySemesterController"%>
<%@page import="me.shafin.sustord.utility.CgpaCalculation"%>
<%@page import="java.util.ArrayList"%>
<%@page import="me.shafin.sustord.bean.SyllabusPOJO"%>
<%@page import="java.util.List"%>
<%@page import="me.shafin.sustord.utility.FormatService"%>
<%@page import="me.shafin.sustord.model.StudentInfo"%>
<%@page import="me.shafin.sustord.service.StudentService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width">
        <title>Grade by Semester</title>

        <!-- Including Bootstrap-->
        <%@include  file="../WEB-INF/jspf/BootstrapInclude.jspf"%>
        <!-- style sheet -->
        <link href="../page_files/css/style.css" rel="stylesheet">
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

                            <legend>Grade Report by Semester
                                <a class="btn btn-danger btn-xs pull-right" type="submit" value="print" onClick="window.print()">Print</a>
                            </legend>
                            <%StudentService studentService;
                                studentService = (StudentService) session.getAttribute("studentService");
                                String regNo = (String) request.getSession().getAttribute("regNo");

                                GradeBySemesterController controller = new GradeBySemesterController(regNo);

                                String name = controller.getStudentName();
                                String sessionName = controller.getStudentAcademicSession();
                                String dept = controller.getStudentDepartmentName();
                                String program = controller.getStudentProgramName();
                                String school = controller.getStudentSchoolName();

                                double creditsCompleted = studentService.getCreditsCompleted(regNo);
                                int coursesCompleted = studentService.getCompletedCourses(regNo);
                                int coursesTotal = studentService.getTotalCourses(regNo);
                                double creditsTotal = studentService.getTotalCredits(regNo);
                                double cgpa = studentService.getCGPA(regNo);


                            %>
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
                                            <td><%=creditsCompleted%></td>
                                            <td>Program</td>
                                            <td>:</td>
                                            <td><%=program%></td>
                                        </tr>
                                        <tr>
                                            <td>Course(s) Completed</td>
                                            <td>:</td>
                                            <td><%=coursesCompleted%> (<%=coursesTotal%>)</td>
                                            <td>Session</td>
                                            <td>:</td>
                                            <td><%=sessionName%></td>
                                        </tr>
                                        <tr>
                                            <td>Total Credits</td>
                                            <td>:</td>
                                            <td><%=creditsTotal%></td>
                                            <td>CGPA</td>
                                            <td>:</td>
                                            <td class="badge pull-left"><%=cgpa%></td>
                                        </tr>

                                    </tbody></table>
                                <div class="text-center"><h4><label>Taken Semesters</label></h4> </div>  


                                <%
                                    int currentSemester = studentService.getStudentCurrentSemester(regNo);
                                    for (int i = 0; i < currentSemester; i++) {
                                        String semesterName = FormatService.formatSemesterName(i + 1);
                                %>
                                <label Style="font-size: 13px"><%=semesterName%></label>
                                <table class="table table-bordered">
                                    <tbody>
                                        <tr>
                                            <td style="width: 15%"><b>Course Code</b></td>
                                            <td style="width: 45%"><b>Title</b></td>
                                            <td style="width: 15%" class="text-center"><b>Credits</b></td>      
                                            <td style="width: 10%" class="text-center"><b>Grade</b></td>
                                            <td style="width: 10%" class="text-center"><b>Grade Point</b></td>

                                        </tr>
                                        <%
                                            List<SyllabusPOJO> courses = studentService.getStudentRegisteredCoursesAsEntity(regNo, i + 1);
                                            List<SyllabusPOJO> drops = new ArrayList<SyllabusPOJO>();
                                            //for current semester
                                            double passedCredit = CgpaCalculation.getPassedCreditOfSemester(courses);
                                            double totalCredit = CgpaCalculation.getTotalCreditOfSemester(courses);
                                            double gradePoint = CgpaCalculation.getGradePointOfSemester(courses);
                                            String gradeLetter = CgpaCalculation.getGradeLetterFromGradePoint(gradePoint);

                                            //for cummilative calculation
                                            List<SyllabusPOJO> cummilativeCourses = studentService.getStudentRegisteredCoursesAll(regNo, i + 1);
                                            double passedCreditC = CgpaCalculation.getPassedCreditOfSemester(cummilativeCourses);
                                            double totalCreditC = CgpaCalculation.getTotalAttendedCredit(cummilativeCourses);
                                            double gradePointC = CgpaCalculation.getGradePointOfSemester(cummilativeCourses);
                                            String gradeLetterC = CgpaCalculation.getGradeLetterFromGradePoint(gradePointC);
                                            for (SyllabusPOJO s : courses) {

                                                if (s.getOfferingSemester() != i + 1) {
                                                    drops.add(s);
                                                } else {
                                                    String code = s.getCourseCode();
                                                    String title = s.getTitle();
                                                    Double credit = s.getCredit();
                                                    String grade = s.getGrade();
                                                    String point = s.getPoint();


                                        %>
                                        <tr>
                                            <td><%=code%></td>
                                            <td><%=title%></td>
                                            <td class="text-center"><%=credit%></td> 
                                            <td class="text-center"><%=grade%></td>
                                            <td class="text-center"><%=point%></td>

                                        </tr>
                                        <%}
                                            }

                                            if (!drops.isEmpty()) {
                                        %>
                                        <tr>
                                            <td colspan="6" style="text-align:left"> <i>**Drop/Advance courses</i> </td>
                                        </tr>
                                        <%
                                            for (SyllabusPOJO d : drops) {
                                                String code = d.getCourseCode();
                                                String title = d.getTitle();
                                                Double credit = d.getCredit();
                                                String grade = d.getGrade();
                                                String point = d.getPoint();

                                        %>
                                        <tr>
                                            <td><%=code%></td>
                                            <td><%=title%></td>
                                            <td class="text-center"><%=credit%></td> 
                                            <td class="text-center"><%=grade%></td>
                                            <td class="text-center"><%=point%></td>

                                        </tr>
                                        <%}
                                            }
                                        %>

                                        <tr class="text-center">
                                            <td colspan="2" style="text-align:center"><b>GPA</b></td>
                                            <td ><b><%=passedCredit%> (<%=totalCredit%>)</b></td>
                                            <td><b><%=gradeLetter%></b></td>
                                            <td colspan="2"><b><%=gradePoint%></b></td>
                                        </tr>
                                        <tr class="text-center">
                                            <td colspan="2" ><b>CGPA</b></td>
                                            <td ><b><%=passedCreditC%> (<%=totalCreditC%>)</b></td>
                                            <td><b><%=gradeLetterC%></b></td>
                                            <td colspan="2"><b><%=gradePointC%></b></td>
                                        </tr>

                                    </tbody></table>
                                    <%}
                                    %>

                            </div>
                        </fieldset>
                    </div>


                </div>
            </div>
        </div>
        <%@include  file="../WEB-INF/jspf/Footer.jspf"%>
    </body>
</html>
