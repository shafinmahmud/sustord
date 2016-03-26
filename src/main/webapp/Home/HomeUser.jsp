<%@page import="me.shafin.sustord.controller.UserHomeController"%>
<%@page import="java.util.ArrayList"%>
<%@page import="me.shafin.sustord.bean.ClassRoutinePOJO"%>
<%@page import="me.shafin.sustord.bean.SyllabusPOJO"%>
<%@page import="java.util.List"%>
<%@page import="me.shafin.sustord.model.CourseRegistration"%>
<%@page import="me.shafin.sustord.utility.FormatService"%>
<%@page import="me.shafin.sustord.utility.CalenderService"%>
<%@page import="me.shafin.sustord.model.StudentInfo"%>
<%@page import="me.shafin.sustord.service.StudentService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<title>Home</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">

<!-- Including Bootstrap-->
<%@include file="../WEB-INF/jspf/BootstrapInclude.jspf"%>
<!-- style sheet for this page-->
<link href="../page_files/css/style.css" rel="stylesheet">

<style type="text/css">
.scheduleTable {
	width: 100%;
}

.scheduleTable td {
	padding: 2px;
	border-top: 1px solid #f5f1f1;
	font-size: 12px;
}

.scheduleTable tr:first-child>td {
	border-top: none;
}
</style>

</head>
<body>
	<!-- validating access to this page-->
	<%@include file="../WEB-INF/jspf/AccessValidation.jspf"%>
	<!-- nav bar portion -->
	<%@include file="../WEB-INF/jspf/NavBar.jspf"%>

	<div class="portal-body">
		<div class="row">

			<!-- Side bar portion -->
			<%@include file="../WEB-INF/jspf/SideBar.jspf"%>

			<div id="main-content" class="col-md-10 column800">
				<%
					StudentService studentService;
					studentService = (StudentService) session.getAttribute("studentService");

					String regNo = (String) request.getSession().getAttribute("regNo");

					UserHomeController homeController = new UserHomeController(regNo);
					String name = homeController.getStudentName();
					String url = homeController.getStudentPhotoUrl();

					int semx = studentService.getStudentCurrentSemester(regNo);
					String semesterName = FormatService.formatSemesterName(semx);
				%>
				<div class="row">
					<div class="col-sm-12 col-xs-12 col-md-3 column200-quick ">
						<br>
						<div class="text-center home-profile-title">
							<a href="../Home/ProfileUser.jsp"> <img
								src="../page_files/images/profilepic/<%=url%>"
								class="img-circle" height="92" alt="User Image" />
							</a> <br> <br> <a href="../Home/ProfileUser.jsp">
								<div><%=name%></div>
								<div style="font-size: 14px"><%=regNo%></div>
							</a>
							<hr>
							<br>
						</div>
					</div>
					<div class="col-sm-12 col-xs-12 col-md-9 column600">


						<fieldset>
							<legend>Class Schedule</legend>
							<table class="table table-striped">

								<tbody>
									<%
										for (int i = 0; i < 7; i++) {
											CalenderService calenderService = new CalenderService();
											String date = calenderService.getDateLabel(i);
											String day = calenderService.getDayLabel(i);

											List<ClassRoutinePOJO> routines = studentService.getStudentRoutine(regNo, day);

											if (i == 0) {
												day = "TODAY";
											} else if (i == 1) {
												day = "TOMORROW";
											}
									%>
									<tr>
										<td>
											<p style="font-size: 12px">
												<b><%=day%></b><br>
											</p>
											<p><%=date%></p>
										</td>
										<td>
											<%
												if (!routines.isEmpty()) {
														for (ClassRoutinePOJO r : routines) {
											%>
											<div>
												<i><small>Time: <label><%=r.getStart()%>
															- <%=r.getEnd()%></label></small></i>
											</div>
											<div>
												<p style="font-size: 13px"><%=r.getCourseCode() + "  " + r.getTitle()%></p>
											</div> <%
 	}
 		} else {
 %>
											<div>
												<p style="font-size: 13px">
													<i>No Classes on this day</i>
												</p>
											</div> <%
 	}
 %>

										</td>
									</tr>
									<%
										}
									%>
								</tbody>
							</table>

						</fieldset>
						<br> <br>
						<fieldset>
							<legend><%=semesterName%></legend>
							<div id="divCourseList">
								<table class="table table-condensed">
									<thead>
										<tr>
											<th>Course Code</th>
											<th>Title</th>
											<th>Credit</th>
											<th>Hours/Week</th>
										</tr>
									</thead>
									<tbody>
										<%
											List<SyllabusPOJO> registeredList;
											List<SyllabusPOJO> regularList;
											List<SyllabusPOJO> droppedList;

											registeredList = studentService.getStudentRegisteredCoursesAsEntity(regNo, semx);
											regularList = new ArrayList<SyllabusPOJO>();
											droppedList = new ArrayList<SyllabusPOJO>();
											double totalCredit = 0;

											for (SyllabusPOJO s : registeredList) {
												if (s.getOfferingSemester() != semx) {
													droppedList.add(s);
												} else {
													regularList.add(s);
												}
												totalCredit += s.getCredit();
											}

											for (SyllabusPOJO s : regularList) {

												String courseCode = s.getCourseCode();
												String title = s.getTitle();
												double credit = s.getCredit();
												double hour = s.getHrsWeek();

												String hrsWeekString = "";
												if (s.isTheoryOrLab()) {
													hrsWeekString = String.valueOf(hour) + " + 0";
												} else {
													hrsWeekString = "0 + " + String.valueOf(hour);
												}
										%>
										<tr>
											<td><%=courseCode%></td>
											<td><%=title%></td>
											<td><%=credit%></td>
											<td>
												<div>
													<p><%=hrsWeekString%></p>
												</div>
											</td>
										</tr>
										<%
											}
											if (droppedList.size() != 0) {
										%>
										<tr class="danger">
											<td colspan="4"
												style="font-size: 13px; padding-top: 10px; text-align: center"><i>Dropped
													courses</i></td>
										</tr>

										<%
											for (SyllabusPOJO s : droppedList) {

													String courseCode = s.getCourseCode();
													String title = s.getTitle();
													double credit = s.getCredit();
													double hour = s.getHrsWeek();

													String hrsWeekString = "";
													if (s.isTheoryOrLab()) {
														hrsWeekString = String.valueOf(hour) + " + 0";
													} else {
														hrsWeekString = "0 + " + String.valueOf(hour);
													}
										%>
										<tr>
											<td><%=courseCode%></td>
											<td><%=title%></td>
											<td><%=credit%></td>
											<td>
												<div>
													<p><%=hrsWeekString%></p>
												</div>
											</td>
										</tr>
										<%
											}
											}
										%>
									
									<tfoot>
										<tr>
											<th colspan="2" style="text-align: center"><b>Total
													credit</b></th>
											<th style="text-align: left; font-size: 14px"
												id="total_credit" colspan="2"><b><%=totalCredit%></b></th>
										</tr>
									</tfoot>
									</tbody>
								</table>
							</div>

						</fieldset>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- footer portion -->
	<%@include file="../WEB-INF/jspf/Footer.jspf"%>

</body>
</html>
