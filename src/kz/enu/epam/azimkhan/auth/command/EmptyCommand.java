package kz.enu.epam.azimkhan.auth.command;

import kz.enu.epam.azimkhan.auth.resource.UrlManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This command is used if empty or wrong command is specified
 */
public class EmptyCommand extends ActionCommand{

    /**
     * Return to the error page
     *
     * @param request request to read the command from
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return UrlManager.INSTANCE.getString("path.page.main");
    }
}
