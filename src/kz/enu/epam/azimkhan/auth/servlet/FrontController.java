package kz.enu.epam.azimkhan.auth.servlet;

import kz.enu.epam.azimkhan.auth.command.ActionCommand;
import kz.enu.epam.azimkhan.auth.helper.RequestHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Front Controller
 */
public class FrontController extends HttpServlet {

    private final RequestHelper requestHelper = RequestHelper.INSTANCE;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Process any incoming request
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActionCommand command = requestHelper.getCommand(request);
        String redirectPath = command.execute(request, response);

        if (redirectPath != null){
            response.sendRedirect(redirectPath);
        }
    }

}
