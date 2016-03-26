/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class ResultUpdateServlet extends HttpServlet {

    
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

       
        StudentService service = (StudentService) request.getSession().getAttribute("studentService");
        String regNo = (String) request.getSession().getAttribute("regNo");
        int x = Integer.parseInt(request.getParameter("semester"));
         // System.out.println(x);
        String takenCoursesJson = service.getStudentRegisteredCourses(regNo,x);
         //System.out.println(takenCoursesJson);
        if(takenCoursesJson.equals("[]")){
            takenCoursesJson = "EMPTY";
        }
        
        PrintWriter out = response.getWriter();
        out.print(takenCoursesJson);
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
