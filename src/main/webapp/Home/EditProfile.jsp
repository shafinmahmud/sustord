<%-- 
    Document   : dataform
    Created on : Jan 9, 2015, 11:51:49 PM
    Author     : SHAFIN
--%>

<%@page import="me.shafin.sustord.controller.EditProfileController"%>
<%@page import="me.shafin.sustord.service.FormatService"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="me.shafin.sustord.service.StudentService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css"></style>
        <meta name="viewport" content="width=device-width">
        <title>Edit Profile</title>
        <!-- jQuery library-->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>  

        <!-- Including Bootstrap-->
        <%@include  file="../WEB-INF/jspf/BootstrapInclude.jspf"%>

        <!-- style sheet for this page-->
        <link href="../page_files/css/style.css" rel="stylesheet">

        <!-- script for this page-->
        <script src="edit-profile-script.js"></script>
        <style>
            #a-form {list-style: none; margin:0; padding: 0; }
            #a-form li {}
            #a-form input {display:inline-block; margin-right: 1em; }
        </style>


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
                        <%                            String regNo = (String) request.getSession().getAttribute("regNo");

                            EditProfileController controller = new EditProfileController(regNo);
                            String name = controller.getStudentName();

                            String fatherName = controller.getFatherName();
                            String motherName = controller.getMotherName();
                            String presentAddress = controller.getPresentAddress();
                            String permanentAddress = controller.getPermanentAddress();
                            String phone = controller.getPhone();
                            String email = controller.getEmail();
                            String sex = controller.getSex();
                            String religion = controller.getReligion();
                            String dob = controller.getDob();
                            String marital = controller.getMaritalStatus();
                            String blood = controller.getBloodGroup();

                            String photoUrl = controller.getStudentPhotoUrl();

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



                        <form id="info-form" class="form-horizontal col-sm-8" role="form">

                            <div class="form-group">
                                <label class="control-label col-sm-3">Registration No:</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control input-sm"  value="<%=regNo%>" readonly>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-3">Name:</label>
                                <div class="col-sm-9">        
                                    <input type="text" class="form-control input-sm"  value="<%=name%>" readonly>
                                </div>
                            </div>

                            <div id="fathername-div" class="form-group">
                                <label class="control-label col-sm-3">Father's name:</label>
                                <div class="col-sm-9">    
                                    <input type="text" id="father" class="form-control input-sm" name="fathersname"  placeholder="Your father's name" value="<%=fatherName%>">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-3">Mother's name:</label>
                                <div class="col-sm-9">    
                                    <input type="text" id="mother" class="form-control input-sm" name="mohtersname"  placeholder="Your mother's name" value="<%=motherName%>">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-3">Present address:</label>
                                <div class="col-sm-9 form-inline">   
                                    <ul id="a-form">
                                        <li>
                                            <input id="present-street"  style="" class="form-control input-sm" name="presentstreet" type="text"  placeholder="house/street" value="<%=presentStreet%>"/>      
                                            <input id="present-area" style="" class="form-control input-sm" name="presentarea" type="text"  placeholder="Area" value="<%=presentArea%>"/>
                                        </li>

                                        <li>
                                            <input id="present-thana" style="" class="form-control input-sm" name="presentthana" type="text"  placeholder="Thana" value="<%=presentThana%>"/>
                                            <input id="present-district" style="" class="form-control input-sm" name="presentdistrict" type="text"   placeholder="District" value="<%=presentDistrict%>" />
                                        </li>
                                        <li>
                                            <select id="present-country" name="presentcountry" class="form-control pull-left input-sm"  style="width:45%; ">
                                                <option value="0" style="display:none" >Country</option>
                                                <option value="1" style="display:none" >*Country</option>
                                                <option value="Bangladesh">Bangladesh</option>
                                                <option value="Others">Others</option>      
                                            </select>
                                        </li>

                                    </ul>

                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-3">Permanent address:</label>
                                <div class="col-sm-9 form-inline">   
                                    <ul id="a-form">
                                        <li>
                                            <input id="permanent-street" style="" name="permanentstreet" class="form-control input-sm" type="text"  placeholder="house/street" value="<%=permanentStreet%>"/>
                                            <input id="permanent-area" style="" name="permanentarea" class="form-control input-sm" type="text"  placeholder="Area" value="<%=permanentArea%>" />
                                        </li>
                                        <li>
                                            <input id="permanent-thana" style="" name="permanentthana" class="form-control input-sm" type="text"  placeholder="Thana" value="<%=permanentThana%>"/>
                                            <input id="permanent-district" style="" name="permanentdistrict" class="form-control input-sm" type="text"  placeholder="District" value="<%=permanentDistrict%>"/>
                                        </li>
                                        <li>
                                            <select id = "permanent-country" name="permanentcountry" class="form-control pull-left input-sm"  style="width:45%; ">
                                                <option value="0" style="display:none" >Country</option>
                                                <option value="1" style="display:none" >*Country</option>
                                                <option value="Bangladesh">Bangladesh</option>
                                                <option value="Others">Others</option>        
                                            </select>
                                        </li>
                                    </ul>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-3">Phone:</label>
                                <div class="col-sm-9"> 
                                    <input id="phone" name="phone" class="form-control input-sm" type="tel" value="<%=phone%>"  placeholder="Your cell phone number ex: 01XXXXXXXXX" required>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-3">Email:</label>
                                <div class="col-sm-9"> 
                                    <input id="email" style="" name="email" class="form-control input-sm" type="email" value="<%=email%>" placeholder="Your email address ex: example@domain.com" >
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-3">Date of Birth:</label>
                                <div class="col-sm-9"> 
                                    <input  style="width:190px" name="dob" id = "dob" class="form-control input-sm" type="date" value="<%=dob%>" placeholder="mm / dd / yyyy">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-3">Gender:</label>
                                <div class="col-sm-9"> 
                                    <select class="form-control pull-left input-sm" id="sex" name="sex" style="width:190px; ">
                                        <option value="0" style="display:none" >Choose your gender</option>
                                        <option value="1" style="display:none" >*Choose your gender</option>
                                        <option value="Male">Male</option>
                                        <option value="Female">Female</option>       
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-3">Religion:</label>
                                <div class="col-sm-9"> 
                                    <select class="form-control pull-left input-sm" id="religion" name="religion" style="width:190px; ">
                                        <option value="0" style="display:none" >Religion of you</option>
                                        <option value="1" style="display:none" >*Religion of you</option>
                                        <option value="Muslim">Muslim</option>
                                        <option value="Hindu">Hindu</option>
                                        <option value="Christian">Christian</option>
                                        <option value="Buddhist">Buddhist</option>
                                        <option value="Others">Others</option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-3">Marital status:</label>
                                <div class="col-sm-9"> 
                                    <select class="form-control pull-left input-sm" id="marital-status" name="maritalstatus" style="width:190px; ">
                                        <option value="0" style="display:none" >Are you married?</option>
                                        <option value="1" style="display:none" >*Are you married?</option>
                                        <option value="Single">Single</option>
                                        <option value="Married">Married</option>
                                    </select>
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="control-label col-sm-3">Marital status:</label>
                                <div class="col-sm-9"> 
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
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-9">
                                    <div class="col-xs-4">
                                        <button  type="submit" class="btn btn-danger" id="confirm-personal">Submit</button>
                                    </div>
                                    <div class="col-xm-8">
                                        <img  id="saving-anim" src="" height="30">
                                    </div>                                   
                                </div>
                            </div>

                        </form>
                        <div class="col-lg-4"> 
                            <img class="img-responsive" src="../page_files/images/profilepic/<%=photoUrl%>" alt="pic"> 
                            <!--<h4> Choose Image </h4>
                            <form action="upload" method="post" enctype="multipart/form-data">
                                <input class ="fileupload" type="file" name="file" />
                                <input type="submit" value="upload" />
                            </form>-->
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
            var gender = "<%=sex%>";
            if (gender != "null")
                $("#sex").val(gender);
            var religion = "<%=religion%>";
            if (religion !== "null")
                $("#religion").val(religion);
            var maritalst = "<%=marital%>";
            if (maritalst !== "null") {
                $("#marital-status").val(maritalst);
            }

            var blood = "<%=blood%>";
            if (blood !== "null")
                $("#blood-group").val(blood);
        </script>

    </body>

</html>


