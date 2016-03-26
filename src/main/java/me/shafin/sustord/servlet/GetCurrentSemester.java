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
import javax.servlet.http.HttpSession;
import me.shafin.sustord.service.StudentService;

/**
 *
 * @author SHAFIN
 */
public class GetCurrentSemester extends HttpServlet {

  
	private static final long serialVersionUID = 1L;


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        StudentService studentService = (StudentService) session.getAttribute("studentService");
        String regNo = (String) request.getSession().getAttribute("regNo");
        int sem = studentService.getStudentCurrentSemester(regNo);
        //System.out.println(sem);
        PrintWriter out = response.getWriter();
        out.print(sem);
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
    }// </editor-fold>

}
