/*
 */
package shafin.sustord.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shafin.sustord.controller.ChangePasswordController;
import shafin.sustord.pojo.Message;
import shafin.sustord.util.JsonConvertion;

/**
 *
 * @author SHAFIN
 */
public class ChangePasswordServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String oldPassword = request.getParameter("oldPass");
        String newPassword = request.getParameter("newPass");

        Message message;
        message = ChangePasswordController.saveNewPassword((String) request.getSession()
                .getAttribute("regNo"), oldPassword, newPassword);

        String messageJson = JsonConvertion.objectToJsonString(message);
        PrintWriter out = response.getWriter();
        out.print(messageJson);
        //System.out.println(messageJson);
        out.flush();
        //
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
