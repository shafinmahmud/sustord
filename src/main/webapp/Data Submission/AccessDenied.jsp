<%-- 
    Document   : AccessDenied
    Created on : May 16, 2015, 2:19:00 PM
    Author     : SHAFIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Access Denied</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width">

        <%@include  file="../WEB-INF/jspf/BootstrapInclude.jspf"%>
        <link href="../page_files/css/style.css" rel="stylesheet">
        
    </head>
    <body>
        <%@include  file="../WEB-INF/jspf/AccessValidation.jspf"%>
        <%@include  file="../WEB-INF/jspf/NavBar.jspf"%>
        <div class="portal-body">
            <div class="row">
                <%@include  file="../WEB-INF/jspf/SideBar.jspf"%>
                <div id="main-content" class="col-md-10 column800" style="height: 400px">
                    
                    <div class="col-md-offset-4 col-xs-offset-4">                  
                            <img src="../page_files/icons/access-denied.png" style="height: 200px;
                                 padding-top: 50px; padding-bottom: 30px;">              
                    </div>
                    <div class="col-md-10 col-xs-10">
                        <p style="font-size: 28px; text-align: center">Access Denied!</p>
                    </div>
                           <div class="col-md-10 col-xs-10">
                        <p style="font-size: 16px; text-align: center">This page is currently 
                        revoked from user for data stability. If you feel any change or update please 
                        contact you Admin!</p>
                    </div> 

                </div>
            </div>
        </div>
                <%@include  file="../WEB-INF/jspf/Footer.jspf"%>
    </body>
</html>
