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
        <link href="../page_files/css/login.css" rel="stylesheet">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>     
        <script src=""></script>
    </head>
    <body>
        <div class="row">
            <div class="col-md-7 col-sm-7 hidden-xs login_left_admin"> </div>
            <div class="col-sm-12 login_panel">
                <%@include  file="../WEB-INF/jspf/BrandLogo.jspf"%>
                
                <div class="login_header">Sign in with your Admin ID.</div>
                <form action="" method="post" role="form" novalidate> 

                    <div class="form-group" style="display: none;">
                        <label for="UserType"> </label>
                        <input name="UserType" value="admin">    
                    </div>
                    <div class="form-group">
                        <label for="UserName">Admin ID</label>
                        <input autocomplete="off" class="form-control valid" data-val="true" data-val-required="The Admin ID is required." id="username" name="UserName" type="text" value="">
                        <span class="field-validation-valid" data-valmsg-for="UserName" data-valmsg-replace="true"></span>
                    </div>
                    <div class="form-group">
                        <label for="Password">Password</label>
                        <input class="form-control" data-val="true" data-val-required="The Password field is required." id="password" name="Password" type="password">
                        <span class="field-validation-valid" data-valmsg-for="Password" data-valmsg-replace="true"></span>
                    </div>            
                    <div id="captcha" class="form-group" style="display: none;">


                        <div class="row">
                            <div class="col-xs-10">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">

                            </div>
                        </div>

                    </div>
                    <button type="submit" class="btn btn-primary" id="login">Sign In</button>
                </form>

                <div class="login_forgotpassword">
                    <a href="">Forgot my password?</a><br>
                    <a href="./LoginUser.jsp">I'm a User!</a> <div>
                    </div>
                </div> 
            </div>

        </div>
    </body>
</html>