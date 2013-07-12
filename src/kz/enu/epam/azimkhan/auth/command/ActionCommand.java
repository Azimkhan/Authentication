package kz.enu.epam.azimkhan.auth.command;

import com.sun.deploy.net.HttpRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Abstract command
 */
public abstract class ActionCommand {

    /**
     * This method reads a command from the request
     * and processes it. The result will be given as
     * a page to forward to
     *
     *
     * @param request request to read the command from
     * @param response
     * @return forward page
     */
    public abstract String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
