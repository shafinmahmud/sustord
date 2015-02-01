<%-- 
    Document   : userprofile
    Created on : Jan 7, 2015, 4:33:43 AM
    Author     : SHAFIN
--%>

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

                                String fatherName = studentInfo.getPersonalInfo().getFathersName();
                                String motherName = studentInfo.getPersonalInfo().getMothersName();
                                String presentAddress = studentInfo.getPersonalInfo().getPresentAddress();
                                String permanentAddress = studentInfo.getPersonalInfo().getPermanentAddress();
                                String phone = studentInfo.getPersonalInfo().getContact();
                                String email = studentInfo.getPersonalInfo().getEmail();
                                String sex = studentInfo.getPersonalInfo().getSex();
                                String religion = studentInfo.getPersonalInfo().getReligion();
                                String dob = studentInfo.getPersonalInfo().getDateOfBirth();
                                String marital = studentInfo.getPersonalInfo().getMaritalStatus();
                                String nationality = studentInfo.getPersonalInfo().getNationality();
                                String blood = studentInfo.getPersonalInfo().getBloodGroup();


                            %>
                            <legend style="font-size: 22px"><%=name%></legend>

                            <div class="col-md-8">
                                <table class="table ">
                                    <tbody>

                                        <tr>
                                            <td style="text-align: right; width: 30%">Registration no:</td>
                                            <td style="text-align: left; width: 70%"><%=regNo%></td>
                                        </tr>
                                        <tr>
                                            <td style="text-align: right">CGPA :</td>
                                            <td style="text-align: left">3.04</td>
                                        </tr>
                                        <tr>
                                            <td style="text-align: right">Credit(s) completed :</td>
                                            <td style="text-align: left">57</td>
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
                                <%
                                    if (regNo.equals("2011331001")) {
                                %>
                                <img class="img-responsive" src="../page_files/images/2011331001.jpg" data-src="holder.js/170x200" alt="pic"> 
                                <%} else {
                                %>
                                <img class="img-responsive" src="../page_files/images/empty-profile-pic.gif" data-src="holder.js/170x200" alt="pic"> 
                                <%}
                                %> 
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

