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
                <div id="main-content" class="col-md-10 column800">
                    
                    
                </div>
            </div>
         </div>
    </body>
</html>
