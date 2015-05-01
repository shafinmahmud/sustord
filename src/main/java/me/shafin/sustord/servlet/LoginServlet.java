package me.shafin.sustord.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import me.shafin.sustord.pojo.LoginMessage;
import me.shafin.sustord.controller.LoginController;
import me.shafin.sustord.utility.JsonConvertion;
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
        try {
            response.setContentType("text/html;charset=UTF-8");

            String userType = request.getParameter("ut");
            String id = request.getParameter("un");
            String password = request.getParameter("pw");

            LoginMessage message = new LoginMessage();

            if (userType.equals("student")) {
                message = LoginController.authencateStudent(id, password);
            } else if (userType.equals("batchAdmin")) {
                message = LoginController.authencateBatchAdmin(id, password);
            } else {

            }

            if (message.isRequestedIdValid() && message.isRequestedPasswordValid()) {

                HttpSession session = request.getSession();

                if (userType.equalsIgnoreCase("student")) {
                    StudentService service = LoginController.temporarySupport();
                    session.setAttribute("studentService", service);
                    session.setAttribute("user", "student");
                    session.setAttribute("regNo", message.getRequestedId());
                }else{
                    session.setAttribute("user", "batchAdmin");
                    session.setAttribute("id", message.getRequestedId());
                }

                session.setAttribute("loginStatus", "ok");
                
                try {
                    String messageJson = JsonConvertion.objectToJsonString(message);
                    PrintWriter out = response.getWriter();
                    out.print(messageJson);
                    System.out.println(messageJson);
                    out.flush();
                } catch (IOException ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                try {
                    String messageJson = JsonConvertion.objectToJsonString(message);
                    PrintWriter out = response.getWriter();
                    System.out.println(messageJson);
                    out.print(messageJson);
                    out.flush();
                } catch (IOException ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (ExceptionInInitializerError ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String errorString = sw.toString(); // stack trace as a string  

            //temporary way to handle service class exception. intend to remove later
            try {
                PrintWriter out = response.getWriter();
                LoginMessage message = new LoginMessage();
                message.setMessageTitle("Sql Exception");
                message.setMessageBody(errorString);
                out.print(JsonConvertion.objectToJsonString(message));
                out.flush();
            } catch (IOException io) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, io);
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
