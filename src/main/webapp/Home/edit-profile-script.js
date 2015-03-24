/* 
 */

$(document).ready(function () {

    alert("entered1");
    $('#father').focus(function () {
        if ($('#father').val() === "*Required") {

            $("#father").val("");
            $("#father").removeClass("requiredwarning");
            $("#father").addClass("input-sm");
        } else {
            //$('#need-id-error').html('');
        }
    });

    $('#mother').focus(function () {
        if ($('#mother').val() === "*Required") {

            $("#mother").val("");
            $("#mother").removeClass("requiredwarning");
            $("#mother").addClass("input-sm");
        } else {
            //$('#need-id-error').html('');
        }
    });

    $('#present-street').focus(function () {
        if ($('#present-street').val() === "*Required") {

            $("#present-street").val("");
            $("#present-street").removeClass("requiredwarning");
            $("#present-street").addClass("input-sm");
        } else {
            //$('#need-id-error').html('');
        }
    });

    $('#present-area').focus(function () {
        if ($('#present-area').val() === "*Required") {

            $("#present-area").val("");
            $("#present-area").removeClass("requiredwarning");
            $("#present-area").addClass("input-sm");
        } else {
            //$('#need-id-error').html('');
        }
    });

    $('#present-thana').focus(function () {
        if ($('#present-thana').val() === "*Required") {

            $("#present-thana").val("");
            $("#present-thana").removeClass("requiredwarning");
            $("#present-thana").addClass("input-sm");
        } else {
            //$('#need-id-error').html('');
        }
    });

    $('#present-district').focus(function () {
        if ($('#present-district').val() === "*Required") {

            $("#present-district").val("");
            $("#present-district").removeClass("requiredwarning");
            $("#present-district").addClass("input-sm");
        } else {
            //$('#need-id-error').html('');
        }
    });

    $('#present-country').focus(function () {
        if ($('#present-country').val() === "1") {

            $("#present-country").val("0");
            $("#present-country").removeClass("requiredwarning");
            $("#present-country").addClass("input-sm");
        } else {
            //$('#need-id-error').html('');
        }
    });


    $('#permanent-street').focus(function () {
        if ($('#permanent-street').val() === "*Required") {

            $("#permanent-street").val("");
            $("#permanent-street").removeClass("requiredwarning");
            $("#permanent-street").addClass("input-sm");
        } else {
            //$('#need-id-error').html('');
        }
    });

    $('#permanent-area').focus(function () {
        if ($('#permanent-area').val() === "*Required") {

            $("#permanent-area").val("");
            $("#permanent-area").removeClass("requiredwarning");
            $("#permanent-area").addClass("input-sm");
        } else {
            //$('#need-id-error').html('');
        }
    });

    $('#permanent-thana').focus(function () {
        if ($('#permanent-thana').val() === "*Required") {

            $("#permanent-thana").val("");
            $("#permanent-thana").removeClass("requiredwarning");
            $("#permanent-thana").addClass("input-sm");
        } else {
            //$('#need-id-error').html('');
        }
    });

    $('#permanent-district').focus(function () {
        if ($('#permanent-district').val() === "*Required") {

            $("#permanent-district").val("");
            $("#permanent-district").removeClass("requiredwarning");
            $("#permanent-district").addClass("input-sm");
        } else {
            //$('#need-id-error').html('');
        }
    });


    $('#permanent-country').focus(function () {
        if ($('#permanent-country').val() === "1") {

            $("#permanent-country").val("0");
            $("#permanent-country").removeClass("requiredwarning");
            $("#permanent-country").addClass("input-sm");
        } else {
            //$('#need-id-error').html('');
        }
    });

    $('#phone').focus(function () {
        if ($('#phone').val() === "*Required") {

            $("#phone").val("");
            $("#phone").removeClass("requiredwarning");
            $("#phone").addClass("input-sm");
        } else {
            //$('#need-id-error').html('');
        }
    });

    $('#email').focus(function () {
        if ($('#email').val() === "*Required") {

            $("#email").val("");
            $("#email").removeClass("requiredwarning");
            $("#email").addClass("input-sm");
        } else {
            //$('#need-id-error').html('');
        }
    });

    $('#dob').focus(function () {
        if ($('#dob').val() === "*Required") {

            $("#dob").val("");
            $("#dob").removeClass("requiredwarning");
            $("#dob").addClass("input-sm");
        } else {
            //$('#need-id-error').html('');
        }
    });

    $("#dob").datepicker();

    $('#sex').focus(function () {
        if ($('#sex').val() === "1") {

            $("#sex").val("0");
            $("#sex").removeClass("requiredwarning");
            $("#sex").addClass("input-sm");
        } else {
            //$('#need-id-error').html('');
        }
    });

    $('#religion').focus(function () {
        if ($('#religion').val() === "1") {

            $("#religion").val("0");
            $("#religion").removeClass("requiredwarning");
            $("#religion").addClass("input-sm");
        } else {
            //$('#need-id-error').html('');
        }
    });

    $('#marital-status').focus(function () {
        if ($('#marital-status').val() === "1") {

            $("#marital-status").val("0");
            $("#marital-status").removeClass("requiredwarning");
            $("#marital-status").addClass("input-sm");
        } else {
            //$('#need-id-error').html('');
        }
    });

    $('#blood-group').focus(function () {
        if ($('#blood-group').val() === "1") {

            $("#blood-group").val("0");
            $("#blood-group").removeClass("requiredwarning");
            $("#blood-group").addClass("input-sm");
        } else {
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
        alert("entered");

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

        if (presentcountry === '0' || presentcountry === '1') {
            // alert(presentcountry);
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
            $("#saving-anim").attr("src", "../page_files/icons/ajax-saver.gif");
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
                            $("#saving-anim").attr("src", "../page_files/icons/success-icon.png");

                        } else {
                            alert("error");
//                            var errormsg = "";
//                            $("#messagebox").html(errormsg);
                        }
                    }
                });
            }, 200);

        }
        return false;

    });

});


