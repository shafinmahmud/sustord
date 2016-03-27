package sm.sustord.servlet;

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

import sm.sustord.controller.LoginController;
import sm.sustord.pojo.LoginMessage;
import sm.sustord.service.StudentService;
import sm.sustord.utility.JsonConvertion;

/**
 *
 * @author SHAFIN
 */
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;


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
                    
                    out.flush();
                } catch (IOException ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                try {
                    String messageJson = JsonConvertion.objectToJsonString(message);
                    PrintWriter out = response.getWriter();
                   
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
