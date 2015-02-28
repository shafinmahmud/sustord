<%-- 
    Document   : ChangePassword
    Created on : Jan 27, 2015, 6:18:40 PM
    Author     : SHAFIN
--%>

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
            <%@include  file="../WEB-INF/jspf/BootstrapInclude.jspf"%>
            <link href="../page_files/css/style.css" rel="stylesheet">
        </head>
        <body>
            <%@include  file="../WEB-INF/jspf/AccessValidation.jspf"%>
            <%@include  file="../WEB-INF/jspf/NavBar.jspf"%>

            <div class="portal-body">
            <div class="row">
                <%@include  file="../WEB-INF/jspf/SideBar.jspf"%>
                <div id="main-content" class="col-md-10 column800">
                    <div class="col-lg-8">
                        <fieldset>
                            <legend>Change Password</legend>

                            <form action="" class="form-horizontal" method="post" role="form" novalidate="novalidate">
                                <div class="form-group">
                                    <label class="col-lg-4" for="OldPassword">Current Password</label>
                                    <div class="col-lg-8">
                                        <input class="form-control" data-val="true" data-val-required="The Current Password field is required." id="OldPassword" name="OldPassword" type="password">
                                        <span class="field-validation-valid" data-valmsg-for="OldPassword" data-valmsg-replace="true"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-4" for="NewPassword">New Password</label>                        
                                    <div class="col-lg-8">
                                        <input class="form-control" data-val="true" data-val-length="The New Password must be at least 8 characters long." data-val-length-max="100" data-val-length-min="8" data-val-regex="Please check password policy" data-val-regex-pattern="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&amp;*-]).{8,}$" data-val-required="The New Password field is required." id="NewPassword" name="NewPassword" type="password">

                                        <span class="field-validation-valid" data-valmsg-for="NewPassword" data-valmsg-replace="true"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-4" for="ConfirmPassword">Confirm New Password</label>
                                    <div class="col-lg-8">
                                        <input class="form-control" data-val="true" data-val-equalto="The new password and confirmation password do not match." data-val-equalto-other="*.NewPassword" id="ConfirmPassword" name="ConfirmPassword" type="password">
                                        <span class="field-validation-valid" data-valmsg-for="ConfirmPassword" data-valmsg-replace="true"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-offset-4 col-lg-8">
                                        <input type="submit" value="Change password" class="btn btn-danger">
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
