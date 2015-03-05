<%-- 
    Document   : dataform
    Created on : Jan 9, 2015, 11:51:49 PM
    Author     : SHAFIN
--%>

<%@page import="me.shafin.sustord.service.FormatService"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="me.shafin.sustord.entity.Syllabus"%>
<%@page import="me.shafin.sustord.entity.StudentInfo"%>
<%@page import="me.shafin.sustord.entity.PersonalInfo"%>
<%@page import="me.shafin.sustord.service.StudentService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css"></style>
        <meta name="viewport" content="width=device-width">
        <title>Personal Information</title>
        <%@include  file="../WEB-INF/jspf/BootstrapInclude.jspf"%>
        <link href="../page_files/css/style.css" rel="stylesheet">

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>

        <script src="../page_files/scripts/personal-info-script.js"></script>




    </head>
    <body>

        <%@include  file="../WEB-INF/jspf/AccessValidation.jspf"%>
        <%@include  file="../WEB-INF/jspf/NavBar.jspf"%>
        <div class="portal-body">
            <div class="row">
                <%@include  file="../WEB-INF/jspf/SideBar.jspf"%>
                <div id="main-content" class="col-md-10 column800">

                    <div class="margin5">

                        <legend>Personal Information</legend>
                        <%                            StudentService service = (StudentService) session.getAttribute("studentService");
                            String regNo = (String) request.getSession().getAttribute("regNo");
                            StudentInfo studentInfo = service.getStudentInfoObjectFromRegNo(regNo);

                            PersonalInfo personalInfo = studentInfo.getPersonalInfo();
                            String reg = regNo;
                            String name = personalInfo.getName();
                            String presentAddress = personalInfo.getPresentAddress();
                            String permanentAddress = personalInfo.getPermanentAddress();

                            if (presentAddress == null) {
                                presentAddress = "null,null,null,null,null";
                            }

                            if (permanentAddress == null) {
                                permanentAddress = "null,null,null,null,null";
                            }

                            String[] parts1 = {};
                            String[] parts2 = {};

                            parts1 = presentAddress.split(",");
                            String presentStreet = parts1[0];
                            String presentArea = parts1[1];
                            String presentThana = parts1[2];
                            String presentDistrict = parts1[3];
                            String presentCountry = parts1[4];

                            parts2 = permanentAddress.split(",");
                            String permanentStreet = parts2[0];
                            String permanentArea = parts2[1];
                            String permanentThana = parts2[2];
                            String permanentDistrict = parts2[3];
                            String permanentCountry = parts2[4];


                        %>


                        <div id = "personal-divsec">
                            <form id = "personal-info-form" action="" method="get" role="form"> 

                                <div  class="col-lg-9">

                                    <table class="table">

                                        <tr>
                                            <td style="text-align: right">
                                                <label >Registration No:</label>
                                            </td>
                                            <td style="text-align: left">
                                                <p style="font-size: 14px"><%=reg%></p>
                                            </td>
                                        </tr> 
                                        <tr>
                                            <td style="text-align: right">
                                                <label >Name:</label>
                                            </td>
                                            <td style="text-align: left">
                                                <p style="font-size: 14px"><%=name%><p>
                                            </td>
                                        </tr> 

                                        <tr>
                                            <td style="text-align: right">
                                                <label>Father's name:</label>
                                            </td>
                                            <td style="text-align: left">
                                                <input id="father" style="" class="form-control input-sm" name="fathersname" type="text" placeholder="Your father's name" value="<%=personalInfo.getFathersName()%>">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="text-align: right"><label>Mother's name:</label></td>
                                            <td style="text-align: left">
                                                <input id="mother" style="" class="form-control input-sm" name="mothersname" type="text" placeholder="Your mother's name" value="<%=personalInfo.getMothersName()%>">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="text-align: right"><label>Present address:</label></td>

                                            <td style="text-align: left">

                                                <table class="table-condensed">
                                                    <tr>
                                                        <td class="col-lg-7" >
                                                            <input id="present-street"  style="" class="form-control input-sm" name="presentstreet" type="text"  placeholder="house/street" value="<%=presentStreet%>"/>
                                                        </td>
                                                        <td class="col-lg-5" >
                                                            <input id="present-area" style="" class="form-control input-sm" name="presentarea" type="text"  placeholder="Area" value="<%=presentArea%>"/>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td >
                                                            <input id="present-thana" style="" class="form-control input-sm" name="presentthana" type="text"  placeholder="Thana" value="<%=presentThana%>"/>
                                                        </td>
                                                        <td>
                                                            <input id="present-district" style="" class="form-control input-sm" name="presentdistrict" type="text"   placeholder="District" value="<%=presentDistrict%>" />
                                                        </td>
                                                    </tr>

                                                    <tr>
                                                        <td >

                                                        </td>
                                                        <td>
                                                            <select id="present-country" name="presentcountry" class="form-control pull-left input-sm"  style="width:100%; ">
                                                                <option value="0" style="display:none" >Country</option>
                                                                <option value="1" style="display:none" >*Country</option>
                                                                <option value="Bangladesh">Bangladesh</option>
                                                                <option value="Others">Others</option>      
                                                            </select>
                                                        </td>

                                                    </tr>


                                                </table>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td style="text-align: right"><label>Permanent address:</label></td>
                                            <td style="text-align: left"> <table class="table-condensed">
                                                    <tr>
                                                        <td class="col-lg-7" >
                                                            <input id="permanent-street" style="" name="permanentstreet" class="form-control input-sm" type="text"  placeholder="house/street" value="<%=permanentStreet%>"/>
                                                        </td>
                                                        <td class="col-lg-5" >
                                                            <input id="permanent-area" style="" name="permanentarea" class="form-control input-sm" type="text"  placeholder="Area" value="<%=permanentArea%>" />
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td >
                                                            <input id="permanent-thana" style="" name="permanentthana" class="form-control input-sm" type="text"  placeholder="Thana" value="<%=permanentThana%>"/>
                                                        </td>
                                                        <td>
                                                            <input id="permanent-district" style="" name="permanentdistrict" class="form-control input-sm" type="text"  placeholder="District" value="<%=permanentDistrict%>"/>
                                                        </td>
                                                    </tr>

                                                    <tr>
                                                        <td >

                                                        </td>
                                                        <td>
                                                            <select id = "permanent-country" name="permanentcountry" class="form-control pull-left input-sm"  style="width:100%; ">
                                                                <option value="0" style="display:none" >Country</option>
                                                                <option value="1" style="display:none" >*Country</option>
                                                                <option value="Bangladesh">Bangladesh</option>
                                                                <option value="Others">Others</option>        
                                                            </select>
                                                        </td>
                                                    </tr>


                                                </table></td>
                                        </tr>
                                        <tr>
                                            <td style="text-align: right"><label>Phone:</label></td>
                                            <td style="text-align: left">
                                                <input id="phone" style="" name="phone" class="form-control input-sm" type="text" value="<%=personalInfo.getContact()%>"  placeholder="Your cell phone number ex: 01XXXXXXXXX" ></td>
                                        </tr>
                                        <tr>
                                            <td style="text-align: right"><label>Email:</label></td>
                                            <td style="text-align: left">
                                                <input id="email" style="" name="email" class="form-control input-sm" type="text" value="<%=personalInfo.getEmail()%>" placeholder="Your email address ex: example@domain.com" ></td>
                                        </tr>
                                        <tr>
                                            <td style="text-align: right"><label>Date of Birth:</label></td>
                                            <td style="text-align: left">
                                                <input  style="; width:190px" name="dob" id = "dob" class="form-control input-sm" type="text" value="<%=personalInfo.getDateOfBirth()%>" placeholder="mm / dd / yyyy"></td>
                                        </tr>


                                        <tr>
                                            <td style="text-align: right"><label>Gender:</label></td>
                                            <td style="text-align: left"> 
                                                <select class="form-control pull-left input-sm" id="sex" name="sex" style="width:190px; ">
                                                    <option value="0" style="display:none" >Choose your gender</option>
                                                    <option value="1" style="display:none" >*Choose your gender</option>
                                                    <option value="Male">Male</option>
                                                    <option value="Female">Female</option>       
                                                </select></td>
                                        </tr>
                                        <tr>
                                            <td style="text-align: right"><label>Religion:</label></td>
                                            <td style="text-align: left">
                                                <select class="form-control pull-left input-sm" id="religion" name="religion" style="width:190px; ">
                                                    <option value="0" style="display:none" >Religion of you</option>
                                                    <option value="1" style="display:none" >*Religion of you</option>
                                                    <option value="Muslim">Muslim</option>
                                                    <option value="Hindu">Hindu</option>
                                                    <option value="Christian">Christian</option>
                                                    <option value="Buddhist">Buddhist</option>
                                                    <option value="Others">Others</option>

                                                </select></td>
                                        </tr>

                                        <tr>
                                            <td style="text-align: right"><label>Marital Status:</label></td>
                                            <td style="text-align: left"> 
                                                <select class="form-control pull-left input-sm" id="marital-status" name="maritalstatus" style="width:190px; ">
                                                    <option value="0" style="display:none" >Are you married?</option>
                                                    <option value="1" style="display:none" >*Are you married?</option>
                                                    <option value="Single">Single</option>
                                                    <option value="Married">Married</option>

                                                </select></td>
                                        </tr>
                                        <tr>
                                            <td style="text-align: right"><label>Blood Group:</label></td>
                                            <td style="text-align: left">
                                                <select class="form-control pull-left input-sm" id="blood-group" name="bloodgroup" style="width:160px; ">
                                                    <option  value="0" style="display:none" >Your Blood group</option>
                                                    <option  value="1" style="display:none" >*Your Blood group</option>
                                                    <option value="A+ve">A+ve</option>
                                                    <option value="A-ve">A-ve</option>
                                                    <option value="B+ve">B+ve</option>
                                                    <option value="B-ve">B-ve</option>
                                                    <option value="AB+ve">AB+ve</option>
                                                    <option value="AB-ve">AB-ve</option>
                                                    <option value="O+ve">O+ve</option>
                                                    <option value="O-ve">O-ve</option>
                                                </select></td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td style="padding-top: 5%">
                                                <div>
                                                    <table class="table-condensed">
                                                        <tr>
                                                            <td>
                                                                <button  type="submit" class="btn btn-danger" id="confirm-personal">Submit</button>
                                                            </td>
                                                            <td>
                                                                <img  id="saving-anim" src="" height="25" alt="">
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </div>

                                            </td>
                                        </tr>                              

                                    </table>
                                </div>
                            </form>
                            <div class="col-md-3"> 
                                <img class="img-responsive" src="../page_files/images/profilepic/<%=service.getPhotoUrl(regNo)%>" alt="pic"> 
                                <h4> Choose Image </h4>
                                <form action="upload" method="post" enctype="multipart/form-data">
                                    <input class ="fileupload" type="file" name="file" />
                                    <input type="submit" value="upload" />
                                </form>
                            </div>

                            </div>

                        </div>
                    </div>
                </div>

            </div>

            <%@include  file="../WEB-INF/jspf/Footer.jspf"%>

            <script>


                var precountry = "<%=presentCountry%>";
                if (precountry !== "null")
                    $("#present-country").val(precountry);
                var percountry = "<%=permanentCountry%>";
                //alert(precountry+precountry.length+percountry+percountry.length);
                if (percountry !== "null")
                    $("#permanent-country").val(percountry);
                var gender = "<%=personalInfo.getSex()%>";
                if (gender != "null")
                    $("#sex").val(gender);
                var religion = "<%=personalInfo.getReligion()%>";
                if (religion !== "null")
                    $("#religion").val(religion);
                var maritalst = "<%=personalInfo.getMaritalStatus()%>";
                if (maritalst !== "null") {
                    $("#marital-status").val(maritalst);
                }


                var blood = "<%=personalInfo.getBloodGroup()%>";
                if (blood !== "null")
                    $("#blood-group").val(blood);
            </script>

    </body>

</html>


