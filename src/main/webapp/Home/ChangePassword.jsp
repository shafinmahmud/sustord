<%-- 
    Document   : ChangePassword
    Created on : Jan 27, 2015, 6:18:40 PM
    Author     : SHAFIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Password Change</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width">

        <%@include  file="../WEB-INF/jspf/BootstrapInclude.jspf"%>
        <link href="../page_files/css/style.css" rel="stylesheet">
        <!-- script for this page-->
        <script src="change-password-script.js"></script>
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
                            <legend>Change Password</legend>

                            <form action="" class="form-horizontal" method="post" role="form" >
                                <div class="form-group">
                                    <label class="col-lg-3" style="text-align: right" for="OldPassword" >Current Password</label>
                                    <div id="old-pass-div" class="col-lg-5">
                                        <input id="old-pass" class="form-control" data-val="true" data-val-required="The Current Password field is required." id="OldPassword" name="OldPassword" type="password">
                                    </div>
                                    <div class="col-lg-4">
                                        <p id="warn-old-pass"></p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-3" style="text-align: right" for="NewPassword">New Password</label>                        
                                    <div id="new-pass-div" class="col-lg-5">
                                        <input class="form-control" data-val="true" data-val-length="The New Password must be at least 8 characters long." data-val-length-max="100" data-val-length-min="8" data-val-regex="Please check password policy" data-val-regex-pattern="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&amp;*-]).{8,}$" data-val-required="The New Password field is required." id="NewPassword" name="NewPassword" type="password">
                                    </div>
                                    <div class="col-lg-4">
                                        <p id="warn-new-pass"></p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-3" style="text-align: right" for="ConfirmPassword">Confirm New Password</label>
                                    <div id="new-pass-retype-div" class="col-lg-5">
                                        <input class="form-control" data-val="true" data-val-equalto="The new password and confirmation password do not match." data-val-equalto-other="*.NewPassword" id="ConfirmPassword" name="ConfirmPassword" type="password">
                                    </div>
                                    <div class="col-lg-4">
                                        <p id="warn-new-pass-retype"></p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-offset-4 col-lg-8">
                                        <input id="submit-change-button" type="submit" value="Change password" class="btn btn-danger">
                                    </div>
                                </div>
                            </form> 
                        </fieldset>

                    </div>

                </div>
            </div>
        </div>
        <%@include  file="../WEB-INF/jspf/Footer.jspf"%>
    </body>
</html>
