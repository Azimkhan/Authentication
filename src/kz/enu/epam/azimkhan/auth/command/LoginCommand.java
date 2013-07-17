package kz.enu.epam.azimkhan.auth.command;

import kz.enu.epam.azimkhan.auth.exception.AuthenticationException;
import kz.enu.epam.azimkhan.auth.logic.authentication.AuthenticationLogic;
import kz.enu.epam.azimkhan.auth.notification.creator.NotificationCreator;
import kz.enu.epam.azimkhan.auth.notification.entity.Notification;
import kz.enu.epam.azimkhan.auth.notification.service.NotificationService;
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
        final NotificationCreator notificationCreator = new NotificationCreator(request);
        final NotificationService notificationService = new NotificationService(request);
        final AuthenticationLogic logic = new AuthenticationLogic(request);


        UrlManager urlManager = UrlManager.INSTANCE;
        MessageManager messageManager = MessageManager.INSTANCE;
        Notification notification = null;

        try {
            logic.authenticate(login, password);

            logger.info("Successful authentication by login: " + login);
            notification = notificationCreator.createFromProperty("info.auth.success");
            notificationService.push(notification);

            return urlManager.getString("path.page.main");

        } catch (AuthenticationException e) {
            String messageKey = null;

            switch (e.getErrorCode()){

                case AuthenticationException.EMPTY_LOGIN:
                    messageKey = "error.auth.empty_login";
                    break;
                case AuthenticationException.EMPTY_PASSWORD:
                    messageKey = "error.auth.empty_password";
                    break;
                case AuthenticationException.INVALID_LOGIN_OR_PASSWORD:
                    messageKey = "error.auth.invalid_login_pass";
                    break;
                default:
                    messageKey = "error.auth.unknown_error";
                    break;
            }

            logger.info("Authentication error [login=" + login + "], error code: " + e.getErrorCode());
            notification = notificationCreator.createFromProperty(Notification.Type.ERROR, messageKey);
            notificationService.push(notification);

        }

        return null;
    }
}
