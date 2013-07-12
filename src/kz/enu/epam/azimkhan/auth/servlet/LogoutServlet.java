package kz.enu.epam.azimkhan.auth.servlet;

import kz.enu.epam.azimkhan.auth.command.ActionCommand;
import kz.enu.epam.azimkhan.auth.helper.RequestHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  Logout servlet
 */
public class LogoutServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        ActionCommand command = RequestHelper.INSTANCE.getCommand("logout");
        String page = command.execute(request, response);

        response.sendRedirect(page);
    }
}
