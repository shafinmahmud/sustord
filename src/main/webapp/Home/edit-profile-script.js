/* 
 */
var initialFormData;
var changedFormData;

$(document).ready(function () {

    initialFormData = $('#info-form').serializeArray();

// ---------------       ERROR MESSAGES    -------------------
    var requiredMessage = "One or more field is required!";
    var warningMessage = "No field is changed!";
    var successMessage = "Your profile has been updated!";
    var serverErrorMessage = "Server Error: Server isnt responding :/";
    // ---------------   ICON AND ANIMATION URLs -------------------  
    var alertIconUrl = "../page_files/icons/warning-icon.png";
    var loaderIconUrl = "../page_files/icons/ajax-loader.gif";
    var successIconUrl = "../page_files/icons/success-icon.png";
    var emptyIconUrl = "../page_files/icons/empty-icon.gif";

    $('#father').bind('input', function () {// get the current value of registration No.
        if ($.trim($('#father').val()).length !== 0) {
            $("#fathername-div").addClass("has-success");
            //$('#need-id-error').html('*Registration No required.');
            //$("#messagebox").html('');
        } else {
            $("#fathername-div").removeClass("has-success");
            //$('#need-id-error').html('');
        }
    });


    //following lines of code checks the all input fields and
    // if any of them is 'null' it sets them empty
    $("input:text").val(function (i, currentval) {
        if (currentval === 'null') {
            return '';
        } else {
            return currentval;
        }
    });


    $("#confirm-personal").click(function () {

        changedFormData = $('#info-form').serializeArray();

        if (JSON.stringify(changedFormData) === JSON.stringify(initialFormData)) {
            $("#saving-anim").attr("src", alertIconUrl);
            $("#messagebox").html(warningMessage);
            //highlightChangedField();
        } else {

            //highlightChangedField();

            var warning = "*Required";
            var continueflag = true;
            var presentcountry = $('#present-country').val();
            var permanentcountry = $('#permanent-country').val();
            var sex = $('#sex').val();
            var religion = $('#religion').val();
            var marital = $('#marital-status').val();
            var blood = $('#blood-group').val();
            $("input:text").val(function (i, currentval) {
                if (currentval === '' || currentval === warning) {
                    $(this).removeClass("input-sm");
                    $(this).addClass("requiredwarning");
                    continueflag = false;
                    return warning;
                } else {
                    return currentval;
                }
            });
            if (presentcountry === '0' || presentcountry === '1') {// alert(presentcountry);
                $("#present-country").val("1");
                $("#present-country").removeClass("input-sm");
                $("#present-country").addClass("requiredwarning");
                continueflag = false;
            }

            if (permanentcountry === '0' || permanentcountry === '1') {

                $("#permanent-country").val("1");
                $("#permanent-country").removeClass("input-sm");
                $("#permanent-country").addClass("requiredwarning");
                continueflag = false;
            }

            if (sex === '0' || sex === '1') {

                $("#sex").val("1");
                $("#sex").removeClass("input-sm");
                $("#sex").addClass("requiredwarning");
                continueflag = false;
            }

            if (religion === '0' || religion === '1') {

                $("#religion").val("1");
                $("#religion").removeClass("input-sm");
                $("#religion").addClass("requiredwarning");
                continueflag = false;
            }

            if (marital === '0' || marital === '1') {

                $("#marital-status").val("1");
                $("#marital-status").removeClass("input-sm");
                $("#marital-status").addClass("requiredwarning");
                continueflag = false;
            }

            if (blood === '0' || blood === '1') {

                $("#blood-group").val("1");
                $("#blood-group").removeClass("input-sm");
                $("#blood-group").addClass("requiredwarning");
                continueflag = false;
            }


            if (continueflag) {
                $("#saving-anim").attr("src", loaderIconUrl);
                this.timer = setTimeout(function () {
                    var presentadd = $('#present-street').val() + ',' + $('#present-area').val() + ','
                            + $('#present-thana').val() + ',' + $('#present-district').val() + ','
                            + $('#present-country').val();
                    var permanentadd = $('#permanent-street').val() + ',' + $('#permanent-area').val() + ','
                            + $('#permanent-thana').val() + ',' + $('#permanent-district').val() + ','
                            + $('#permanent-country').val();
                    //alert(permanentadd);
                    $.ajax({
                        url: '../SavePersonalInfoServlet',
                        data: {
                            fname: $('#father').val(),
                            mname: $('#mother').val(),
                            pradd: presentadd,
                            paadd: permanentadd,
                            ph: $('#phone').val(),
                            em: $('#email').val(),
                            dob: $('#dob').val(),
                            sex: $('#sex').val(),
                            reli: $('#religion').val(),
                            mar: $('#marital-status').val(),
                            blood: $('#blood-group').val(),
                            nat: $('#permanent-country').val() + 'i'
                        },
                        type: 'POST',
                        success: function (succ) {
                            if (succ !== 'ERROR') {
                                $("#saving-anim").attr("src", successIconUrl);
                            } else {
                                $("#saving-anim").attr("src", alertIconUrl);
//                            var errormsg = "";
//                            $("#messagebox").html(errormsg);
                            }
                        }
                    });
                }, 200);
            }
            return false;
        }
        return false;
    });
});

function highlightChangedField() {

    //changedFormData;
            //alert(JSON.stringify(initialFormData));
}

