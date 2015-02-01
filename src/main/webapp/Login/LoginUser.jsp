<%--
    Document   : LoginUser
    Created on : Dec 25, 2014, 10:21:59 AM
    Author     : SHAFIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width">       
        <title>SUST-ORD</title>
        <link href="../page_files/css/loginStyle.css" rel="stylesheet">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>     
        <script src="../page_files/scripts/login-script.js"></script>
    </head>
    <body>
        <%
            try {
                if (session.getAttribute("loginStatus").equals("ok")) {
                    response.sendRedirect("http://sustord-shafinmahmud.rhcloud.com/Home/HomeUser.jsp");
                }
            } catch (Exception e) {

            }
        %>
        <div class="row">
            <div class="col-md-7 col-sm-7 hidden-xs login_left">

            </div>
            <div class="col-sm-12 login_panel">
                <%@include  file="../WEB-INF/jspf/BrandLogo.jspf"%>
                <div id="before-form" class="login_header">Sign in with your Registration Number.</div>
                <div>                  
                    <p id="messagebox" class="requiredwarning small"></p>
                </div>

                <form id = "loginform" action="" method="post" role="form"> 
                    <div class="form-group" style="display: none;">
                        <input id="usertype" name="UserType" value="student">    
                    </div>
                    <div id="reg-no" class="form-group">
                        <label for="UserName">Registration No</label>
                        <input id="username" autocomplete="off" class="form-control valid"  name="UserName" type="text" value="">
                        <p id="need-id-error" class="requiredwarning small"></p>
                    </div>
                    <div id="pass" class="form-group">
                        <label for="Password">Password</label>
                        <input id="password"  class="form-control" name="Password" type="password">
                        <p id="need-pass-error" class="requiredwarning small"></p>
                    </div>            

                    <div>
                        <table class="table-condensed">
                            <tr>
                                <td>
                                    <button type="submit" class="btn btn-primary" id="login" >Sign In</button>
                                </td>
                                <td>

                                </td>
                                <td>
                                    <img  id="loading-anim" src="" height="22" alt="">
                                </td>
                            </tr>
                        </table>
                    </div>

                </form>

                <div class="login_forgotpassword">
                    <a href="">Can’t access your account?</a> <br>
                    <a href="./LoginAdmin.jsp">I'm Admin</a></div>
            </div>
        </div> 
    </body>
</html>


