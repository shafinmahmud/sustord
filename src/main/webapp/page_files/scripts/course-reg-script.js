var avaiableCoursesJson;
var pendingCoursesJson;
var droppedCoursesJson;
var registeredCoursesJson;
var choosedCoursesJson;

$(document).ready(function () {
    $('#reg-semester-dropdown').val(1).change();
    ajaxCallForDropDown();

    $('#reg-semester-dropdown').change(function () {
        ajaxCallForDropDown();

    });

//following is the on click event with the .add-course class
//for course add buttons
    $(document).on("click", ".add-course", function () {
            
        var sylIdFromButton = $(this).attr("id").substr(4);

        var syllId, code, title, credit;

        for (var i = 0; i < avaiableCoursesJson.length; i++) {

            syllId = avaiableCoursesJson[i].syllabusId;

            if (syllId == sylIdFromButton) { //alert here... NEVER USE === FOR COMPARISON
                code = avaiableCoursesJson[i].courseCode;
                title = avaiableCoursesJson[i].title;
                credit = avaiableCoursesJson[i].credit;
                
                choosedCoursesJson.push(avaiableCoursesJson[i]);
                break;
            }
        }

        addSelectedCourse(syllId, code, title, credit);
        printSumofCredit();
        $(this).remove();


    });


    $(document).on("click", ".remove-course", function () {

        var codeFromButton = $(this).attr("id").substr(4);
        $('#choosed-tr-' + codeFromButton).remove();

        printSumofCredit();

        for (var i = 0; i < choosedCoursesJson.length; i++) {
            if (choosedCoursesJson[i].syllabusId == codeFromButton) {
                choosedCoursesJson.splice(i, 1);
            }
        }
    });


    $('#confirm-course-registration').click(function () {

        $("#saving-anim").attr("src", "../page_files/icons/ajax-saver.gif");
        //alert(choosedCoursesJson);

        $.ajax({
            url: '../CourseRegServlet',
            data: {
                jsonString: JSON.stringify(choosedCoursesJson),
                semester: $('#reg-semester-dropdown').val()
            },
            //https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/JSON/stringify
            type: 'POST',
            success: function (status) {

                if (status === 'OK') {
                    $("#saving-anim").attr("src", "../page_files/icons/success-icon.png");
                    $("#status-message").html("Congrats! You are Awsome!");
                } else {
                    $("#saving-anim").attr("src", "../page_files/icons/failure-icon.png");
                    $("#status-message").html("OOPS! Duplicate Entry!");
                }

            },
            error: function () {
                $("#saving-anim").attr("src", "");
                alert("ERROR! COURSE REGISTRATION FAILED!");
            }
        });
    });

});

function populateMainTable(coursesJson) {

    $('#course-table-main').append('<tr >'
            + '<td colspan="5" style="font-size: 13px; padding-top: 10px; text-align: center">'
            + '<i>Regular courses</i>'
            + '</td>'
            + '</tr>');

    for (var i = 0; i < coursesJson.length; i++) {

        var syllabusId = coursesJson[i].syllabusId;
        var code = coursesJson[i].courseCode;
        var title = coursesJson[i].title;
        var credit = coursesJson[i].credit;
        $('#course-table-main').append('<tr id="main-tr-' + syllabusId + '"><td>' + code + '</td> <td>'
                + title + '</td> <td>' +
                +credit + '</td> <td>'
                + '<div><section class="border">'
                + '<button class="add-course" id="add-' + syllabusId + '">add</button></section>'
                + '</div></td></tr>');
    }

}

function populatePendingTable(coursesJson) {
    $('#course-table-main').append('<tr >'
            + '<td colspan="5" style="font-size: 13px; padding-top: 10px; text-align: center">'
            + '<i>Your pending courses from previous semester</i>'
            + '</td>'
            + '</tr>');
    // alert(coursesJson[0].courseCode);
    for (var i = 0; i < coursesJson.length; i++) {

        var syllabusId = coursesJson[i].syllabusId;
        var code = coursesJson[i].courseCode;
        var title = coursesJson[i].title;
        var credit = coursesJson[i].credit;
        $('#course-table-main').append('<tr id="main-tr-' + syllabusId + '"><td>' + code + '</td> <td>'
                + title + '</td> <td>' +
                +credit + '</td> <td>'
                + '<div><section class="border">'
                + '<button class="add-course" id="add-' + syllabusId + '">add</button></section>'
                + '</div></td></tr>');
    }
}


