
package me.shafin.sustord.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import me.shafin.sustord.bean.SyllabusPOJO;
import me.shafin.sustord.utility.JsonConvertion;
import me.shafin.sustord.service.StudentService;

/**
 *
 * @author SHAFIN
 */
public class CourseDetailsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        StudentService studentService = (StudentService) session.getAttribute("studentService");
        int sem = Integer.parseInt(request.getParameter("semester"));

        String regNo = (String) request.getSession().getAttribute("regNo");
        List<SyllabusPOJO> courseDetailsList;
 
        courseDetailsList = studentService.getStudentSyllabusAsEntity(regNo, sem);
         for(SyllabusPOJO s: courseDetailsList){
            String text = studentService.getStudentCourseDetails(s.getCourseCode());
            s.setCourseDetails(text);
        }
        String courseDetailsListJson = JsonConvertion.objectListToJsonString(courseDetailsList);
        //System.out.println("taken: "+takenCoursesJson);

        if (courseDetailsListJson.equals("[]")) {
            courseDetailsListJson = "EMPTY";
        }
        
        PrintWriter out = response.getWriter();
        //out.print(syllabusCoursesJson + "#" + droppedCoursesJson + "#" + takenCoursesJson);
        out.print(courseDetailsListJson);
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
