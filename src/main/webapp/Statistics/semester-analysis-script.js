var ap = 0;
var a = 0;
var am = 0;
var bp = 0;
var b = 0;
var bm = 0;
var cp = 0;
var c = 0;
var cm = 0;
var f = 0;

$(document).ready(function () {
    ajaxCallForDropDown();
    $('#semester-dropdown').change(function () {
        ajaxCallForDropDown();
    });
});

//function ajaxCallForCurrentSemester() {
//
//    $.ajax({
//        url: '../GetCurrentSemester',
//        type: 'POST',
//        success: function (currentSemester) {
//            $('#semester-dropdown').val(currentSemester).change();
//        },
//        error: function () {
//            alert("ERRORX");
//        }
//    });
//}

function ajaxCallForDropDown() {
    $.ajax({
        url: '../SemesterStatServlet',
        data: {
            semester: $('#semester-dropdown').val()
        },
        type: 'POST',
        success: function (semesterStat) {

            var semesterStatJson = [];
            $('#details-div').html('');
            if (semesterStat != 'EMPTY') {
                semesterStatJson = $.parseJSON(semesterStat);
            }

            $('#pie-chart-table tr').not(function () {
                if ($(this).has('th').length) {
                    return true;
                }
            }).remove();



            //setting data for the bar-chart
            ap = semesterStatJson.semesterGradeDistributionMap.Ap;
            a = semesterStatJson.semesterGradeDistributionMap.A;
            am = semesterStatJson.semesterGradeDistributionMap.Am;
            bp = semesterStatJson.semesterGradeDistributionMap.Bp;
            b = semesterStatJson.semesterGradeDistributionMap.B;
            bm = semesterStatJson.semesterGradeDistributionMap.Bm;
            cp = semesterStatJson.semesterGradeDistributionMap.Cp;
            c = semesterStatJson.semesterGradeDistributionMap.C;
            cm = semesterStatJson.semesterGradeDistributionMap.Cp;
            f = semesterStatJson.semesterGradeDistributionMap.F;

            printBarChart(semesterStatJson);

            for (var i = 0; i < semesterStatJson.courseStat.length; i++) {
                printPieChart(semesterStatJson.courseStat[i]);
            }

            printRankedList(semesterStatJson.studentRankedList);

        },
        error: function () {
            alert("CourseDetails ERROR");
        }
    });
}

function printBarChart() {

//Build the bar chart
    $('#bar-chart-div').html('<div id="new-div" style="width: 100%; height: 275px;"></div>');
    $(function () {



        $('#new-div').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: 'Grade Distribution'
            },
            subtitle: {
                text: $('#semester-dropdown option:selected').text()
            },
            xAxis: {
                type: 'category',
                labels: {
                    rotation: -45,
                    style: {
                        fontSize: '13px',
                        fontFamily: 'Verdana, sans-serif'
                    }
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'No. of students'
                }
            },
            legend: {
                enabled: false
            },
            tooltip: {
                pointFormat: '<b>{point.y:.1f}</b>'
            },
            series: [{
                    name: 'semesterGrade',
                    data: [
                        ['A+', ap],
                        ['A', a],
                        ['A-', am],
                        ['B+', bp],
                        ['B', b],
                        ['B-', bm],
                        ['C+', cp],
                        ['C', c],
                        ['C-', cm],
                        ['F', f],
                    ],
                    dataLabels: {
                        enabled: true,
                        rotation: -90,
                        color: '#FFFFFF',
                        align: 'right',
                        x: 4,
                        y: 10,
                        style: {
                            fontSize: '13px',
                            fontFamily: 'Verdana, sans-serif',
                            textShadow: '0 0 3px black'
                        }
                    }
                }]
        });
    });
}


function printPieChart(courseObject) {

    var syllabusId = courseObject.syllabusId;
    var code = courseObject.courseCode;
    var title = courseObject.title;
    var attend = courseObject.noOfAttendedStudent;

    var obatainedGrade = courseObject.grade;
    obatainedGrade = $.trim(obatainedGrade); // trimming spaces from the first and last spaces

    if (obatainedGrade.length > 2) {
        obatainedGrade = courseObject.grade.slice(-2);
    }
    // alert(obatainedGrade.length + ' ' + obatainedGrade);

    var dataSet = [];
    var letterArray = ["A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "F"];
    var gradeArray = [
        courseObject.courseGradeDistributionMap.Ap,
        courseObject.courseGradeDistributionMap.A,
        courseObject.courseGradeDistributionMap.Am,
        courseObject.courseGradeDistributionMap.Bp,
        courseObject.courseGradeDistributionMap.B,
        courseObject.courseGradeDistributionMap.Bm,
        courseObject.courseGradeDistributionMap.Cp,
        courseObject.courseGradeDistributionMap.C,
        courseObject.courseGradeDistributionMap.Cm,
        courseObject.courseGradeDistributionMap.F
    ];


    for (var i = 0; i < letterArray.length; i++) {
        // alert(letterArray[i].length + ' ' + letterArray[i] + '---' + obatainedGrade.length + ' ' + obatainedGrade);
        if (letterArray[i] === obatainedGrade) {
//            alert(letterArray[i]);
            var gradeObject = {
                name: letterArray[i],
                y: gradeArray[i],
                sliced: true,
                selected: true
            };
            dataSet.push(gradeObject);
        } else {
            dataSet.push([letterArray[i], gradeArray[i]]);
        }
    }

    var gradeObject = {
        type: 'pie',
        name: 'grade',
        data: dataSet
    };

    $('#pie-chart-table').append('<tr>'
            + '<td>'
            + '<div id="container-' + syllabusId + '"style="width: 350px; height: 350px;"></div>'
            + '</td>'
            + '<td style="padding-top: 95px">'

            + '<p style="font-size: 16px"><b>' + code + ' </b> </p>'
            + '<p style="font-size: 16px">' + title + '</p>'
            + '<p style="font-size: 14px">Course Attends: ' + attend + '</p>'
            + '<p style="font-size: 14px">Obtained grade: ' + courseObject.grade + '</p>'
            + '</td>'
            + '</tr>');

    //alert(dataSet);
    // Build the chart
    $("#container-" + syllabusId).highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: '',
            style: {"color": "red", "fontSize": "14px"}
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: false,
                    distance: 10
                },
                showInLegend: true,
                colors: ['#27ae60', '#2ecc71', '#90ed7d', '#2980b9', '#3498db',
                    '#91e8e1', '#e67e22', '#F39C12', '#F9BF3B', '#e74c3c']
            }
        },
        legend: {
            align: 'right',
            verticalAlign: 'top',
            layout: 'vertical',
            x: 0,
            y: 100,
            labelFormat: '{name}'
        },
        series: [gradeObject]
    });

}


function printRankedList(rankedList) {
    $('#semester-rank-table').find("tr:gt(0)").remove();
    
    for (i = 0; i < rankedList.length; i++) {
        $('#semester-rank-table').append('<tr>'
                + '<td class="text-center">' + rankedList[i].gradeWiseRank + '</td>'
                + '<td>' + rankedList[i].name + '</td>'
                + '<td  class="text-center">' + rankedList[i].registrationNo + '</td>'
                + '<td  class="text-center">' + rankedList[i].completedCredits + '</td>'
                + '<td  class="text-center">' + rankedList[i].gradePoint + '</td>'
                + '</tr>');
    }

}

