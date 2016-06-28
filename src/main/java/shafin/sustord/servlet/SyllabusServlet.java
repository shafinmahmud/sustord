/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shafin.sustord.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shafin.sustord.bean.SyllabusPOJO;
import shafin.sustord.service.StudentService;
import shafin.sustord.util.JsonConvertion;

/**
 *
 * @author SHAFIN
 */
public class SyllabusServlet extends HttpServlet {

  
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        StudentService service = (StudentService) request.getSession().getAttribute("studentService");
        String regNo = (String) request.getSession().getAttribute("regNo");
        int sem = Integer.parseInt(request.getParameter("semester"));

        List<SyllabusPOJO> syllabus = service.getStudentSyllabusAsEntity(regNo, sem);
        List<SyllabusPOJO> optionalFiltered = new ArrayList<>();
        boolean optionalFoundFlag = false;
        for (SyllabusPOJO spojo : syllabus) {
            if (spojo.getCourseCode().endsWith("*")) {
                optionalFoundFlag = true;
            } else {
                optionalFiltered.add(spojo);
            }
        }

        List<SyllabusPOJO> optionalCourses = new ArrayList<>();

        if (optionalFoundFlag) {
            optionalCourses = service.getStudentSyllabusAsEntity(regNo, 0);
        }

        String syllabusCoursesJson = JsonConvertion.objectListToJsonString(optionalFiltered);
       // System.out.println("syllabus: " + syllabusCoursesJson);

        String optionalCoursesJson = JsonConvertion.objectListToJsonString(optionalCourses);
        //System.out.println("optional: " + optionalCoursesJson);

        List<SyllabusPOJO> pending = service.getStudentPendingCoursesAsEntity(regNo, sem);
        String pendingCoursesJson = JsonConvertion.objectListToJsonString(pending);
       // System.out.println("pending: " + pendingCoursesJson);

        List<SyllabusPOJO> dropped = service.getStudentDroppedCoursesAsEntity(regNo, sem);
        String droppedCoursesJson = JsonConvertion.objectListToJsonString(dropped);
        //System.out.println("dropped: " + droppedCoursesJson);

        List<SyllabusPOJO> taken = service.getStudentRegisteredCoursesAsEntity(regNo, sem);
        String takenCoursesJson = JsonConvertion.objectListToJsonString(taken);
        //System.out.println("taken: " + takenCoursesJson);

        if (syllabusCoursesJson.equals("[]")) {
            syllabusCoursesJson = "EMPTY";
        }

        if (optionalCoursesJson.equals("[]")) {
            optionalCoursesJson = "EMPTY";
        }

        if (pendingCoursesJson.equals("[]")) {
            pendingCoursesJson = "EMPTY";
        }

        if (droppedCoursesJson.equals("[]")) {
            droppedCoursesJson = "EMPTY";
        }

        if (takenCoursesJson.equals("[]")) {
            takenCoursesJson = "EMPTY";
        }

        PrintWriter out = response.getWriter();
        //out.print(syllabusCoursesJson + "#" + droppedCoursesJson + "#" + takenCoursesJson);
        out.print(syllabusCoursesJson + "#" + pendingCoursesJson + "#" + droppedCoursesJson + "#" + takenCoursesJson + "#" + optionalCoursesJson);
        out.flush();
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
