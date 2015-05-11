<%-- 
    Document   : userprofile
    Created on : Jan 7, 2015, 4:33:43 AM
    Author     : SHAFIN
--%>

<%@page import="me.shafin.sustord.controller.UserProfileController"%>
<%@page import="me.shafin.sustord.model.StudentInfo"%>
<%@page import="me.shafin.sustord.service.StudentService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Profile</title>
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
                            <%                                StudentService studentService;
                                studentService = (StudentService) session.getAttribute("studentService");
                                
                                
                                String regNo = (String) request.getSession().getAttribute("regNo");
                                
                                UserProfileController profileController = new UserProfileController();
                                String name = profileController.getStudentName();

                                String sessionName = profileController.getStudentAcademicSession();
                                String dept = profileController.getStudentDepartmentName();
                                String program = profileController.getStudentProgramName();
                                String school = profileController.getStudentSchoolName();

                                double creditsCompleted = studentService.getCreditsCompleted(regNo);
                                double creditsTotal = studentService.getTotalCredits(regNo);
                                double cgpa = studentService.getCGPA(regNo);

                                String fatherName = profileController.getFatherName();
                                String motherName = profileController.getMotherName();
                                String presentAddress = profileController.getPresentAddress();
                                String permanentAddress = profileController.getPermanentAddress();
                                String phone = profileController.getPhone();
                                String email = profileController.getEmail();
                                String sex = profileController.getSex();
                                String religion = profileController.getReligion();
                                String dob = profileController.getDob();
                                String marital = profileController.getMaritalStatus();
                                String nationality = profileController.getNationality();
                                String blood = profileController.getBloodGroup();
                                
                                String photoUrl = profileController.getStudentPhotoUrl();


                            %>
                            <legend style="font-size: 22px"><%=name%>
                            <a class="btn btn-danger btn-xs pull-right" href="EditProfile.jsp" type="submit">Edit</a>
                            </legend>
    
                            <div class="col-md-8">
                                <table class="table ">
                                    <tbody>

                                        <tr>
                                            <td style="text-align: right; width: 30%">Registration no:</td>
                                            <td style="text-align: left; width: 70%"><%=regNo%></td>
                                        </tr>
                                        <tr>
                                            <td style="text-align: right">CGPA :</td>
                                            <td style="text-align: left"><%=cgpa%></td>
                                        </tr>
                                        <tr>
                                            <td style="text-align: right">Credit(s) completed :</td>
                                            <td style="text-align: left"><%=creditsCompleted%> (<%=creditsTotal%>)</td>
                                        </tr>
                                        <tr>
                                            <td style="text-align: right">Session :</td>
                                            <td style="text-align: left"><%=sessionName%></td>
                                        </tr>
                                        <tr>
                                            <td style="text-align: right">Program :</td>
                                            <td style="text-align: left"><%=program%></td>
                                        </tr>
                                        <tr>
                                            <td style="text-align: right">Department :</td>
                                            <td style="text-align: left"><%=dept%></td>
                                        </tr>                                      

                                        <tr>
                                            <td style="text-align: right">School :</td>
                                            <td style="text-align: left"><%=school%></td>
                                        </tr>

                                    </tbody>
                                </table>
                            </div>
                            <div class="col-md-4">           
                                <img class="img-responsive" src="../page_files/images/profilepic/<%=photoUrl%>" alt="pic">                                
                            </div>


                            <div class="col-md-8">
                                <table class="table">
                                    <tbody>
                                        <tr>
                                            <td style="text-align: right; width: 30%">Father's name:</td>
                                            <td style="text-align: left"><%=fatherName%></td>
                                        </tr>
                                        <tr>
                                            <td style="text-align: right">Mother's name:</td>
                                            <td style="text-align: left"><%=motherName%></td>
                                        </tr>
                                        <tr>
                                            <td style="text-align: right">Present address:</td>
                                            <td style="text-align: left"><%=presentAddress%></td>
                                        </tr>
                                        <tr>
                                            <td style="text-align: right">Permanent Address:</td>
                                            <td style="text-align: left"><%=permanentAddress%></td>
                                        </tr>
                                        <tr>
                                            <td style="text-align: right">Phone:</td>
                                            <td style="text-align: left"><%=phone%></td>
                                        </tr>
                                        <tr>
                                            <td style="text-align: right">Email:</td>
                                            <td style="text-align: left"><%=email%></td>
                                        </tr>

                                        <tr>
                                            <td style="text-align: right">Date of Birth:</td>
                                            <td style="text-align: left"><%=dob%></td>
                                        </tr>
                                        <tr>
                                            <td style="text-align: right">Sex:</td>
                                            <td style="text-align: left"><%=sex%></td>
                                        </tr>
                                        <tr>
                                            <td style="text-align: right">Religion:</td>
                                            <td style="text-align: left"><%=religion%></td>
                                        </tr>
                                        <tr>
                                            <td style="text-align: right">Nationality:</td>
                                            <td style="text-align: left"><%=nationality%></td>
                                        </tr>
                                        <tr>
                                            <td style="text-align: right">Marital Status:</td>
                                            <td style="text-align: left"><%=marital%></td>
                                        </tr>
                                        <tr>
                                            <td style="text-align: right">Blood Group:</td>
                                            <td style="text-align: left"><%=blood%></td>
                                        </tr>
                                    </tbody>
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

