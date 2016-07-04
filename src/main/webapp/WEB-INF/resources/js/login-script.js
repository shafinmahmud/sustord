
$(document).ready(function () {

    // ---------------       ERROR MESSAGES    -------------------
    var idErrorMessage = "Invalid Registration No.<br>"
            + "Make sure you typed the valid Registration No. "
            + "It usually looks like YYYYDEPXXX. eg. 2011331001 ";

    var passErrorMessage = "Wrong password.<br>"
            + "Have you changed your password? "
            + "If not, your default password is 123456";

    var serverErrorMessage = "Server Error: Server isnt responsing :(";
    var unknownErrorMessage = "Unknwon error occured :(";
    var ajaxErrorMessage = "Ajax operation failed! :(";

    // ---------------   ICON AND ANIMATION URLs -------------------  
    var loaderUrl = "../resources/icons/ajax-loader.gif";
    var successUrl = "../resources/icons/success-icon.png";
    var emptyIconUrl = "../resources/icons/empty-icon.gif";



 // handling event for the empty input in the Registration No field 
    $('#username').bind('input', function () {
        if ($.trim($('#username').val()).length === 0) {
            $("#reg-no").addClass("has-error");
        } else {
            $("#reg-no").removeClass("has-error");
        }
    });

 // handling event for the empty input in the Password field 
    $('#password').bind('input', function () {
        if ($('#password').val() === "") {
            $("#pass").addClass("has-error");
        } else {
            $("#pass").removeClass("has-error");
        }
    });


 // Onclick actions for the Login button
    $('#login').click(function () {
       
       var userNameLength = $.trim($('#username').val()).length; // username is trimed to check white spaces
       var passwordLength = $('#password').val().length; // password should not be trimed

        if ( userNameLength === 0 || passwordLength === 0) {
            if (!(userNameLength > 0)) {
                $("#reg-no").addClass("has-error");
            }
            if (!(passwordLength > 0)) {
                $("#pass").addClass("has-error");
            }

            return false;
        } else {
            $("#loading-anim").attr("src", loaderUrl); // 'loading' animation

            /*$.ajax({
                url: '../LoginServlet',
                data: {
                    ut: $('#usertype').val(),
                    un: $('#username').val(),
                    pw: $('#password').val()
                },
                type: 'POST',
                success: function (messageString) {
                    var messageJson = $.parseJSON(messageString);
                    
                    if (messageJson.requestedIdValid.toString() === "true" && 
                            messageJson.requestedPasswordValid.toString() === "true") {

                        $("#loading-anim").attr("src", successUrl)
                                .fadeTo(9, 1,
                                        function ()
                                        {
                                            document.location = '../Home/HomeUser.jsp';
                                        }
                                );

                    } else if (messageJson.requestedIdValid.toString() !== 'true') {

                        $("#loading-anim").attr("src", emptyIconUrl);
                        $("#messagebox").html(idErrorMessage);

                    } else if (messageJson.requestedPasswordValid.toString() !== 'true') {

                        $("#loading-anim").attr("src", emptyIconUrl);
                        $("#messagebox").html(passErrorMessage);

                    } else if (messageJson.requestedIdValid.toString() === 'null') {
                        alert(messageJson.messageBody);
                        $("#messagebox").html(serverErrorMessage);
                        $("#loading-anim").attr("src", emptyIconUrl);
                    } else {
                        //alert("got it");
                       // alert(messageJson.messageBody);
                        $("#messagebox").html(unknownErrorMessage);
                        $("#loading-anim").attr("src", emptyIconUrl);
                    }
                },
                error: function (messageString) {
                    //alert("oops");
                    $("#messagebox").html(ajaxErrorMessage);   
                    alert(JSON.stringify(messageString));
                    $("#loading-anim").attr("src", emptyIconUrl);
                                    
                }
            });*/

        }
        return false;
    });

});