function populateDroppedTable(coursesJson) {

    $('#course-table-main').append('<tr >'
            + '<td colspan="5" style="font-size: 13px; padding-top: 10px; text-align: center">'
            + '<i>Your Dropped courses available for this semester</i>'
            + '</td>'
            + '</tr>');
    // alert(coursesJson[0].courseCode);
    for (var i = 0; i < coursesJson.length; i++) {

        var syllabusId = coursesJson[i].syllabusId;
        var code = coursesJson[i].courseCode;
        var title = coursesJson[i].title;
        var credit = coursesJson[i].credit;
        $('#course-table-main').append('<tr id="main-tr-' + syllabusId + '"><td>' + code + '</td> <td>'
                + title + '</td> <td>' +
                +credit + '</td> <td>'
                + '<div><section class="border">'
                + '<button class="add-course" id="add-' + syllabusId + '">add</button></section>'
                + '</div></td></tr>');
    }

}

function addSelectedCourse(syllbusId, code, title, credit) {

    $('#course-table-choosed tr.total-row').before('<tr id="choosed-tr-' + syllbusId + '"><td>' + code + '</td> <td>'
            + title + '</td> <td class="credit">' +
            +credit + '</td> <td>'
            + '<div ><section class="border">'
            + '<button class="remove-course" id="rem-' + syllbusId + '">rem</button></section>'
            + '</div></td></tr>');

    $("#add-" + syllbusId).remove();
    //putCheckMark(code);
}

function printSumofCredit() {
    var sum = 0;
    $(".credit").each(function () {

        var value = $(this).text();
        // add only if the value is number
        if (!isNaN(value) && value.length !== 0) {
            sum += parseFloat(value);
        }
    });

    $("#total_credit").html('<b>' + sum + '</b>');
}

function ajaxCallForDropDown() {
    $("#saving-anim").attr("src", "../page_files/icons/empty-icon.gif");
    $("#status-message").html('');
    $.ajax({
        url: '../SyllabusServlet',
        data: {
            semester: $('#reg-semester-dropdown').val()
        },
        type: 'POST',
        success: function (semesterCourseNtakenCourses) {

            avaiableCoursesJson = [];
            pendingCoursesJson = [];
            droppedCoursesJson = [];
            registeredCoursesJson = [];
            choosedCoursesJson = [];

            var arr = semesterCourseNtakenCourses.split('#');

            if (arr[0] !== 'EMPTY') {
                avaiableCoursesJson = $.parseJSON(arr[0]);
            }
            if (arr[1] !== 'EMPTY') {
                pendingCoursesJson = $.parseJSON(arr[1]);
            }
            if (arr[2] !== 'EMPTY') {
                droppedCoursesJson = $.parseJSON(arr[2]);
            }
            if (arr[3] !== 'EMPTY') {
                registeredCoursesJson = $.parseJSON(arr[3]);
            }

            $('#course-table-main tr:gt(0)').remove();

            $('#course-table-choosed tr').not(function () {
                if ($(this).has('th').length) {
                    return true;
                }
            }).remove();


            var txt = $("#reg-semester-dropdown option:selected").text();
            $('#sem-title').text(txt);

            populateMainTable(avaiableCoursesJson);

            if (pendingCoursesJson.length != 0) {

                populatePendingTable(pendingCoursesJson);

                for (var i = 0; i < pendingCoursesJson.length; i++) {
                    avaiableCoursesJson.push(pendingCoursesJson[i]);
                }
            }

            if (droppedCoursesJson.length != 0) {
                populateDroppedTable(droppedCoursesJson);

                for (var i = 0; i < droppedCoursesJson.length; i++) {
                    avaiableCoursesJson.push(droppedCoursesJson[i]);
                }
            }

            for (var i = 0; i < registeredCoursesJson.length; i++) {

                var sylId = registeredCoursesJson[i].syllabusId;
                var code = registeredCoursesJson[i].courseCode;
                var title = registeredCoursesJson[i].title;
                var credit = registeredCoursesJson[i].credit;
                addSelectedCourse(sylId, code, title, credit);
                choosedCoursesJson.push(registeredCoursesJson[i]);
            }

            printSumofCredit();

        },
        error: function () {
            alert("ERROR");
        }
    });
}



