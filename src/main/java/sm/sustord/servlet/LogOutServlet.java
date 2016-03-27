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
import javax.servlet.http.HttpSession;

import sm.sustord.utility.ServiceDispatcher;

/**
 *
 * @author SHAFIN
 */
public class LogOutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String userType = (String) request.getSession().getAttribute("user");
        String regNo = (String) request.getSession().getAttribute("regNo");

        if (request.isRequestedSessionIdValid()) {
            HttpSession session = request.getSession();
            session.invalidate();

            if (userType.equalsIgnoreCase("student")) {
                ServiceDispatcher.nullifyStudentServices(regNo);
                response.sendRedirect("Login/LoginUser.jsp");
            } else if (userType.equalsIgnoreCase("batchAdmin")) {
                ServiceDispatcher.nullifyStudentServices(regNo);
                response.sendRedirect("Admin/LoginAdmin.jsp");
            } else {
                response.sendRedirect("Login/LoginUser.jsp");
            }
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
