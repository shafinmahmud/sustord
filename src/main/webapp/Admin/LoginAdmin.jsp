<%-- 
    Document   : LoginAdmin
    Created on : Dec 25, 2014, 10:30:37 AM
    Author     : SHAFIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width">       
        <title>SUST-ORD</title>

        <!-- jQuery library-->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>       

        <!-- style sheet for this page-->
        <link href="../page_files/css/login.css" rel="stylesheet">
        <!-- java script for the login -->
        <script src="admin-login-script.js"></script>
    </head>
    <body>

        <!-- validating login status-->
        <%
            try {
                if (session.getAttribute("user").equals("batchAdmin")) {
                    if (session.getAttribute("loginStatus").equals("ok")) {
                        response.sendRedirect("./HomeAdmin.jsp");
                    }
                }
            } catch (Exception e) {

            }
        %>

        <div class="row">
            <div class="col-md-7 col-sm-7 hidden-xs login_left_admin">

            </div>
            <div class="col-sm-12 login_panel">
                <%@include  file="../WEB-INF/jspf/BrandLogo.jspf"%>
                <div id="before-form" class="login_header">Sign in with your Admin ID.</div>
                <div>                  
                    <p id="messagebox" class="requiredwarning small"></p>
                </div>

                <form id = "loginform" action="" method="post" role="form"> 
                    <div class="form-group" style="display: none;">
                        <input id="usertype" name="UserType" value="batchAdmin">    
                    </div>
                    <div id="reg-no" class="form-group">
                        <label class="control-label" for="username">Admin ID</label>
                        <input id="username" autocomplete="off" class="form-control valid"  name="UserName" type="text" value="">
                        <p id="need-id-error" class="requiredwarning small"></p>
                    </div>
                    <div id="pass" class="form-group">
                        <label class="control-label" for="password">Password</label>
                        <input id="password"  autocomplete="off" class="form-control valid" name="Password" type="password">
                        <p id="need-pass-error" class="requiredwarning small"></p>
                    </div> 
                    <div class="form-group">
                        <div>
                            <div >
                                <button type="submit" class="btn btn-primary" id="login" >Sign In</button>      
                                <img  class="col-xs-offset-1" id="loading-anim" src="..\page_files\icons\empty-icon.gif" height="25" alt="">
                            </div>   
                        </div>
                    </div>
                </form>

                <div class="login_forgotpassword">
                    <a href="">Can’t access your account?</a> <br>
                    <a href="../Login/LoginUser.jsp">I'm user</a></div>
            </div>
        </div> 
    </body>
</html>


