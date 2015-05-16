/* 
 */

// ---------------       ERROR MESSAGES    -------------------
    var oldPassRequired = "The Current Password is required.";
    var newPassLengthError = "The New Password must be at least 6 characters long.";
    var newPassMatchError = "The new password and confirmation password do not match.";
// ---------------   ICON AND ANIMATION URLs -----------------
    var alertUrl = "";
    var loaderUrl = "../page_files/icons/ajax-loader.gif";
    var successUrl = "../page_files/icons/success-icon.png";
    var emptyIconUrl = "../page_files/icons/empty-icon.gif";
    

$(document).ready(function () {

    $("#submit-change-button").click(function () {

        $('#warn-old-pass').html('');
        $('#warn-new-pass').html('');
        $('#warn-new-pass-retype').html('');

        validationFLag = false;

        //check the old password field
        oldPassword = $('#old-pass').val();
        newPassword = $('#new-pass').val();
        newPasswordRetype = $('#new-pass-retype').val();
 
        if (oldPassword.length === 0) {
            $('#warn-old-pass').html(oldPassRequired);
            // $("#old-pass").addClass("has-error");   
        } else {
            if (newPassword.length < 6) {
                $('#warn-new-pass').html(newPassLengthError);
                // $("#old-pass").addClass("has-error");   
            } else {
                if (newPasswordRetype !== newPassword) {
                    $('#warn-new-pass-retype').html(newPassMatchError);
                    // $("#old-pass").addClass("has-error");   
                } else {
                    validationFLag = true;
                }
            }
        }

        if (validationFLag) {
            ajaxCall(oldPassword, newPassword);
        }

    });
});


function ajaxCall(oldPassword, newPassword) {
    //$('#need-id-error').html('');
    //$('#need-pass-error').html('');

    $("#status-icon").attr("src", loaderUrl); // 'loading' animation

    $.ajax({
        url: '../ChangePasswordServlet',
        data: {
            oldPass: oldPassword,
            newPass: newPassword
        },
        type: 'POST',
        success: function (messageString) {
            var messageJson = $.parseJSON(messageString);
            $("#status-icon").attr("src", successUrl);       
        },
        error: function (messageString) {
            alert("oops");
        }
    });
}




