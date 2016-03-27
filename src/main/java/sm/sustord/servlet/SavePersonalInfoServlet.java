/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.sustord.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sm.sustord.model.PersonalInfo;
import sm.sustord.service.StudentService;

/**
 *
 * @author SHAFIN
 */
public class SavePersonalInfoServlet extends HttpServlet {

   
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        StudentService service = (StudentService) request.getSession().getAttribute("studentService");
        String regNo = (String) request.getSession().getAttribute("regNo");
        
        PersonalInfo personalInfo = service.getStudentInfoObjectFromRegNo(regNo).getPersonalInfo();
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
