/* 
 */

$(document).ready(function () {

// ---------------       ERROR MESSAGES    -------------------
    var oldPassRequired = "The Current Password field is required.";
    var newPassLengthError = "The New Password must be at least 8 characters long.";
    var newPassMatchError = "The new password and confirmation password do not match.";
// ---------------   ICON AND ANIMATION URLs -----------------
    var alertUrl = "";
    var loaderUrl = "../page_files/icons/ajax-loader.gif";
    var successUrl = "../page_files/icons/success-icon.png";
    var emptyIconUrl = "../page_files/icons/empty-icon.gif";

    
    $("#submit-change-button").click(function () {

        validationFLag = false;

        //check the old password field
        oldPassword = $('#old-pass').val();
        if(oldPassword.length === 0){
            $("#old-pass").addClass("has-error");
            $('#need-pass-error').html('*Password required.');
        }

        //check the new password field or retype password field is empty or not

        //match the new password field and the retyped password field for similarity

        if (validationFLag) {

        }

    });
});

function highlightChangedField() {

    //changedFormData;
    //alert(JSON.stringify(initialFormData));
}



