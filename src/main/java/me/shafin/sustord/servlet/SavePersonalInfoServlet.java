/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.shafin.sustord.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.shafin.sustord.entity.PersonalInfo;
import me.shafin.sustord.service.StudentService;

/**
 *
 * @author SHAFIN
 */
public class SavePersonalInfoServlet extends HttpServlet {

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
        PersonalInfo personalInfo = service.getStudentInfo().getPersonalInfo();
        personalInfo.setFathersName(request.getParameter("fname"));
        personalInfo.setMothersName(request.getParameter("mname"));
        personalInfo.setPresentAddress(request.getParameter("pradd"));
        personalInfo.setPermanentAddress(request.getParameter("paadd"));
        personalInfo.setContact(request.getParameter("ph"));
        personalInfo.setEmail(request.getParameter("em"));
        personalInfo.setDateOfBirth(request.getParameter("dob"));
        personalInfo.setSex(request.getParameter("sex"));
        personalInfo.setNationality(request.getParameter("nat"));
        personalInfo.setReligion(request.getParameter("reli"));
        personalInfo.setMaritalStatus(request.getParameter("mar"));
        personalInfo.setBloodGroup(request.getParameter("blood"));

        boolean status = service.savePersonalInfo(personalInfo);
        if (status) {
            response.getWriter().print("OK");
        } else {
            response.getWriter().print("ERROR");
        }

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