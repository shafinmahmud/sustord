
package me.shafin.sustord.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.shafin.sustord.service.StudentService;

/**
 *
 * @author SHAFIN
 */
public class CourseRegServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        StudentService service = (StudentService) request.getSession().getAttribute("studentService");
        String regNo = (String) request.getSession().getAttribute("regNo");
        boolean b = service.doStudentCourseRegistration(regNo,request.getParameter("jsonString"),
               Integer.parseInt(request.getParameter("semester")));
        
        PrintWriter out = response.getWriter();
        if (b) {
            out.print("OK");
        out.flush();
        }else{
            out.print("ER");
        out.flush();
        }
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
