/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.shafin.sustord.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.shafin.sustord.bean.SyllabusPOJO;
import me.shafin.sustord.service.JsonConvertion;
import me.shafin.sustord.service.StudentService;

/**
 *
 * @author SHAFIN
 */
public class SyllabusServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        StudentService service = (StudentService) request.getSession().getAttribute("studentService");
        String regNo = (String) request.getSession().getAttribute("regNo");
        int x = Integer.parseInt(request.getParameter("semester"));

        List<SyllabusPOJO> syllabus = service.getStudentSyllabusAsEntity(regNo,x);
        String syllabusCoursesJson = JsonConvertion.objectListToJsonString(syllabus);
        //System.out.println("syllabus: " + syllabusCoursesJson);

        List<SyllabusPOJO> pending = service.getStudentPendingCoursesAsEntity(regNo,x);
        String pendingCoursesJson = JsonConvertion.objectListToJsonString(pending);
        //System.out.println("pending: "+pendingCoursesJson);

        List<SyllabusPOJO> dropped = service.getStudentDroppedCoursesAsEntity(regNo,x);
        String droppedCoursesJson = JsonConvertion.objectListToJsonString(dropped);
       // System.out.println("dropped: "+droppedCoursesJson);

        List<SyllabusPOJO> taken = service.getStudentRegisteredCoursesAsEntity(regNo,x);
        String takenCoursesJson = JsonConvertion.objectListToJsonString(taken);
        //System.out.println("taken: "+takenCoursesJson);

        if (syllabusCoursesJson.equals("[]")) {
            syllabusCoursesJson = "EMPTY";
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
        out.print(syllabusCoursesJson + "#" + pendingCoursesJson+ "#" + droppedCoursesJson + "#" + takenCoursesJson);
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
