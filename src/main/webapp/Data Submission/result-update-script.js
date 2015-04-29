var changedGradeJson;
var registeredCoursesJson = [];

$(document).ready(function () {
    ajaxCallForCurrentSemester();

    $('#result-semester-dropdown').change(function () {

        ajaxCallForDropDown();
    });

    $(document).on("change", ".dropdown-grade", (function () {

        var courseRegIdFromDropDown = $(this).attr("id").substr(9);
        var changedValue = $(this).val();
        var doChange = true;
//        alert($('#dropdown-' + courseRegIdFromDropDown).val().length);
        //checking if the grade is changed before this change
        for (var i = 0; i < changedGradeJson.length; i++) {
            if (courseRegIdFromDropDown == changedGradeJson[i].courseRegistrationId) {

                var oldValue = "";

                //traversing registeresCoursesJson for old value
                for (var j = 0; j < registeredCoursesJson.length; j++) {
                    if (courseRegIdFromDropDown == registeredCoursesJson[j].courseRegistrationId) {
                        oldValue = registeredCoursesJson[j].grade;
                    }
                }
                //checking if the current change is the old value;
                if (changedValue == oldValue) {
                    changedGradeJson.splice(i, 1);
                    doChange = false;
                } else {
                    changedGradeJson[i].grade = changedValue;
                }

            }
        }
        //if it is the first change then push that to changedGradeJson
        for (var i = 0; i < registeredCoursesJson.length; i++) {
            if (courseRegIdFromDropDown == registeredCoursesJson[i].courseRegistrationId
                    && doChange) {
                var temp = $.extend({}, registeredCoursesJson[i]); //http://api.jquery.com/jquery.extend/
                temp.grade = changedValue;
                changedGradeJson.push(temp);
            }
        }

//        var s = "start->";
//        for (var k = 0; k < changedGradeJson.length; k++) {
//            s = s.concat(changedGradeJson[k].courseCode + ":" + changedGradeJson[k].grade + " ");
//        }
//        alert(s);
    }));

    $('#confirm-result-update').click(function () {
        $("#saving-anim").attr("src", "../page_files/icons/ajax-saver.gif");
        $.ajax({
            url: '../SaveResultUpdateServlet',
            data: {
                jsonString: JSON.stringify(changedGradeJson),
                semester: $('#result-semester-dropdown').val()
            },
            type: 'POST',
            success: function (status) {
                if (status == 'OK') {
                    ajaxCallForDropDown();
                    $("#saving-anim").attr("src", "../page_files/icons/success-icon.png");
                    $("#status-message").html("Congrats! Grades Updated!");
                } else {
                    $("#saving-anim").attr("src", "../page_files/icons/failure-icon.png");
                    $("#status-message").html("OOPS! You tried something BAD!");
                }
            },
            error: function () {
                $("#saving-anim").attr("src", "");
                $("#status-message").html("ERROR OCCURED");

            }
        });
    });
});





function printSumofCredit() {
    var sum = 0;
    $(".credit").each(function () {

        var value = $(this).text();
        // add only if the value is number
        if (!isNaN(value) && value.length !== 0) {
            sum += parseFloat(value);
        }
    });

    $("#taken_credit").html('<b>' + sum + '</b>');
}

function ajaxCallForCurrentSemester() {

    $.ajax({
        url: '../GetCurrentSemester',
        type: 'POST',
        success: function (currentSemester) {
            $('#result-semester-dropdown').val(currentSemester).change();
        },
        error: function () {
            alert("ERRORX");
        }
    });
}

function ajaxCallForDropDown() {
    $.ajax({
        url: '../ResultUpdateServlet',
        data: {
            semester: $('#result-semester-dropdown').val()
        },
        type: 'POST',
        success: function (takenCourses) {
            $('#table-registered-courses tr').not(function () {
                if ($(this).has('th').length) {
                    return true;
                }
            }).remove();

            var txt = $("#result-semester-dropdown option:selected").text();
            $('#sem-title').text(txt);

            if (takenCourses == 'EMPTY') {

            } else {
                registeredCoursesJson = $.parseJSON(takenCourses);
                changedGradeJson = [];
                populateMainTable(registeredCoursesJson);
                printSumofCredit();
            }

        },
        error: function () {
            alert("ERROR");
        }
    });
}


function populateMainTable(coursesJson) {

    for (var i = 0; i < coursesJson.length; i++) {

        var courseRegId = coursesJson[i].courseRegistrationId;
        var code = coursesJson[i].courseCode;
        var title = coursesJson[i].title;
        var credit = coursesJson[i].credit;
        var grade = coursesJson[i].grade;
        if (grade == 'N/A') {
            grade = '<p style="color:#808080">N/A</p>';
        } else {
            grade = '<p><b>' + grade + '</b></p>';
        }

        $('#table-registered-courses tr.total-row').before('<tr id="main-tr-' + courseRegId + '"><td>' + code + '</td> <td>'
                + title + '</td> <td class="credit" style="text-align: center">' +
                +credit + '</td> <td style="text-align: center">'
                + '<div>' + grade
                + '</div></td> <td>'
                + '<div>'
                + '<select class="dropdown-grade" id="dropdown-' + courseRegId + '" style="width:70px; height: 20px; font-size: 12px">'
                + '<option selected style="display:none">change</option>'
                + '<option value="A+">A+</option>'
                + '<option value="A">A</option>'
                + '<option value="A-">A-</option>'
                + '<option value="B+">B+</option>'
                + '<option value="B">B</option>'
                + '<option value="B-">B-</option>'
                + '<option value="C+">C+</option>'
                + '<option value="C">C</option>'
                + '<option value="C-">C-</option>'
                + '<option value="F">F</option>'
                + '<option value="N/A">N/A</option>'
                + '</select>'
                + '</div>'
                + '</td></tr>');

        // $('dropdown-88').val('A');
    }

}