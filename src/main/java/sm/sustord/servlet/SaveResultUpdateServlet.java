/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.sustord.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sm.sustord.service.StudentService;

/**
 *
 * @author SHAFIN
 */
public class SaveResultUpdateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;


	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        StudentService service = (StudentService) request.getSession().getAttribute("studentService");
        String regNo = (String) request.getSession().getAttribute("regNo");
        
        boolean b = service.doStudentResultUpdate(regNo,request.getParameter("jsonString"),
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
