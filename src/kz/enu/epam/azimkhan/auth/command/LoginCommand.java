package kz.enu.epam.azimkhan.auth.command;

import kz.enu.epam.azimkhan.auth.exception.AuthenticationException;
import kz.enu.epam.azimkhan.auth.logic.authentication.AuthenticationLogic;
import kz.enu.epam.azimkhan.auth.notification.creator.NotificationCreator;
import kz.enu.epam.azimkhan.auth.notification.entity.Notification;
import kz.enu.epam.azimkhan.auth.notification.service.NotificationService;
import kz.enu.epam.azimkhan.auth.resource.PathManager;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Login command
 */
public class LoginCommand extends ActionCommand{

    public static final String LOGIN_PARAMETER = "login";
    public static final String PASSWORD_PARAMETER = "password";
    private Logger logger = Logger.getRootLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("login_var", LOGIN_PARAMETER);
        request.setAttribute("password_var", PASSWORD_PARAMETER);
        final String login = request.getParameter(LOGIN_PARAMETER);
        final String password = request.getParameter(PASSWORD_PARAMETER);
        final NotificationCreator notificationCreator = new NotificationCreator(request);
        final NotificationService notificationService = new NotificationService(request);
        final AuthenticationLogic logic = new AuthenticationLogic(request);


        PathManager pathManager = PathManager.INSTANCE;
        Notification notification = null;

        if (login != null && password != null){
            try {
                if (logic.authenticate(login, password)){

                    logger.info("Successful authentication by login: " + login);
                    notification = notificationCreator.createFromProperty("info.auth.success");

                    return pathManager.getString("path.page.main");
                }else{
                    logger.info("Authentication fail by login: " + login);
                    notification = notificationCreator.createFromProperty(Notification.Type.ERROR,"error.auth.invalid_login_pass");
                }

            } catch (AuthenticationException e) {
                throw new ServletException(e);
            } finally {
                if (notification != null){
                    notificationService.push(notification);
                }
            }
        }

        request.getRequestDispatcher("jsp/login.jsp").forward(request, response);

        return null;
    }
}
