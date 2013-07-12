package kz.enu.epam.azimkhan.auth.command;

import kz.enu.epam.azimkhan.auth.exception.AuthenticationException;
import kz.enu.epam.azimkhan.auth.logic.authentication.AuthenticationLogic;
import kz.enu.epam.azimkhan.auth.resource.MessageManager;
import kz.enu.epam.azimkhan.auth.resource.UrlManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
public class LoginCommand extends ActionCommand{

    public static final String LOGIN_PARAMETER = "login";
    public static final String PASSWORD_PARAMETER = "password";
    private Logger logger = Logger.getRootLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final String login = request.getParameter(LOGIN_PARAMETER);
        final String password = request.getParameter(PASSWORD_PARAMETER);

        AuthenticationLogic logic = new AuthenticationLogic(request);

        UrlManager urlManager = UrlManager.INSTANCE;
        MessageManager messageManager = MessageManager.INSTANCE;

        try {
            logic.authenticate(login, password);

            logger.info("Successful authentication by login: " + login);
            return urlManager.getString("path.page.main");

        } catch (AuthenticationException e) {
            String errorMessage = null;

            switch (e.getErrorCode()){
                case AuthenticationException.EMPTY_LOGIN:
                    errorMessage = messageManager.getMessage("login.empty_login");
                    break;
                case AuthenticationException.EMPTY_PASSWORD:
                    errorMessage = messageManager.getMessage("login.empty_password");
                    break;
                case AuthenticationException.INVALID_LOGIN_OR_PASSWORD:
                    errorMessage = messageManager.getMessage("login.invalid_login_pass");
                    break;
                default:
                    errorMessage = messageManager.getMessage("login.unknown_error");
                    break;
            }

            logger.info("Authentication error [login=" + login + "]: " + errorMessage);
            request.setAttribute("error", errorMessage);

        }

        return null;
    }
}
