var allCoursesJson;
var regularCoursesJson;
var pendingCoursesJson;
var droppedCoursesJson;
var registeredCoursesJson;
var choosedCoursesJson;

$(document).ready(function () {
    ajaxCallForCurrentSemester();

    $('#reg-semester-dropdown').change(function () {
        ajaxCallForDropDown();
    });

//following is the on click event with the .add-course class
//for course add buttons
    $(document).on("click", ".add-course", function () {

        var syllIdFromButton = $(this).attr("id").substr(4);

        toggleCourseButton(syllIdFromButton, 0);
        setStatus(syllIdFromButton, 0);

        for (var i = 0; i < allCoursesJson.length; i++) {

            syllId = allCoursesJson[i].syllabusId;

            if (syllId == syllIdFromButton) { //alert here... NEVER USE === FOR COMPARISON               
                choosedCoursesJson.push(allCoursesJson[i]);
                break;
            }
        }
        printSumofCredit();

    });


    $(document).on("click", ".remove-course", function () {

        var syllIdFromButton = $(this).attr("id").substr(4);

        toggleCourseButton(syllIdFromButton, 1);
        setStatus(syllIdFromButton, 1);

        for (var i = 0; i < choosedCoursesJson.length; i++) {
            if (choosedCoursesJson[i].syllabusId == syllIdFromButton) {
                choosedCoursesJson.splice(i, 1);
            }
        }
        printSumofCredit();
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
                    $("#status-message").html("Done!!");
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

function ajaxCallForCurrentSemester() {

    $.ajax({
        url: '../GetCurrentSemester',
        type: 'POST',
        success: function (currentSemester) {
            $('#reg-semester-dropdown').val(currentSemester).change();
        },
        error: function () {
            alert("ERRORX");
        }
    });
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

            allCoursesJson = [];
            regularCoursesJson = [];
            pendingCoursesJson = [];
            droppedCoursesJson = [];
            registeredCoursesJson = [];
            choosedCoursesJson = [];

            var arr = semesterCourseNtakenCourses.split('#');

            if (arr[0] !== 'EMPTY') {
                regularCoursesJson = $.parseJSON(arr[0]);
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


            $('#course-table-main tr').not(function () {
                if ($(this).has('th').length) {
                    return true;
                }
            }).remove();


            //we'll change it later
            var txt = $("#reg-semester-dropdown option:selected").text();
            $('#sem-title').text(txt);


            if (regularCoursesJson.length != 0) {

                populateRows(regularCoursesJson);//poulating main table with regular courses
                //pushing all regularCourses to allCourses
                for (var i = 0; i < regularCoursesJson.length; i++) {
                    allCoursesJson.push(regularCoursesJson[i]);
                }
            } else {
                //if there is no regularCourse what to do ? do it here
            }

            if (pendingCoursesJson.length != 0) {

                $('#course-table-main').append('<tr >'
                        + '<td colspan="5" style="font-size: 13px; padding-top: 10px; text-align: center">'
                        + '<i>Your pending courses from previous semester</i>'
                        + '</td>'
                        + '</tr>');

                populateRows(pendingCoursesJson);
                for (var i = 0; i < pendingCoursesJson.length; i++) {
                    allCoursesJson.push(pendingCoursesJson[i]);
                }
            } else {
                //if there is no pendingCourse what to do ? do it here
            }

            if (droppedCoursesJson.length != 0) {

                $('#course-table-main').append('<tr >'
                        + '<td colspan="5" style="font-size: 13px; padding-top: 10px; text-align: center">'
                        + '<i>Your Dropped courses available for this semester</i>'
                        + '</td>'
                        + '</tr>');

                populateRows(droppedCoursesJson);
                for (var i = 0; i < droppedCoursesJson.length; i++) {
                    allCoursesJson.push(droppedCoursesJson[i]);
                }
            } else {
                //if there is no pendingCourse what to do ? do it here
            }


            //itereting the registered courses here and deciding about the button and status
            if (registeredCoursesJson.length != 0) {

                for (var i = 0; i < allCoursesJson.length; i++) {

                    var found = false;
                    var syllabusId = allCoursesJson[i].syllabusId;

                    for (var j = 0; j < registeredCoursesJson.length; j++) {

                        if (syllabusId == registeredCoursesJson[j].syllabusId) {
                            //alert(syllabusId);
                            found = true;
                            choosedCoursesJson.push(registeredCoursesJson[j]);
                        }
                    }

                    if (found) {
                        toggleCourseButton(syllabusId, 0);
                        setStatus(syllabusId, 0);
                    }
                }
            }

            printSumofCredit();

        },
        error: function () {
            alert("ERROR");
        }
    });
}



//new function for new look
function populateRows(coursesJson) {
    for (var i = 0; i < coursesJson.length; i++) {

        var syllabusId = coursesJson[i].syllabusId;
        var code = coursesJson[i].courseCode;
        var title = coursesJson[i].title;
        var credit = coursesJson[i].credit;

        $('#course-table-main').append('<tr id="main-tr-' + syllabusId + '"><td>' + code + '</td> <td>'
                + title + '</td> <td style="text-align: center">' +
                +credit + '</td> <td style="text-align: center"><div id="course-status-div-' + syllabusId + '"></div></td>'
                + '<td style="text-align: center">'
                + '<div id="main-button-div-' + syllabusId + '">'
                + '</div></td></tr>');

        //default status and button
        toggleCourseButton(syllabusId, 1);
        setStatus(syllabusId, 1);
    }
}


//new function for status icon
function toggleCourseButton(syllabusId, changeTo) {
    //if status 0
    if (changeTo == 1) {
        $("#main-button-div-" + syllabusId).html('<div id="main-button-div-' + syllabusId + '"><section class="border">'
                + '<button class="add-course" id="add-' + syllabusId + '">add</button></section>'
                + '</div>');
    } else {
        //if status 1     
        $("#main-button-div-" + syllabusId).html('<div id="main-button-div-' + syllabusId + '"><section class="border">'
                + '<button class="remove-course" id="rem-' + syllabusId + '">rem</button></section>'
                + '</div>');
    }
    //alert(divId);
}

//new function for button toggle
function setStatus(syllabusId, status) {
    //if changeTo is 1
    if (status == 0) {
        $("#course-status-div-" + syllabusId).html('<div id="course-status-div-' + syllabusId + '"><section>'
                + '<img src="../page_files/icons/success-icon.png" height="22" alt="checked"></section></div>');
    } else {
        //if changeTo is 0
        $("#course-status-div-" + syllabusId).html('<div id="course-status-div-' + syllabusId + '"><section>'
                + '<img src="../page_files/icons/circle-icon.png" height="22" alt="checked"></section></div>');

    }
}

function printSumofCredit() {
    var sum = 0;
//    $(".credit").each(function () {
//
//        var value = $(this).text();
//        // add only if the value is number
//        if (!isNaN(value) && value.length !== 0) {
//            sum += parseFloat(value);
//        }
//    });

    for (var i = 0; i < choosedCoursesJson.length; i++) {
        sum += choosedCoursesJson[i].credit;
    }

    $("#total_credit").html('<b>' + sum + '</b>');
}



