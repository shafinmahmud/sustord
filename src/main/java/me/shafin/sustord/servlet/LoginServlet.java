package me.shafin.sustord.servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import me.shafin.sustord.controller.LoginController;
import me.shafin.sustord.service.StudentService;

/**
 *
 * @author SHAFIN
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");

        String userType = request.getParameter("ut");
        String registrationNo = request.getParameter("un");
        String password = request.getParameter("pw");

        StudentService studentService = new StudentService();

        String verification = "noresponse#";

        if (userType.equals("student")) {
            verification = LoginController.authencateStudent(registrationNo, password);
        } else if (userType.equals("admin")) {
                  
        }

        String[] arr = verification.split("#");
        //System.out.println(arr[0]+" "+arr[1]);
        if (arr[0].equals("verified")) {
            
            HttpSession session = request.getSession();
            session.setAttribute("studentService", studentService);
            String status = "ok";
            session.setAttribute("loginStatus", status);
            session.setAttribute("regNo", arr[1]);
            try {
                response.getWriter().print(arr[0]);
            } catch (IOException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            try {
                // System.out.println("servlet print------------------" +verification);
                response.getWriter().print(arr[0]);
            } catch (IOException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
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
