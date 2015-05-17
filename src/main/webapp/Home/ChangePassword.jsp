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

                            <form  class="form-horizontal" method="post" role="form" >
                                <div class="form-group">
                                    <label class="col-lg-3" style="text-align: right" >Current Password</label>
                                    <div id="old-pass-div" class="col-lg-5">
                                        <input id="old-pass" class="form-control has-error" type="password" for="OldPassword">
                                    </div>
                                    <div class="col-lg-4">
                                        <p id="warn-old-pass"></p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-3" style="text-align: right">New Password</label>                        
                                    <div id="new-pass-div" class="col-lg-5">
                                        <input id="new-pass" class="form-control" type="password" for="newPassword">
                                    </div>
                                    <div class="col-lg-4">
                                        <p id="warn-new-pass"></p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-3" style="text-align: right" for="ConfirmPassword">Confirm New Password</label>
                                    <div id="new-pass-retype-div" class="col-lg-5">
                                        <input id="new-pass-retype" class="form-control" name="ConfirmPassword" type="password">
                                    </div>
                                    <div class="col-lg-4">
                                        <p id="warn-new-pass-retype"></p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-offset-3 col-lg-1 col-xs-1">                                     
                                        <button id="submit-change-button" type="button" class="btn btn-danger">Submit</button>                                                                      
                                    </div>
                                    <div class="col-lg-1 col-xs-offset-1 col-xs-1">
                                        <img id="status-icon" src="..\page_files\icons\empty-icon.gif" height="25" alt="">                                  
                                    <div class="col-lg-8 col-md-8">                                   
                                        <p id="status-text" style="font-size: 13.5px; padding-top: 8px"></p>
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
