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
import me.shafin.sustord.controller.SemesterAnalysisController;
import me.shafin.sustord.pojo.StatSemesterPojo;
import me.shafin.sustord.utility.JsonConvertion;
import me.shafin.sustord.service.StudentService;

/**
 *
 * @author SHAFIN
 */
public class SemesterStatServlet extends HttpServlet {

 
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        StudentService studentService = (StudentService) session.getAttribute("studentService");
        int sem = Integer.parseInt(request.getParameter("semester"));

        String regNo = (String) request.getSession().getAttribute("regNo");
        StatSemesterPojo stat = studentService.getSemesterStatistics(regNo, sem);
        
        SemesterAnalysisController controller = new SemesterAnalysisController(regNo);
        stat.setStudentRankedList(controller.getSemesterRankList(sem));
        
        String statJson = JsonConvertion.objectToJsonString(stat);
        //System.out.println(statJson);

        PrintWriter out = response.getWriter();
        out.print(statJson);
        out.flush();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
