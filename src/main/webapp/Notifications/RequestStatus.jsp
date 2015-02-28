<%-- 
    Document   : RequestStatus
    Created on : Feb 14, 2015, 12:05:24 PM
    Author     : SHAFIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width">
        <title>Request Status</title>
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

                    <div class="margin5">
                        <%@include  file="../WEB-INF/jspf/ComingSoon.jspf"%>
                    </div>
                </div>
            </div>
        </div>
        <%@include  file="../WEB-INF/jspf/Footer.jspf"%>
    </body>
</html>
