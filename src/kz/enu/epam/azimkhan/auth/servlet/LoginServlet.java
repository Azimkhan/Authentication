package kz.enu.epam.azimkhan.auth.servlet;

import kz.enu.epam.azimkhan.auth.command.ActionCommand;
import kz.enu.epam.azimkhan.auth.command.LoginCommand;
import kz.enu.epam.azimkhan.auth.helper.RequestHelper;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Used to log in to the system
 */
public class LoginServlet extends HttpServlet {

    private RequestHelper requestHelper = RequestHelper.INSTANCE;
    private Logger logger = Logger.getRootLogger();

    /**
     * Called when user posts login and password
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActionCommand command = requestHelper.getCommand(request);


        String page = command.execute(request, response);

        if (page != null) {
            response.sendRedirect(page);
        } else {
            doGet(request, response);
        }

    }

    /**
     * Called when user opens login page
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("login_var", LoginCommand.LOGIN_PARAMETER);
        request.setAttribute("password_var", LoginCommand.PASSWORD_PARAMETER);
        request.getRequestDispatcher("login.jsp").forward(request,response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

    }
}
