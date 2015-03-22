var coursesJson = [];

$(document).ready(function () {

    ajaxCallForCurrentSemester();

    $('#semester-dropdown').change(function () {
        ajaxCallForDropDown();
    });
});

function ajaxCallForCurrentSemester() {

    $.ajax({
        url: '../GetCurrentSemester',
        type: 'POST',
        success: function (currentSemester) {
            $('#semester-dropdown').val(currentSemester).change();
        },
        error: function () {
            alert("ERRORX");
        }
    });
}

function ajaxCallForDropDown() {
    $.ajax({
        url: '../CourseDetailsServlet',
        data: {
            semester: $('#semester-dropdown').val()
        },
        type: 'POST',
        success: function (courseDetailsList) {

            $('#details-div').html('');
            if (courseDetailsList != 'EMPTY') {
                coursesJson = $.parseJSON(courseDetailsList);
            }

            for (var i = 0; i < coursesJson.length; i++) {
                printCourseDetails(coursesJson[i]);
            }

        },
        error: function () {
            alert("CourseDetails ERROR");
        }
    });
}


function printCourseDetails(courseObject) {
    var code = courseObject.courseCode;
    var title = courseObject.title;
    var credit = courseObject.credit;
    var hrs = courseObject.hrsWeek;
    var details = courseObject.courseDetails;

    $('#details-div').append('<table class="table" style="background-color: #BCED91"> '
            + '<tbody>'
            + '<tr>'
            + '<td>'
            + '<label >' + code + ': ' + title + '</label>'
            + '</td>'
            + '<td>'
            + '<p class="pull-right" ><i>' + hrs + ' Hours/Week, ' + credit + ' Credit</i></p>'
            + '</td>'
            + '</tr>'
            + '</tbody>'
            + '</table>');

    if (details == '') {
        details = '<i>Details for ' + code + ' is not available right now.</i>'
    }
    $('#details-div').append('<div class="margin-l5 " style="font-size: 14px">'
            + details +
            '</div><br>');

}


