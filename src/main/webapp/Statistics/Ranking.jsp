<%-- 
    Document   : Ranking
    Created on : Feb 3, 2015, 10:25:39 PM
    Author     : SHAFIN
--%>

<%@page import="me.shafin.sustord.bean.StudentPOJO"%>
<%@page import="java.util.List"%>
<%@page import="me.shafin.sustord.entity.StudentInfo"%>
<%@page import="me.shafin.sustord.service.StudentService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Ranking</title>
        <style type="text/css">
            [ng\:cloak],[ng-cloak],[data-ng-cloak],[x-ng-cloak],.ng-cloak,.x-ng-cloak,.ng-hide
            {display:none !important;
            }ng\:form{display:block;}.ng-animate-block-transitions{transition:0s all!important;
                                                                   -webkit-transition:0s all!important;}
            </style>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <meta name="viewport" content="width=device-width">
            <link href="../page_files/css/style.css" rel="stylesheet">
            <script src=""></script>
        </head>
        <body>
            <%@include  file="../WEB-INF/jspf/AccessValidation.jspf"%>
            <%@include  file="../WEB-INF/jspf/NavBar.jspf"%>

            <div class="portal-body">
            <div class="row">
                <%@include  file="../WEB-INF/jspf/SideBar.jspf"%>
                <div id="main-content" class="col-md-10 column800">
                    <div class="col-lg-12">
                        <fieldset>
                            <legend>Ranking by CGPA</legend>
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th style="width: 5%">#Rank</th>
                                        <th style="width: 40%">Name</th>
                                        <th style="width: 15%">Registration No</th>
                                        <th style="width: 20%; text-align: center">Completed Credits</th>
                                        <th style="width: 20%; text-align: center">CGPA</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%                                        StudentService studentService;
                                        studentService = (StudentService) session.getAttribute("studentService");
                                        String myRegNo = (String) request.getSession().getAttribute("regNo");
                                        List<StudentPOJO> students = studentService.getStudentRankList(myRegNo);

                                        for (StudentPOJO s : students) {
                                            String name = s.getName().toUpperCase();
                                            String reg = s.getRegistrationNo();
                                            double completedCredit = s.getCompletedCredits();
                                            double cgpa = s.getCgpa();
                                            
                                            if(myRegNo.equals(reg)){
                                            
                                    %>
                                           <tr>
                                               <td class="text-center"><b>#<%=students.indexOf(s)+1%></b></td>
                                               <td><b><%=name%></b></td>
                                               <td  class="text-center"><b><%=reg%></b></td>
                                               <td  class="text-center"><b><%=completedCredit%></b></td>
                                               <td  class="text-center"><b><%=cgpa%></b></td>
                                            </tr>
                                    <%
                                        }else{
                                                %>
                                                <tr>
                                               <td class="text-center">#<%=students.indexOf(s)+1%></td>
                                                <td><%=name%></td>
                                                <td  class="text-center"><%=reg%></td>
                                                <td  class="text-center"><%=completedCredit%></td>
                                                <td  class="text-center"><%=cgpa%></td>
                                            </tr>
                                                <%
                                            }
                                        }
                                    %>
                                </tbody>

                            </table>

                        </fieldset>

                    </div>

                </div>
            </div>
        </div>
        <%@include  file="../WEB-INF/jspf/Footer.jspf"%>
    </body>
</html>
