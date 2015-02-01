
$(document).ready(function () {
    $('#username').bind('input', function () {// get the current value of the input field.
        if ($('#username').val() === "") {
            $('#need-id-error').html('*Registration No required.');
            $("#messagebox").html('');
        } else {
            $('#need-id-error').html('');
        }
    });

    $('#password').bind('input', function () {// get the current value of the input field.
        if ($('#password').val() === "") {
            $('#need-pass-error').html('*Password required.');
            $("#messagebox").html('');
        } else {
            $('#need-pass-error').html('');
        }
    });



    $('#login').click(function () {

        if ($('#username').val() === "" || $('#password').val() === "") {

            if ($('#username').val() === "")
                $('#need-id-error').html('*Registration No required.');
            if ($('#password').val() === "")
                $('#need-pass-error').html('*Password required.');

            return false;
        } else {
            $('#need-id-error').html('');
            $('#need-pass-error').html('');

          $("#loading-anim").attr("src", "../page_files/icons/ajax-loader.gif");

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
                        
                        $("#loading-anim").attr("src", "../page_files/icons/success-icon.png")
                                .fadeTo(9, 1,
                                        function ()
                                        {
                                            document.location = '../Home/HomeUser.jsp';
                                        }
                                );

                    } else if(validationMsg == 'denied'){
                        
                        var errormsg = "Invalid username or password.<br>"
                                + "Make sure you typed the correct Registration No. "
                                + "It usually looks like 2011331XXX. And make sure you remember your password.";
                        $("#loading-anim").attr("src", "")
                        $("#messagebox").html(errormsg);
                    }else if(validationMsg == 'noresponse'){
                        alert("no way :/");
                    }else{
                        alert(validationMsg);
                    }
                },
                error: function () {
                alert("UNKNOWN ERROR :(");
            }
            });

        }
        return false;
    });

});