<%-- 
    Document   : Ranking
    Created on : Feb 3, 2015, 10:25:39 PM
    Author     : SHAFIN
--%>

<%@page import="sm.sustord.utility.StatisticsHelper"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="sm.sustord.bean.StudentPOJO"%>
<%@page import="java.util.List"%>
<%@page import="sm.sustord.model.StudentInfo"%>
<%@page import="sm.sustord.service.StudentService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Ranking</title>
<style type="text/css">
[ng\:cloak], [ng-cloak], [data-ng-cloak], [x-ng-cloak], .ng-cloak,
	.x-ng-cloak, .ng-hide {
	display: none !important;
}

ng\:form {
	display: block;
}

.ng-animate-block-transitions {
	transition: 0s all !important;
	-webkit-transition: 0s all !important;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<%@include file="../WEB-INF/jspf/BootstrapInclude.jspf"%>
<link href="../page_files/css/style.css" rel="stylesheet">

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script src="http://code.highcharts.com/highcharts.js"></script>

<script src=""></script>
</head>
<body>
	<%@include file="../WEB-INF/jspf/AccessValidation.jspf"%>
	<%@include file="../WEB-INF/jspf/NavBar.jspf"%>

	<div class="portal-body">
		<div class="row">
			<%@include file="../WEB-INF/jspf/SideBar.jspf"%>
			<div id="main-content" class="col-md-10 column800">
				<div class="col-lg-12">
					<fieldset>
						<%
							StudentService studentService;
							studentService = (StudentService) session.getAttribute("studentService");
							String myRegNo = (String) request.getSession().getAttribute("regNo");
							List<StudentPOJO> students = studentService.getStudentRankList(myRegNo);

							Map map = StatisticsHelper.getCgpaDistibutionMap(students);
						%>
						<legend>CGPA Analysis</legend>

						<div id="line-chart-div" style="width: 100%; height: 400px;"></div>
						<script>
							$(function() {
								$('#line-chart-div')
										.highcharts(
												{
													chart : {
														type : 'column'
													},
													title : {
														text : 'Grade Distribution'
													},
													subtitle : {
														text : $(
																'#semester-dropdown option:selected')
																.text()
													},
													xAxis : {
														type : 'category',
														labels : {
															rotation : -45,
															style : {
																fontSize : '13px',
																fontFamily : 'Verdana, sans-serif'
															}
														}
													},
													yAxis : {
														min : 0,
														title : {
															text : 'No. of students'
														}
													},
													legend : {
														enabled : false
													},
													tooltip : {
														pointFormat : '<b>{point.y:.1f}</b>'
													},
													series : [ {
														name : 'Population',
														data : [
																[ 'A+',
						<%=map.get("Ap")%>
							],
																[ 'A',
						<%=map.get("A")%>
							],
																[ 'A-',
						<%=map.get("Am")%>
							],
																[ 'B+',
						<%=map.get("Bp")%>
							],
																[ 'B',
						<%=map.get("B")%>
							],
																[ 'B-',
						<%=map.get("Bm")%>
							],
																[ 'C+',
						<%=map.get("Cp")%>
							],
																[ 'C',
						<%=map.get("C")%>
							],
																[ 'C-',
						<%=map.get("Cm")%>
							],
																[ 'F',
						<%=map.get("F")%>
							], ],
														dataLabels : {
															enabled : true,
															rotation : -90,
															color : '#FFFFFF',
															align : 'right',
															x : 4,
															y : 10,
															style : {
																fontSize : '13px',
																fontFamily : 'Verdana, sans-serif',
																textShadow : '0 0 3px black'
															}
														}
													} ]
												});
							});
						</script>
					</fieldset>
				</div>
				<br> <br> <br>
				<legend>CGPA Ranking</legend>
				<fieldset>
					<table class="table table-condensed">
						<thead>
							<tr>
								<th style="width: 5%">#Rank</th>
								<th style="width: 40%">Name</th>
								<th style="width: 15%">Registration No</th>
								<th style="width: 20%; text-align: center">Completed
									Credits</th>
								<th style="width: 20%; text-align: center">CGPA</th>
							</tr>
						</thead>
						<tbody>
							<%
								for (StudentPOJO s : students) {
									String name = s.getName().toUpperCase();
									String reg = s.getRegistrationNo();
									double completedCredit = s.getCompletedCredits();
									double cgpa = s.getCgpa();

									if (myRegNo.equals(reg)) {
							%>
							<tr class="success">
								<td class="text-center"><b>#<%=students.indexOf(s) + 1%></b></td>
								<td><b><%=name%></b></td>
								<td class="text-center"><b><%=reg%></b></td>
								<td class="text-center"><b><%=completedCredit%></b></td>
								<td class="text-center"><b><%=cgpa%></b></td>
							</tr>
							<%
								} else {
							%>
							<tr>
								<td class="text-center">#<%=students.indexOf(s) + 1%></td>
								<td><%=name%></td>
								<td class="text-center"><%=reg%></td>
								<td class="text-center"><%=completedCredit%></td>
								<td class="text-center"><%=cgpa%></td>
							</tr>
							<%
								}
								}
							%>
						</tbody>

					</table>

				</fieldset>

			</div>

		</div>
	</div>
	</div>
	<%@include file="../WEB-INF/jspf/Footer.jspf"%>
</body>
</html>
