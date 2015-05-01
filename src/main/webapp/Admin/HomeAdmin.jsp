<%-- 
    Document   : HomeAdmin
    Created on : Apr 3, 2015, 4:48:47 PM
    Author     : SHAFIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Home | Admin</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width">

        <!-- Including Bootstrap-->
        <%@include  file="../WEB-INF/jspf/BootstrapInclude.jspf"%>
        <!-- style sheet for this page-->
        <link href="../page_files/css/style.css" rel="stylesheet">

    </head>
    <body>
        <!-- validating access to this page-->
        <%@include  file="../WEB-INF/jspf/AccessValidation.jspf"%>
        <!-- nav bar portion -->
        <%@include  file="../WEB-INF/jspf/NavBar.jspf"%>
        <div class="portal-body">
            <div class="row">

                <!-- Side bar portion -->
                <%@include  file="../WEB-INF/jspf/SideBarAdmin.jspf"%>
                <div id="main-content" class="col-md-12 column800">
                    <div class="row">
                        <div class="col-sm-12 col-xs-12 col-md-12 column1200">
                            <fieldset>
                                <legend>Data input</legend>
                                <h4>Semester</h4>
                                <h4>year</h4>
                                <h4>course code</h4>
                            </fieldset>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <!-- footer portion -->
        <%@include  file="../WEB-INF/jspf/Footer.jspf"%>

    </body>
</html>
