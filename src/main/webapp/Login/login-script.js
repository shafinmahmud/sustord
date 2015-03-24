
$(document).ready(function () {

    // ---------------       ERROR MESSAGES    -------------------
    var regErrorMessage = "Invalid Registration No.<br>"
            + "Make sure you typed the valid Registration No. "
            + "It usually looks like YYYYDEPXXX. eg. 2011331001 ";

    var passErrorMessage = "Wrong password.<br>"
            + "Have you changed your password? "
            + "If not, your default password is 123456";

    var serverErrorMessage = "Server Error: Server isnt responding :(";
    var ajaxErrorMessage = "hibernate.cfg.xml configuration error! :(";

    // ---------------   ICON AND ANIMATION URLs -------------------  
    var loaderUrl = "../page_files/icons/ajax-loader.gif";
    var successUrl = "../page_files/icons/success-icon.png";
    var emptyIconUrl = "../page_files/icons/empty-icon.gif";



 // handling event for the empty input in the Registration No field 
    $('#username').bind('input', function () {// get the current value of registration No.
        if ($('#username').val() === "") {
            $("#reg-no").addClass("has-error");
            //$('#need-id-error').html('*Registration No required.');
            //$("#messagebox").html('');
        } else {
            $("#reg-no").removeClass("has-error");
            //$('#need-id-error').html('');
        }
    });

 // handling event for the empty input in the Password field 
    $('#password').bind('input', function () {// get the current value of the input field.
        if ($('#password').val() === "") {
            $("#pass").addClass("has-error");
            //$('#need-pass-error').html('*Password required.');
            //$("#messagebox").html('');
        } else {
            $("#pass").removeClass("has-error");
            //$('#need-pass-error').html('');
        }
    });


 // Onclick actions for the Login button
    $('#login').click(function () {

        if ($('#username').val() === "" || $('#password').val() === "") {

            if ($('#username').val() === "") {
                $("#reg-no").addClass("has-error");
            }
            if ($('#password').val() === "") {
                $("#pass").addClass("has-error");
                // $('#need-pass-error').html('*Password required.');
            }

            return false;
        } else {
            //$('#need-id-error').html('');
            //$('#need-pass-error').html('');

            $("#loading-anim").attr("src", loaderUrl); // 'loading' animation

            $.ajax({
                url: '../LoginServlet',
                data: {
                    ut: $('#usertype').val(),
                    un: $('#username').val(),
                    pw: $('#password').val()
                },
                type: 'POST',
                success: function (validationMsg) {
                    if (validationMsg == 'verified') {

                        $("#loading-anim").attr("src", successUrl)
                                .fadeTo(9, 1,
                                        function ()
                                        {
                                            document.location = '../Home/HomeUser.jsp';
                                        }
                                );

                    } else if (validationMsg == 'regError') {

                        $("#loading-anim").attr("src", emptyIconUrl);
                        $("#messagebox").html(regErrorMessage);

                    } else if (validationMsg == 'passError') {

                        $("#loading-anim").attr("src", emptyIconUrl);
                        $("#messagebox").html(passErrorMessage);

                    } else if (validationMsg == 'noresponse') {
                        alert(serverErrorMessage);
                        $("#loading-anim").attr("src", emptyIconUrl);
                    } else {
                        alert(validationMsg);
                        $("#loading-anim").attr("src", emptyIconUrl);
                    }
                },
                error: function () {
                    alert(ajaxErrorMessage);
                    $("#loading-anim").attr("src", emptyIconUrl);
                }
            });

        }
        return false;
    });

});