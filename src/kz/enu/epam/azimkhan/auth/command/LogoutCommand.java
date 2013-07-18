package kz.enu.epam.azimkhan.auth.command;

import kz.enu.epam.azimkhan.auth.entity.User;
import kz.enu.epam.azimkhan.auth.logic.authentication.AuthenticationLogic;
import kz.enu.epam.azimkhan.auth.resource.PathManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Logout command
 */
public class LogoutCommand extends ActionCommand{

    private final Logger logger = Logger.getRootLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute(AuthenticationLogic.SESSION_VAR);
        if (user != null){
            AuthenticationLogic.logout(request);
            logger.info("Logged out: " + user.getUsername());
        }
        return PathManager.INSTANCE.getString("path.page.main");
    }
}
